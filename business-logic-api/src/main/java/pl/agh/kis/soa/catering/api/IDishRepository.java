package pl.agh.kis.soa.catering.api;

import pl.agh.kis.soa.catering.model.Dish;

import java.util.List;


public interface IDishRepository {

    void addDish(Dish dish);

    Dish getDish(Long dishId);

    List<Object> getAllDishes();

    void updateDish(Dish dish);

    void deleteDish(Long dishId);


    void acceptDish(Long dishId);

    void setDishDay(Long dishId);

    Dish getDishDay();

    List<Object> getTopDishes();

    List<Dish> getAllDishesToAccept();

    List<Object> getAllAcceptedDishes();

    List<Object> getAllCategoryDishes(Long categoryId);

}
