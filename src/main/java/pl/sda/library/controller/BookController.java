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
    public Set<Book> getAllBooks(@RequestParam(required = false) String title, @RequestParam(required = false) String author) {
        return orderService.getBooks(title,author);
    }

    @GetMapping(value = "/books/{id}", produces = "application/json")
    public ResponseEntity getBooksById(@PathVariable(required = false) int id) {
        Optional<Book> optionalBook = orderService.findBookById(id);
        if (optionalBook.isPresent()) {
            return ResponseEntity.ok(optionalBook.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/book/borrow/{id}", produces = "application/json")
    public ResponseEntity<Book> borrowBook(@PathVariable int id) {
        Optional<Book> book = orderService.rentBook(id);
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
        Book addedBook = orderService.addBook(book);
        return new ResponseEntity<>(addedBook.getId(),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/book/remove/{id}")
    public ResponseEntity removeBook(@PathVariable int id) {
        orderService.removeBook(id);
        return ResponseEntity.noContent().build();
    }


}
