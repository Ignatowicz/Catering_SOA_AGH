package pl.agh.kis.soa.catering.implementation;

import pl.agh.kis.soa.catering.api.IOrderRepository;
import pl.agh.kis.soa.catering.model.Order;
import pl.agh.kis.soa.catering.utils.OrderStatus;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;


@Stateless
@Remote(IOrderRepository.class)
public class OrderRepository implements IOrderRepository {

    @Override
    public void addOrder(Order order) {

    }

    @Override
    public Order getOrder(Long orderId) {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }

    @Override
    public void updateOrder(Order order) {

    }

    @Override
    public void deleteOrder(Long orderId) {

    }

    @Override
    public void changeOrderStatus(Long orderId, OrderStatus status) {

    }

    @Override
    public List<Order> getAllOrdersByStatus(OrderStatus status) {
        return null;
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return null;
    }

    @Override
    public List<Order> getUserOrderDueToDate(Long userId, Date date) {
        return null;
    }

    @Override
    public Object generateBill(Long orderId, Date date) {
        return null;
    }
}
