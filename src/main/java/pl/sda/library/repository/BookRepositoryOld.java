package pl.sda.library.repository;

import org.springframework.stereotype.Repository;
import pl.sda.library.exception.BookNotFoundException;
import pl.sda.library.model.Book;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


public class BookRepositoryOld {

//    Set<Book> books = new HashSet<Book>();
//
//    public BookRepository() {
//        books.add(new Book(1, "Jan Brzechwa", "Lokomotywa"));
//        books.add(new Book(2, "Marta Abramowicz", "Zakonnice odchodzą po cichu"));
//        books.add(new Book(3, "Katarzyna Miszczuk", "Szeptucha"));
//        books.add(new Book(4, "Justyna Kopińska", "Polska odwraca oczy"));
//        books.add(new Book(5, "Zośka Papużanka", "On"));
//        books.add(new Book(6, "ks. Jan Kaczkowski", "Życie na pełnej petardzie"));
//        books.add(new Book(7, "Katarzyna Bonda", "Okularnik"));
//        books.add(new Book(8, "Małgorzata Halber", "Najgorszy człowiek na świecie"));
//        books.add(new Book(9, "Łukasz Orbitowski", "Inna dusza"));
//        books.add(new Book(10, "Justyna Wydra", "Esesman i Żydówka"));
//    }

    private Set<Book> books = initialize();

    private Set<Book> initialize() {
        return new HashSet<>(Arrays.asList(
                new Book(1, "Testy", "Kaczanowski"),
                new Book(2, "Dzuma", "Camus"),
                new Book(3, "W pustyni i w puszczy", "Sienkiewicz"),
                new Book(4, "Pan Tadeusz", "Mickiewicz"),
                new Book(5, "Dziady", "Mickiewicz"),
                new Book(6, "Anioly i demony", "Brown"),
                new Book(7, "Gra o tron", "Martin"),
                new Book(8, "Harry Potter", "Rowling"),
                new Book(9, "Testy", "Kaczanowski"),
                new Book(10, "Testy", "Kaczanowski")
        ));
    }

    public Optional<Book> borrowBook(int id, LocalDate borrowedTill) {
        Optional<Book> any = books.stream()
                .filter(book -> id==book.getId())
                .filter(book -> book.getBorrowedTill() == null)
                .findAny();
        any.ifPresent(book -> book.setBorrowedTill(borrowedTill));
        return any;
    }

    public Optional<Book> returnBook(int id) {
        Optional<Book>any= books.stream()
                .filter(book -> id == (book.getId()))
                .findAny();
//                .orElseThrow(() -> new RuntimeException("Book not found"))
//                .setBorrowedTill(null);
        any.ifPresent(book -> book.setBorrowedTill(null));
        return any;
    }

    public Book addNewBook(Book bookToAdd) {
        bookToAdd.setId(generateNextId());
        books.add(bookToAdd);
        return bookToAdd;
    }

    public boolean removeBook(int id) {
        Optional<Book> bookToRemove = books.stream().filter(book -> id == book.getId())
                .findFirst();
//                .orElseThrow(() -> new RuntimeException("Book not found"));
        if (bookToRemove.isPresent()){
        return books.remove(bookToRemove.get());
        }
        return false;
    }

    public Set<Book> getBooks(String title, String author){
         if (author==null && title !=null) {
            return books.stream()
                    .filter(book -> book.getTitle().equals(title))
                    .collect(Collectors.toSet());
        }
        else if (title==null && author !=null) {
            return books.stream()
                    .filter(book -> book.getAuthor().equals(author))
                    .collect(Collectors.toSet());
        }else return books;
    }

    private int generateNextId() {
        if(books.size()==0){ return 1; }
        return books.stream()
                .mapToInt(Book::getId)
                .max().getAsInt() + 1;
    }

    public void update(Book book) {
        books.remove(book);
        books.add(book);
    }

    public Optional<Book> findBookById(int id) {
        return books.stream()
                .filter(bookInBase -> bookInBase.getId()==id)
                .findAny();
    }
}
