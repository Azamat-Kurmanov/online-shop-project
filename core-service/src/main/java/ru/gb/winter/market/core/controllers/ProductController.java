package ru.gb.winter.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.winter.market.api.dto.ProductDto;
import ru.gb.winter.market.api.dto.ResourceNotFoundException;
import ru.gb.winter.market.core.convertors.ProductConvertor;
import ru.gb.winter.market.core.entities.Product;
import ru.gb.winter.market.core.services.CategoryService;
import ru.gb.winter.market.core.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductConvertor productConvertor;

    @GetMapping
    public List<ProductDto> findAllProducts() {
        return productService.findAll().stream().map(productConvertor::entityToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id){
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт не найден, id:" + id));
        return productConvertor.entityToDto(product);
    }

    @PostMapping
    public ProductDto createNewProduct(@RequestBody ProductDto productDto){
        Product product = productService.createNewProduct(productDto);
        return productConvertor.entityToDto(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id){
        productService.deleteById(id);
    }
}
