package pl.agh.kis.soa.catering.implementation;

import lombok.Getter;
import lombok.Setter;
import pl.agh.kis.soa.catering.api.IDishRepository;
import pl.agh.kis.soa.catering.dao.DishDao;
import pl.agh.kis.soa.catering.model.Dish;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.*;


@Getter
@Setter
@Stateless
@Remote(IDishRepository.class)
public class DishRepository implements IDishRepository {

    @Override
    public void addDish(Dish dish) {
        DishDao.getInstance().addItem(dish);
    }

    @Override
    public Dish getDish(Long dishId) {
        return DishDao.getInstance().getItem(dishId);
    }

    @Override
    public List<Object> getAllDishes() {
        return new ArrayList<>(Arrays.asList(DishDao.getInstance().getItems().toArray()));
    }

    @Override
    public void updateDish(Dish dish) {
        DishDao.getInstance().updateItem(dish);
    }

    @Override
    public void deleteDish(Long dishId) {
        DishDao.getInstance().deleteItem(dishId);
    }

    @Override
    public void acceptDish(Long dishId) {
        DishDao.getInstance().getItem(dishId).setAccepted(true);
    }

    @Override
    public void setDishDay(Long dishId) {
        List<Dish> dishes = DishDao.getInstance().getItems();
        dishes.forEach(elem -> {
            if (elem.getDishDay()) {
                elem.setDishDay(false);
                DishDao.getInstance().updateItem(elem);
            }
        });

        Dish dish = DishDao.getInstance().getItem(dishId);
        dish.setDishDay(true);
        DishDao.getInstance().updateItem(dish);
    }

    @Override
    public Dish getDishDay() {
        List<Dish> dishes = DishDao.getInstance().getItems();

        if (dishes.isEmpty())
            return null;

        for (Dish d : dishes) {
            if (d.getDishDay())
                return d;
        }
        return null;
    }

    @Override
    public List<Object> getTopDishes() {
        // TODO
        return null;
    }

    @Override
    public List<Dish> getAllDishesToAccept() {

        if (DishDao.getInstance().getItems().isEmpty()) {
            return null;
        }

        List<Object> objects = new ArrayList<Object>(Arrays.asList(DishDao.getInstance().getItems().stream()
                .filter(o -> o.getAccepted().equals(false)).toArray()));

        List<Dish> dishes = new ArrayList<>();
        for (Object o : objects) {
            dishes.add((Dish) o);
        }

        return dishes;
    }

    @Override
    public List<Object> getAllAcceptedDishes() {
        return new ArrayList<>(Arrays.asList(DishDao.getInstance().getItems().stream()
                .filter(o -> o.getAccepted().equals(true)).toArray()));
    }

    @Override
    public List<Object> getAllCategoryDishes(Long categoryId) {
        return new ArrayList<>(Arrays.asList(DishDao.getInstance().getItems().stream()
                .filter(o -> o.getCategory().getId().equals(categoryId)).toArray()));
    }

}
