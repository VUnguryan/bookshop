package ua.step.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.step.bookshop.models.Genre;

public interface GenreRepository extends JpaRepository<Genre, Short> {
}