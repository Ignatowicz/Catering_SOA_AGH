package pl.agh.kis.soa.catering.managers;

import lombok.Getter;
import lombok.Setter;
import pl.agh.kis.soa.catering.api.*;
import pl.agh.kis.soa.catering.model.Category;
import pl.agh.kis.soa.catering.model.Dish;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;


@Getter
@Setter
@Named("ModifyMenuManager")
@ManagedBean
@SessionScoped
public class ModifyMenuManager implements Serializable {

    @EJB(lookup = "java:global/business-logic-implementation/CategoryRepository")
    private ICategoryRepository categoryRepository;

    @EJB(lookup = "java:global/business-logic-implementation/DishRepository")
    private IDishRepository dishRepository;

    private String dishName;
    private String dishPrice;
    private Long selectedDish;
    private boolean modifyDish;

    private String categoryName;
    private Long selectedCategory;
    private boolean modifyCategory;
    private Long categoryNumber;

    public ModifyMenuManager() {
        categoryNumber = -1L;
        modifyCategory = false;
        modifyDish = false;
    }

    public void clearAll() {
        modifyCategory = false;
        selectedCategory = null;
        modifyDish = false;
        selectedDish = null;
        categoryName = null;
        dishName = null;
        dishPrice = null;
        categoryNumber = null;
    }

    public String addNewCategory() {
        Category category = new Category();
        category.setName(categoryName);
        categoryRepository.addCategory(category);
        clearAll();
        return "/catering_products.xhtml?faces-redirect=true";
    }

    public String modifyCategory() {
        Category category = new Category();
        category.setId(selectedCategory);
        category.setName(categoryName);
        categoryRepository.updateCategory(category);
        clearAll();
        return "/catering_products.xhtml?faces-redirect=true";
    }

    public String addNewDish() {
        if (categoryNumber > -1L) {
            Dish dish = new Dish();
            dish.setName(dishName);
            dish.setPrice(Float.valueOf(dishPrice));
            dish.setCategory(categoryRepository.getCategory(categoryNumber));
            dishRepository.addDish(dish);
        }
        categoryNumber = -1L;
        clearAll();
        return "/catering_products.xhtml?faces-redirect=true";
    }

    public String modifyDish() {
        Dish dish = new Dish();
        dish.setId(selectedDish);
        dish.setName(dishName);
        dish.setPrice(Float.valueOf(dishPrice));
        Category category = categoryRepository.getCategory(categoryNumber);
        dish.setCategory(category);
        dishRepository.updateDish(dish);
        clearAll();
        return "/catering_products.xhtml?faces-redirect=true";
    }

    public String processCategoryApply() {
        if (modifyCategory)
            return modifyCategory();
        else
            return addNewCategory();
    }

    public String processDishApply() {
        if (modifyDish)
            return modifyDish();
        else
            return addNewDish();
    }

    public void updateCategoryModifyInput() {
        if (selectedCategory != -1L) {
            Category category = categoryRepository.getCategory(selectedCategory);
            categoryName = category.getName();
        }
    }

    public void updateDishModifyInput() {
        if (selectedDish != -1L) {
            Dish dish = dishRepository.getDish(selectedDish);
            dishName = dish.getName();
            dishPrice = String.valueOf(dish.getPrice());
            categoryNumber = dish.getCategory().getId();
        }
    }

    public String deleteCategory() {
        if (selectedCategory != null && selectedCategory != -1L)
            categoryRepository.deleteCategory(selectedCategory);
        return null;
    }

    public String deleteDish() {
        if (selectedDish != null && selectedDish != -1L) {
            dishRepository.deleteDish(selectedDish);
        }
        return null;
    }

}
