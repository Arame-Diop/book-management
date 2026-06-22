package sn.iage.isi.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityTransaction;
import sn.iage.isi.entities.Book;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BookRepository {
    EntityManager em = JpaUtil.getEntityManager();

    private String generateIsbn() {
        // Préfixe ISBN-13 : 978 ou 979
        String[] prefixes = {"978", "979"};
        Random random = new Random();

        String prefix = prefixes[random.nextInt(2)];        // 978 ou 979
        String group = String.valueOf(random.nextInt(2));    // 0 ou 1 (groupe langue)
        String publisher = String.format("%04d", random.nextInt(10000));   // éditeur 4 chiffres
        String title    = String.format("%04d", random.nextInt(10000));    // titre   4 chiffres

        // Calcul du chiffre de contrôle (checksum ISBN-13)
        String base = prefix + group + publisher + title;   // 12 chiffres
        int checkDigit = computeIsbn13CheckDigit(base);

        String isbn = base + checkDigit;

        // Format lisible : 978-X-XXXX-XXXX-X
        return String.format("%s-%s-%s-%s-%d",
                prefix, group, publisher, title, checkDigit);
    }

    private int computeIsbn13CheckDigit(String base12) {
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int digit = Character.getNumericValue(base12.charAt(i));
            sum += (i % 2 == 0) ? digit : digit * 3;   // alternance poids 1 et 3
        }
        int remainder = sum % 10;
        return remainder == 0 ? 0 : 10 - remainder;
    }

    public Book createBook(Book book) {
        EntityTransaction tx = em.getTransaction();
        Book b = Book.builder()
                .isbn(generateIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publicationYear(book.getPublicationYear())
                .countPages(book.getCountPages())
                .category(book.getCategory())
                .build();
        b.setUserUpdated("admin");
        b.setUserCreated("admin");
        try {
            tx.begin();
            em.persist(b);
            tx.commit();
            // Sécurisé : on n'ajoute à la liste que si elle est déjà chargée (évite le NullPointerException)
            if (book.getCategory() != null && book.getCategory().getBooks() != null) {
                book.getCategory().getBooks().add(b);
            }
        } catch (Exception e) {
            tx.rollback();
        }
        return b;
    }

    public List<Book> ListAllBooks() {
        return em
                .createNamedQuery("Book.findAll", Book.class)
                .getResultList();
    }

    public Book findBookById(int id) {
        Book book = em.find(Book.class, id);
        if (book == null)
            throw new EntityNotFoundException("Book not found");
        return book;
    }

    public Book findBookByIsbn(String isbn) {
        try {
            return em
                    .createQuery("SELECT b FROM Book b WHERE b.isbn = :isbn", Book.class)
                    .setParameter("isbn", isbn)
                    .getSingleResult();
        } catch (Exception e) {
            throw new EntityNotFoundException("Book not found");
        }
    }

    public Book updateBook(int id, Book newBook) {
        EntityTransaction tx = em.getTransaction();
        Book b = findBookById(id);
        if (b != null) {
            b.setTitle(newBook.getTitle());
            b.setAuthor(newBook.getAuthor());
            b.setPublicationYear(newBook.getPublicationYear());
            b.setCountPages(newBook.getCountPages());
            b.setCategory(newBook.getCategory());
            try {
                tx.begin();
                em.merge(b);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
            }
        }
        return b;
    }

    public void deleteBook(int id) {
        EntityTransaction tx = em.getTransaction();
        Book b = findBookById(id);
        try {
            tx.begin();
            em.remove(b);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }

    public List<Book> ListeBooksByCategory(String categoryName) {
        return em
                .createQuery("SELECT b FROM Book b WHERE LOWER(b.category.name) = :categoryName", Book.class)
                .setParameter("categoryName", categoryName.toLowerCase())
                .getResultList();
    }

    public List<Book> searchBooksByTitle(String keyword) {
        return em
                .createQuery("SELECT b FROM Book b WHERE LOWER(b.title) LIKE :kw", Book.class)
                .setParameter("kw", "%" + keyword.toLowerCase() + "%")
                .getResultList();
    }

    public List<Book> searchBooksByAuthor(String keyword) {
        return em
                .createQuery("SELECT b FROM Book b WHERE LOWER(b.author) LIKE :kw", Book.class)
                .setParameter("kw", "%" + keyword.toLowerCase() + "%")
                .getResultList();
    }

    public List<Book> searchBooksAfterYear(int year) {
        return em
                .createQuery("SELECT b FROM Book b WHERE b.publicationYear > :year", Book.class)
                .setParameter("year", year)
                .getResultList();
    }

    // Nombre de livres PAR catégorie (sans paramètre) -> un total par catégorie
    public Map<String, Long> countBooksByCategory() {
        List<Object[]> results = em
                .createQuery(
                        "SELECT b.category.name, COUNT(b) " +
                                "FROM Book b " +
                                "GROUP BY b.category.name " +
                                "ORDER BY b.category.name", Object[].class)
                .getResultList();

        Map<String, Long> counts = new LinkedHashMap<>();
        for (Object[] row : results) {
            String categoryName = (String) row[0];
            Long total = (Long) row[1];
            counts.put(categoryName, total);
        }
        return counts;
    }

    public Long countAllBooks() {
        return em
                .createQuery("SELECT COUNT(b.id) FROM Book b", Long.class)
                .getSingleResult();
    }
}