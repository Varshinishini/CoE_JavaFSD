package library_Management;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

// Book Class
class Book {
    private String title;
    private String author;
    private String ISBN;

    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getISBN() { return ISBN; }
}

// User Class
class User {
    private String name;
    private String userID;
    private List<Book> borrowedBooks;

    public User(String name, String userID) {
        this.name = name;
        this.userID = userID;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() { return name; }
    public String getUserID() { return userID; }
    public List<Book> getBorrowedBooks() { return borrowedBooks; }
}

// ILibrary Interface
interface ILibrary {
    void borrowBook(String ISBN, String userID) throws BookNotFoundException, UserNotFoundException, MaxBooksAllowedException;
    void returnBook(String ISBN, String userID) throws BookNotFoundException, UserNotFoundException;
    void reserveBook(String ISBN, String userID) throws BookNotFoundException, UserNotFoundException;
    Book searchBook(String title);
}

// Abstract LibrarySystem Class
abstract class LibrarySystem implements ILibrary {
    protected List<Book> books = new ArrayList<>();
    protected List<User> users = new ArrayList<>();

    public abstract void addBook(Book book);
    public abstract void addUser(User user);
}

// Custom Exceptions
class BookNotFoundException extends Exception {
    public BookNotFoundException(String message) { super(message); }
}
class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) { super(message); }
}
class MaxBooksAllowedException extends Exception {
    public MaxBooksAllowedException(String message) { super(message); }
}

// LibraryManager Class
class LibraryManager extends LibrarySystem {
    private final Map<String, Book> bookMap = new ConcurrentHashMap<>();
    private final Map<String, User> userMap = new ConcurrentHashMap<>();
    private final int MAX_BOOKS = 3;
    private final ExecutorService executorService = Executors.newFixedThreadPool(3);

    @Override
    public synchronized void addBook(Book book) {
        books.add(book);
        bookMap.put(book.getISBN(), book);
    }

    @Override
    public synchronized void addUser(User user) {
        users.add(user);
        userMap.put(user.getUserID(), user);
    }

    @Override
    public void borrowBook(String ISBN, String userID) throws BookNotFoundException, UserNotFoundException, MaxBooksAllowedException {
        executorService.submit(() -> {
            try {
                User user = userMap.get(userID);
                if (user == null) throw new UserNotFoundException("User not found!");

                Book book = bookMap.get(ISBN);
                if (book == null) throw new BookNotFoundException("Book not found!");

                if (user.getBorrowedBooks().size() >= MAX_BOOKS)
                    throw new MaxBooksAllowedException("User has reached max book limit!");

                user.getBorrowedBooks().add(book);
                System.out.println(user.getName() + " borrowed " + book.getTitle());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        });
    }

    @Override
    public void returnBook(String ISBN, String userID) throws BookNotFoundException, UserNotFoundException {
        executorService.submit(() -> {
            try {
                User user = userMap.get(userID);
                if (user == null) throw new UserNotFoundException("User not found!");

                Book book = bookMap.get(ISBN);
                if (book == null || !user.getBorrowedBooks().contains(book))
                    throw new BookNotFoundException("Book not borrowed by user!");

                user.getBorrowedBooks().remove(book);
                System.out.println(user.getName() + " returned " + book.getTitle());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        });
    }

    @Override
    public void reserveBook(String ISBN, String userID) throws BookNotFoundException, UserNotFoundException {
        System.out.println("Book reserved: " + ISBN + " by user " + userID);
    }

    @Override
    public Book searchBook(String title) {
        return books.stream().filter(b -> b.getTitle().equalsIgnoreCase(title)).findFirst().orElse(null);
    }
}

// Main Class
public class LibrarySystemMain {
    public static void main(String[] args) {
        LibraryManager libraryManager = new LibraryManager();

        // Adding Books
        libraryManager.addBook(new Book("The Alchemist", "Paulo Coelho", "12345"));
        libraryManager.addBook(new Book("1984", "George Orwell", "67890"));

        // Adding Users
        libraryManager.addUser(new User("Alice", "U1"));
        libraryManager.addUser(new User("Bob", "U2"));

        // Borrowing Books
        try {
            libraryManager.borrowBook("12345", "U1");
            libraryManager.borrowBook("67890", "U2");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

