package ru.gb.winter.market.core.convertors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.winter.market.api.dto.ProductDto;
import ru.gb.winter.market.api.dto.ResourceNotFoundException;
import ru.gb.winter.market.core.entities.Category;
import ru.gb.winter.market.core.entities.Product;
import ru.gb.winter.market.core.services.CategoryService;

@Component
@RequiredArgsConstructor
public class ProductConvertor {
    private final CategoryService categoryService;
    public ProductDto entityToDto(Product product){
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice(), product.getCategory().getTitle());
    }

    public Product dtoToEntity(ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Категория не найдена"));
        product.setCategory(category);
        return product;
    }
}
