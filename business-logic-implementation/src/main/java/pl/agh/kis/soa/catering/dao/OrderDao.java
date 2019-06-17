package pl.agh.kis.soa.catering.dao;

import pl.agh.kis.soa.catering.model.Order;


public class OrderDao extends AbstractModelDao<Order> {

    private static OrderDao instance;

    private OrderDao() {
        super(Order.class);
    }

    public static OrderDao getInstance() {
        if (instance == null) {
            instance = new OrderDao();
        }
        return instance;
    }

}
