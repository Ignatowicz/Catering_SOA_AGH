package pl.agh.kis.soa.server.soap.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface ICateringSoapService {

    @WebMethod
    void addDishToCategory(
            @WebParam(name = "dishName") String name,
            @WebParam(name = "dishPrice") Float price,
            @WebParam(name = "categoryId") Long categoryId);

}
