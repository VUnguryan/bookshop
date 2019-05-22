package ua.step.bookshop.repositories;
/*
import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.step.bookshop.models.Author;
import ua.step.bookshop.models.Book;

public interface AuthorRepository extends JpaRepository<Author, Integer> {*/
	
	import java.util.List;

	import org.springframework.data.domain.Page;
	import org.springframework.data.domain.Pageable;
	import org.springframework.data.domain.Slice;
	import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
	import org.springframework.data.repository.CrudRepository;
	import org.springframework.data.repository.query.Param;

import ua.step.bookshop.models.Author;

public 	interface AuthorRepository
			extends JpaRepository<Author, Long> {
		//extends CrudRepositoryJpaRepository<Author, Long> {

		@Query("select c from Author c")
		Page<Author> findAllPage(Pageable pageable);

		@Query("select c from Author c")
		Slice<Author> findAllSlice(Pageable pageable);

		@Query("select c from Author c")
		List<Author> findAllSorted(Sort sort);

		Page<Author> findByMovie(String authorName, Pageable pageable);

		@Query("select c from Author c where c.name = :name")
		Slice<Author> findByMovieCustom(
				@Param("name") String authorName, Pageable pageable);

		@Query("select c from Author c where c.name = :name")
		List<Author> findByMovieSorted(
				@Param("name") String authorName, Sort sort);
	
}