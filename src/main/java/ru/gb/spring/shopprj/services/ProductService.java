package ru.gb.spring.shopprj.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.spring.shopprj.dto.ProductDto;
import ru.gb.spring.shopprj.entities.Category;
import ru.gb.spring.shopprj.entities.Product;
import ru.gb.spring.shopprj.exceptions.ResourceNotFoundException;
import ru.gb.spring.shopprj.repositories.ProductRepository;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product createNewProduct(ProductDto productDto) {
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(product.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() ->  new ResourceNotFoundException("Category not found"));
        product.setCategory(category);
        productRepository.save(product);
        return product;
    }
}
