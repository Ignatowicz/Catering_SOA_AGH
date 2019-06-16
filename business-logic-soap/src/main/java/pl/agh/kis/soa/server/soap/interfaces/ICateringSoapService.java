package pl.agh.kis.soa.server.soap.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.math.BigDecimal;

@WebService
public interface ICateringSoapService {
    @WebMethod
    void addDishToCategory(@WebParam(name = "dishName")String name, @WebParam(name = "dishPrice") BigDecimal price, @WebParam(name = "categoryId")Long categoryId);
}
