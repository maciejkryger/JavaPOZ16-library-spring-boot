package pl.sda.library.service;

import org.springframework.stereotype.Service;
import pl.sda.library.model.Book;
import pl.sda.library.repository.BookRepository;

import java.time.LocalDate;
import java.util.HashSet;
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

    public Optional<Book> rentBook(int id) {
            Optional<Book> optionalBook= bookRepository.findById(id);
            if(optionalBook.isPresent()){
                Book book = optionalBook.get();
                book.setBorrowedTill(setBorrowTill());
                bookRepository.save(book);
            }
            return optionalBook;
    }

    public Optional<Book> returnBook(int bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()){
            Book book = optionalBook.get();
            book.setBorrowedTill(null);
            bookRepository.save(book);
        }
        return optionalBook;
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public void removeBook(int id) {
        bookRepository.deleteById(id);
    }

    public Set<Book> getBooks(String title, String author){
        if (title!=null && author == null) {
            return bookRepository.findByTitle(title);
        }
        else if (title==null && author != null) {
            return bookRepository.findByAuthor(author);
        }
        else if (title!=null && author != null) {
            return bookRepository.findByTitleAndAuthor(title,author);
        }
            return new HashSet<>(bookRepository.findAll());
    }



//    public void updateBook(Book book) {
//        bookRepository.update(book);
//    }

    public Optional<Book> findBookById(int id) {
    return bookRepository.findById(id);
    }
}
