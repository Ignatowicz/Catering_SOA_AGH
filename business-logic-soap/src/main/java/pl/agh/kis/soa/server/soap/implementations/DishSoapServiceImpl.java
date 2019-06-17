package pl.agh.kis.soa.server.soap.implementations;


import pl.agh.kis.soa.catering.api.ICategoryRepository;
import pl.agh.kis.soa.catering.api.IDishRepository;
import pl.agh.kis.soa.catering.model.Dish;
import pl.agh.kis.soa.server.soap.interfaces.DishSoapService;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface = "pl.agh.kis.soa.server.soap.interfaces.DishSoapService")
public class DishSoapServiceImpl implements DishSoapService {

    @EJB(lookup = "java:global/business-logic-implementation/DishRepository")
    IDishRepository dishRepository;

    @EJB(lookup = "java:global/business-logic-implementation/CategoryRepository")
    ICategoryRepository categoryRepository;

    @WebMethod
    public void addDishToCategory(String name, Float price, Long categoryId) {
        Dish dish = new Dish();
        dish.setAccepted(false);
        dish.setDishDay(false);
        dish.setName(name);
        dish.setPrice(price);
        dish.setCategory(categoryRepository.getCategory(categoryId));
        dishRepository.addDish(dish);
    }

}
