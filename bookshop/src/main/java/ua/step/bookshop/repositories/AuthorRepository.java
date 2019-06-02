package ua.step.bookshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.step.bookshop.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}