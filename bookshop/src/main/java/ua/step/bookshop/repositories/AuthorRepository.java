package ua.step.bookshop.repositories;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.step.bookshop.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
	Author findByName(String name);
	//List<Author> findAllByNameAsc(Sort sort);
	//List<Author> findAllByName(String name, Pageable pageable);
	//List<Author> findAllByOrderByNameAsc(String authorName);
//List<Author> findAllByOrderByDateAsc();
}
