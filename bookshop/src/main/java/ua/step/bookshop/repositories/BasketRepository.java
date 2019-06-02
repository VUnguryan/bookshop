package ua.step.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.step.bookshop.models.Basket;

public interface BasketRepository extends JpaRepository<Basket, Integer> {

}
