package ru.gb.winter.market.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.winter.market.core.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
