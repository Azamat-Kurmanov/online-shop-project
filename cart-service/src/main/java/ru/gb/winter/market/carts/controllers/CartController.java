package ru.gb.winter.market.carts.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.winter.market.api.dto.CartDto;
import ru.gb.winter.market.carts.convertors.CartConverter;
import ru.gb.winter.market.carts.services.CartService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
@CrossOrigin("*")
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id){
        System.out.println("id: " + id);
        cartService.add(id);
    }

    @GetMapping("/clear")
    public void clearCart(){
        cartService.clear();
    }

    @GetMapping("/remove/{id}")
    public void removeFromCart(@PathVariable Long id){
        cartService.remove(id);
    }

    @GetMapping
    public CartDto getCurrentCart() {
        return cartConverter.entityToDto(cartService.getCurrentCart());
    }

}
