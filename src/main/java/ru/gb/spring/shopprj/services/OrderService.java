package ru.gb.spring.shopprj.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.spring.shopprj.entities.User;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductService productService;

    public void createOrder(User user){

    }
}
