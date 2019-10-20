package pl.sda.library.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.Objects;

public class Book {

    int id;
    String author;
    String title;
    LocalDate borrowedTill;

    public Book() {
    }

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;

    }

    public Book(String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    @JsonGetter //dla nowszych wersji SpringBoota nie jest wymagane
    public int getId() {
        return id;
    }

    @JsonIgnore //dla nowszych wersji SpringBoota nie jest wymagane
    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonGetter //dla nowszych wersji SpringBoota nie jest wymagane
    public LocalDate getBorrowedTill() {
        return borrowedTill;
    }

    @JsonIgnore //dla nowszych wersji SpringBoota nie jest wymagane
    public void setBorrowedTill(LocalDate borrowedTill) {
        this.borrowedTill = borrowedTill;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Book book = (Book) o;
        return id == book.id;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
                .append("bookId: ")
                .append(id)
                .append(" | Title: ")
                .append(title)
                .append(" | Author: ")
                .append(author);
        if (borrowedTill != null) {
            builder.append(" | borrowed up to: ")
                    .append(borrowedTill);
        }
        return builder.toString();
    }
}
