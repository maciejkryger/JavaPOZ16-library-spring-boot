package pl.sda.library.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.library.model.Book;
import pl.sda.library.service.OrderService;

import java.util.Optional;
import java.util.Set;

@RestController
public class BookController {

    private final OrderService orderService;

    public BookController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/books", produces = "application/json")
    public Set<Book> getAllBooks(@RequestParam(required = false) String title) {
        return orderService.getBooks(title);
    }

    @GetMapping(value = "/book/order/{id}", produces = "application/json")
    public ResponseEntity<Book> borrowBook(@PathVariable int id) {
        Optional<Book> book = orderService.borrowBook(id);
        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/book/return/{id}", produces = "application/json")
    public ResponseEntity<Book> returnBook(@PathVariable int id) {
        Optional<Book> book = orderService.returnBook(id);
        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/book/add", consumes = "application/json")
    public ResponseEntity<Integer> addBook(@RequestBody Book book) {
        Book addedBook = orderService.addNewBook(book);
        return new ResponseEntity<>(addedBook.getId(),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/book/remove/{id}")
    public ResponseEntity deleteBook(@PathVariable int id) {
        if (orderService.removeBook(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(id);
        }
        return ResponseEntity.notFound().build();
    }

}
