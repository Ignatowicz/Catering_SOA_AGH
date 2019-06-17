package pl.agh.kis.soa.catering.dao;

import pl.agh.kis.soa.catering.model.Dish;


public class DishDao extends AbstractModelDao<Dish> {

    private static DishDao instance;

    private DishDao() {
        super(Dish.class);
    }

    public static DishDao getInstance() {
        if (instance == null) {
            instance = new DishDao();
        }
        return instance;
    }

}
