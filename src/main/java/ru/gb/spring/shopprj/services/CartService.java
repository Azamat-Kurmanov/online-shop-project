package ru.gb.spring.shopprj.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.spring.shopprj.model.Cart;
import ru.gb.spring.shopprj.entities.Product;
import ru.gb.spring.shopprj.exceptions.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private Cart tempCart;

    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }

    public Cart getCurrentCart() {
        tempCart.getItems().forEach(it -> System.out.println("tempCart: " + it.getProductId() + " title: " + it.getProductTitle()));
        return tempCart;
    }

    public void add(Long productId){
        Product product = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Не удается добавить продукт с id"+productId+" в корзину. Продукт не найден"));
        tempCart.add(product);
    }

    public void remove(Long productId){
        tempCart.remove(productId);
    }

    public void clear(){
        tempCart.clear();
    }
}
