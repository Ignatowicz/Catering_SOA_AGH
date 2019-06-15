package pl.agh.kis.soa.catering.api;

import pl.agh.kis.soa.catering.model.Order;
import pl.agh.kis.soa.catering.utils.OrderStatus;

import java.util.Date;
import java.util.List;

public interface IOrderRepository {

    void addOrder(Order order);

    Order getOrder(Long orderId);

    List<Order> getAllOrders();

    void updateOrder(Order order);

    void deleteOrder(Long orderId);


    void changeOrderStatus(Long orderId, OrderStatus status);

    List<Order> getAllOrdersByStatus(OrderStatus status);

    List<Order> getUserOrders(Long userId);

    List<Order> getUserOrderDueToDate(Long userId, Date date);

    Object generateBill(Long orderId, Date date);
}
