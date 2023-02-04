package ru.gb.winter.market.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.winter.market.core.entities.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByTitle(String title);
}
