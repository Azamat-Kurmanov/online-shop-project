package ru.gb.spring.shopprj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.spring.shopprj.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
