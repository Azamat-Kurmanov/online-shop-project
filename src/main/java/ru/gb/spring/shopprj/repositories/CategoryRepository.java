package ru.gb.spring.shopprj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.spring.shopprj.entities.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByTitle(String title);
}
