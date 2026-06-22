package sn.iage.isi.main;

import sn.iage.isi.entities.Book;
import sn.iage.isi.entities.Category;
import sn.iage.isi.repositories.BookRepository;
import sn.iage.isi.repositories.CategoryRepository;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        CategoryRepository categoryRepository = new CategoryRepository();
        BookRepository bookRepository = new BookRepository();

        // Création d'un livre
//        Book book = new Book();
//        book.setTitle("L'école des femmes");
//        book.setAuthor("Molière");
//        book.setPublicationYear(1662);
//        book.setCountPages(191);
//        book.setCategory(categoryRepository.getById(3));
//
//        bookRepository.createBook(book);

        // Liste de tous les livres
//        List<Book> books = bookRepository.ListAllBooks();
//        for (Book b : books) {
//            System.out.println(b);
//        }

        // Trouver un livre grace à son id
//        Book book = bookRepository.findBookById(3);
//        System.out.println(book);

        // Trouver un livre grace à son isbn
//        Book book = bookRepository.findBookByIsbn("978-0-3746-4427-7");
//        System.out.println(book);

        // Mettre à jour un livre
//        Book b = new Book();
//        b.setTitle("Les Fleurs du Mal");
//        b.setAuthor("Charles Baudelaire");
//        b.setPublicationYear(1857);
//        b.setCountPages(256);
//        b.setCategory(categoryRepository.getById(4));
//
//        bookRepository.updateBook(4, b);

        // Supprimer un livre
//        bookRepository.deleteBook(5);

        // Liste des livres par catégorie
//        List<Book> books = bookRepository.ListeBooksByCategory("théâtre");
//        for (Book b : books) {
//            System.out.println(b);
//        }

        // Rechercher des livres par titre
//        List<Book> books = bookRepository.searchBooksByTitle("m");
//        for(Book book : books){
//            System.out.println(book);
//        }

        // Rechercher des livres par auteur
//        List<Book> books = bookRepository.searchBooksByAuthor("ch");
//        for(Book book : books){
//            System.out.println(book);
//        }

        // Rechercher des livres parus après une année donnée
//        List<Book> books = bookRepository.searchBooksAfterYear(1700);
//        for (Book book : books) {
//            System.out.println(book);
//        }

        // Nombre de livres par catégorie
//        String categoryName = "Roman";
//        long nb = bookRepository.countBooksByCategory(categoryName);
//        System.out.println("Nombre de livre de la catégorie " + categoryName  + " : " + nb);

        // Nombre total de livres
//        long nb = bookRepository.countAllBooks();
//        System.out.println("Nombre total de livre : " + nb);
    }
}