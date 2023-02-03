package ru.gb.spring.shopprj.convertors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.spring.shopprj.dto.CategoryDto;
import ru.gb.spring.shopprj.entities.Category;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryConvertor {
    private final ProductConvertor productConvertor;

    public CategoryDto entityToDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setProducts(category.getProducts().stream().map(productConvertor::entityToDto).collect(Collectors.toList()));
        return categoryDto;
    }
}
