package ua.step.bookshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.step.bookshop.models.Rate;

public interface RateRepository extends JpaRepository<Rate, Integer>{
	List<Rate> findByOrderByBook();
	Rate findByOrderByUser();

}
