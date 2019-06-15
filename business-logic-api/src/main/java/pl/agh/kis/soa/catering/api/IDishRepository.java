package pl.agh.kis.soa.catering.api;

import pl.agh.kis.soa.catering.model.Dish;

import java.util.List;

public interface IDishRepository {

    void addDish(Dish dish);

    Dish getDish(Long dishId);

    List<Dish> getAllDishes();

    void updateDish(Dish dish);

    void deleteDish(Long dishId);


    void acceptDish(Long dishId);

    void setDishDay(Long dishId);

    Dish getDishDay();

    List<Dish> getTopDishes();

    List<Dish> getAllDishesToAccept();

    List<Dish> getAllAcceptedDishes();

    List<Dish> getAllCategoryDishes(Long categoryId);

}
