package sn.iage.isi.main;

import sn.iage.isi.entities.Book;
import sn.iage.isi.entities.Category;
import sn.iage.isi.repositories.BookRepository;
import sn.iage.isi.repositories.CategoryRepository;
import sn.iage.isi.repositories.JpaUtil;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        CategoryRepository categoryRepository = new CategoryRepository();
        BookRepository bookRepository = new BookRepository();

        // Pour Créer quelques catégories
//        {
//            categoryRepository.create(Category.builder().name("Roman").build());
//            categoryRepository.create(Category.builder().name("Théâtre").build());
//            categoryRepository.create(Category.builder().name("Poésie").build());
//            System.out.println("Catégories créées !");
//        }

        // Pour Créer un livre
//        {
//            Book book = new Book();
//            book.setTitle("L'école des femmes");
//            book.setAuthor("Molière");
//            book.setPublicationYear(1662);
//            book.setCountPages(191);
//            book.setCategory(categoryRepository.getById(3));
//            Book created = bookRepository.createBook(book);
//            System.out.println("Livre créé : " + created);
//        }

        // Pour Lister tous les livres
//        {
//            List<Book> books = bookRepository.ListAllBooks();
//            for (Book b : books) {
//                System.out.println(b);
//            }
//        }

        // =====  Pour Trouver un livre par son id =====
//        {
//            Book book = bookRepository.findBookById(1);
//            System.out.println(book);
//        }

        // ===== Pour Trouver un livre par son isbn =====
//        {
//            Book book = bookRepository.findBookByIsbn("979-0-1306-6716-7");
//            System.out.println(book);
//        }

        // Pour Mettre à jour un livre
//        {
//            Book b = new Book();
//            b.setTitle("Les Fleurs du Mal");
//            b.setAuthor("Charles Baudelaire");
//            b.setPublicationYear(1857);
//            b.setCountPages(256);
//            b.setCategory(categoryRepository.getById(4));
//            Book updated = bookRepository.updateBook(1, b);
//            System.out.println("Livre mis à jour : " + updated);
//        }

        // Pour Supprimer un livre
//        {
//            bookRepository.deleteBook(1);
//            System.out.println("Livre supprimé.");
//        }

        // Pour Lister des livres par catégorie
//        {
//            List<Book> books = bookRepository.ListeBooksByCategory("théâtre");
//            for (Book b : books) {
//                System.out.println(b);
//            }
//        }

        // Pour Rechercher des livres par titre
//        {
//            List<Book> books = bookRepository.searchBooksByTitle("m");
//            for (Book b : books) {
//                System.out.println(b);
//            }
//        }

        // Pour Rechercher des livres par auteur
//        {
//            List<Book> books = bookRepository.searchBooksByAuthor("ch");
//            for (Book b : books) {
//                System.out.println(b);
//            }
//        }

        // Pour Rechercher des livres publier après une année donnée
//        {
//            List<Book> books = bookRepository.searchBooksAfterYear(1700);
//            for (Book b : books) {
//                System.out.println(b);
//            }
//        }

        // Pour lister le  Nombre de livres par catégorie
//        {
//            Map<String, Long> parCategorie = bookRepository.countBooksByCategory();
//            parCategorie.forEach((cat, nb) ->
//                    System.out.println(cat + " : " + nb + " livre(s)"));
//        }

        //  Nombre total de livres
//        {
//            long nb = bookRepository.countAllBooks();
//            System.out.println("Nombre total de livres : " + nb);
//        }


        JpaUtil.close();
    }
}