package pl.agh.kis.soa.catering.api;

import pl.agh.kis.soa.catering.model.Order;
import pl.agh.kis.soa.catering.utils.OrderStatus;

import java.util.Date;
import java.util.List;


public interface IOrderRepository {

    void addOrder(Order order);

    Order getOrder(Long orderId);

    List<Object> getAllOrders();

    void updateOrder(Order order);

    void deleteOrder(Long orderId);


    void changeOrderStatus(Long orderId, OrderStatus status);

    List<Object> getAllOrdersByStatus(OrderStatus status);

    List<Object> getUserOrders(Long userId);

    List<Order> getUserOrdersDueToDate(Long userId, Date startDate, Date endDate);

    Object generateBill(Long userId, Date startDate, Date endDate);
}
