package pl.agh.kis.soa.server.soap.implementations;


import pl.agh.kis.soa.server.soap.interfaces.ICateringSoapService;

import javax.jws.WebService;
import java.math.BigDecimal;

@WebService(endpointInterface = "pl.agh.kis.soa.server.soap.interfaces.CateringService")
public class CateringSoapServiceImpl implements ICateringSoapService {
    @EJB(lookup = "java:global/business-logic-implementation/DishRepository")
    IDishRepository dishRepository;

    public void addDishToCategory(String name, BigDecimal price, Long categoryId) {
        dishRepository.addDish();
    }
}
