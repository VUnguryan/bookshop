package ua.step.bookshop.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import ua.step.bookshop.models.Book;

/**
 * 
 * @author Константин
 *
 */

public interface BookRepository extends JpaRepository<Book, Integer> {

}
