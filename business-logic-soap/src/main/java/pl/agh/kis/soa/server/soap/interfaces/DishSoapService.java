package pl.agh.kis.soa.server.soap.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
public interface DishSoapService {

    @WebMethod
    void addDishToCategory(
            @WebParam(name = "dishName") String name,
            @WebParam(name = "dishPrice") Float price,
            @WebParam(name = "categoryId") Long categoryId);

}
