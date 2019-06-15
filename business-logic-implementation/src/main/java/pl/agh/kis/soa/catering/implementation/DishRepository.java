package pl.agh.kis.soa.catering.implementation;

import pl.agh.kis.soa.catering.api.IDishRepository;
import pl.agh.kis.soa.catering.model.Dish;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;


@Stateless
@Remote(IDishRepository.class)
public class DishRepository implements IDishRepository {
    @Override
    public void addDish(Dish dish) {

    }

    @Override
    public Dish getDish(Long dishId) {
        return null;
    }

    @Override
    public List<Dish> getAllDishes() {
        return null;
    }

    @Override
    public void updateDish(Dish dish) {

    }

    @Override
    public void deleteDish(Long dishId) {

    }

    @Override
    public void acceptDish(Long dishId) {

    }

    @Override
    public void setDishDay(Long dishId) {

    }

    @Override
    public Dish getDishDay() {
        return null;
    }

    @Override
    public List<Dish> getTopDishes() {
        return null;
    }

    @Override
    public List<Dish> getAllDishesToAccept() {
        return null;
    }

    @Override
    public List<Dish> getAllAcceptedDishes() {
        return null;
    }

    @Override
    public List<Dish> getAllCategoryDishes(Long categoryId) {
        return null;
    }
}
