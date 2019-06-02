package ua.step.bookshop.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.step.bookshop.models.Genre;


public interface GenreRepository extends JpaRepository<Genre, Short> {
/*	@Query("select b from Genre b where b.name = :name")
	Genre findByName(@Param("name") String name);
    
    Page <Genre> findAll (Pageable page);
    Page <Genre> findAllByOrderByNameDesc (String name);*/
}