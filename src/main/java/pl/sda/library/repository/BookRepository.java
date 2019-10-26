package pl.sda.library.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.library.model.Book;
import java.util.Set;


public interface BookRepository extends JpaRepository<Book, Integer> {



    Set<Book> findByTitle(String title);

    Set<Book> findByAuthor(String author);

    Set<Book> findByTitleAndAuthor(String title, String author);


}
