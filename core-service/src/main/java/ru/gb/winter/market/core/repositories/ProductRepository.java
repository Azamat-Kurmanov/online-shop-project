package ru.gb.winter.market.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.winter.market.core.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
