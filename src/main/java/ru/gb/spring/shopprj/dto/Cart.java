package ru.gb.spring.shopprj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.spring.shopprj.entities.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Cart {
    private List<CartItem> items;
    private int totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void add(Product product){   // TODO: Доработать ДЗ
        items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        items.stream().filter(products -> products.getProductTitle()==product.getTitle());
        recalculate();
        countProducts(product);
    }

    private void countProducts(Product product) {
        List<ProductDto> productList = new ArrayList<>();
        int quantity = 0;
        int totalSum = 0;
        if (productList.size()==0) {
            productList.add(new ProductDto(product.getId(), product.getTitle(), product.getPrice()));
        } else {
            for (ProductDto productDto: productList){
                if (productDto.getTitle()==product.getTitle()){
                    quantity+=1;
                    totalSum = quantity * product.getPrice();
                }
            }
        }
        System.out.println("Product: " + product.getTitle() + " quantity: " + quantity + " totalSum: " + totalSum);
    }

    public void recalculate() {
        totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.getPrice();
        }
    }
}
