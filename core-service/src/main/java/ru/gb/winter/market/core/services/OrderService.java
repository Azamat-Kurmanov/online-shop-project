package ru.gb.winter.market.core.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.winter.market.api.dto.CartDto;
import ru.gb.winter.market.api.dto.CartItemDto;
import ru.gb.winter.market.core.entities.Order;
import ru.gb.winter.market.core.entities.OrderItem;
import ru.gb.winter.market.core.entities.User;
import ru.gb.winter.market.core.repositories.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductService productService;
    private final OrderRepository orderRepository;

    @Transactional
    public void createOrder(User user){
        CartDto cartDto = null;
        //----------
//        cartDto = new CartDto();
//        cartDto.setTotalPrice(100);
//        CartItemDto cartItemDto = new CartItemDto();
//        cartItemDto.setProductId(1L);
//        cartItemDto.setQuantity(1);
//        cartItemDto.setPricePerProduct(25);
//        cartItemDto.setPrice(25);
//        cartItemDto.setProductTitle("Bread");
//        cartDto.setItems(
//                List.of(cartItemDto)
//        );
        //----------
        Order order = new Order();
        order.setUser(user);
        order.setItems(cartDto.getItems().stream().map(
            cartItem -> new OrderItem(
                productService.findById(cartItem.getProductId()).get(),
                order,
                cartItem.getQuantity(),
                cartItem.getPricePerProduct(),
                cartItem.getPrice()
            )
        ).collect(Collectors.toList()));
        orderRepository.save(order);
    }
}
