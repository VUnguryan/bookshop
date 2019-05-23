package ua.step.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.step.bookshop.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
