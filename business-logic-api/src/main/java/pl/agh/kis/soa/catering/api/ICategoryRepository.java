package pl.agh.kis.soa.catering.api;

import pl.agh.kis.soa.catering.model.Category;

import java.util.List;


public interface ICategoryRepository {

    void addCategory(Category category);

    Category getCategory(Long categoryId);

    List<Object> getAllCategories();

    void updateCategory(Category category);

    void deleteCategory(Long categoryId);

}
