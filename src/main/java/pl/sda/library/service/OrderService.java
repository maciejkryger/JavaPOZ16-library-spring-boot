package pl.sda.library.service;

import org.springframework.stereotype.Service;
import pl.sda.library.model.Book;
import pl.sda.library.repository.BookRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderService {

    private final BookRepository bookRepository;

    public OrderService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    private LocalDate setBorrowTill() {
        return LocalDate.now().plusDays(30);
    }

    public Optional<Book> borrowBook(int id) {
        return bookRepository.borrowBook(id, setBorrowTill());
    }

    public Optional<Book> returnBook(int bookId) {
        return bookRepository.returnBook(bookId);
    }

    public Book addNewBook(Book book) {
        return bookRepository.addNewBook(book);
    }

    public boolean removeBook(int id) {
        return bookRepository.removeBook(id);
    }

    public Set<Book> getBooks(String title, String author){
        return bookRepository.getBooks(title,author);
    }

    public void updateBook(Book book) {
        bookRepository.update(book);
    }

    public Book findBookById(int id) {
    return bookRepository.findBookById(id);
    }
}
