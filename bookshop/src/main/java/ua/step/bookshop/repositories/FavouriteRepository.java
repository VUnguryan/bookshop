package ua.step.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.step.bookshop.models.Favourites;

public interface FavouriteRepository extends JpaRepository<Favourites, Integer> {
}