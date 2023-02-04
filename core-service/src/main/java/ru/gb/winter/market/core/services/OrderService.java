package ru.gb.winter.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.winter.market.core.entities.User;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductService productService;

    public void createOrder(User user){

    }
}
