package ua.step.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.step.bookshop.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
