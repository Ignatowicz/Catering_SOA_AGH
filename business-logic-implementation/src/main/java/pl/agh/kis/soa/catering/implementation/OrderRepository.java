package pl.agh.kis.soa.catering.implementation;

import lombok.Getter;
import lombok.Setter;
import pl.agh.kis.soa.catering.api.IOrderRepository;
import pl.agh.kis.soa.catering.dao.OrderDao;
import pl.agh.kis.soa.catering.model.Order;
import pl.agh.kis.soa.catering.utils.Bill;
import pl.agh.kis.soa.catering.utils.OrderStatus;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@Stateless
@Remote(IOrderRepository.class)
public class OrderRepository implements IOrderRepository {

    @Override
    public void addOrder(Order order) {
        OrderDao.getInstance().addItem(order);
    }

    @Override
    public Order getOrder(Long orderId) {
        return OrderDao.getInstance().getItem(orderId);
    }

    @Override
    public List<Object> getAllOrders() {
        return new ArrayList<Object>(Arrays.asList(OrderDao.getInstance().getItems().toArray()));
    }

    @Override
    public void updateOrder(Order order) {
        OrderDao.getInstance().updateItem(order);
    }

    @Override
    public void deleteOrder(Long orderId) {
        OrderDao.getInstance().deleteItem(orderId);
    }

    @Override
    public void changeOrderStatus(Long orderId, OrderStatus status) {
        OrderDao.getInstance().getItem(orderId).setStatus(status);
    }

    @Override
    public List<Object> getAllOrdersByStatus(OrderStatus status) {
        return new ArrayList<>(Arrays.asList(OrderDao.getInstance().getItems().stream()
                .filter(o -> o.getStatus().equals(status)).toArray()));
    }

    @Override
    public List<Object> getUserOrders(Long userId) {
        return new ArrayList<>(Arrays.asList(OrderDao.getInstance().getItems().stream()
                .filter(o -> o.getUser().getId().equals(userId)).toArray()));
    }

    @Override
    public List<Order> getUserOrdersDueToDate(Long userId, Date startDate, Date endDate) {
        return OrderDao.getInstance().getItems().stream()
                .filter(o -> o.getDate().after(startDate) && o.getDate().before(endDate))
                .filter(o -> o.getUser().getId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public Object generateBill(Long userId, Date startDate, Date endDate) {
        List<Order> orderList = getUserOrdersDueToDate(userId, startDate, endDate);

        List<String> stringList = new ArrayList<>();
        Bill bill = new Bill();
        orderList.forEach(elem -> {
            elem.getDishes().forEach(dish -> {
                stringList.add(dish.getName());
            });
        });

        bill.setOrderedPosition(stringList);
        bill.setGeneratedDate(new Date());
        bill.setStartDate(startDate);
        bill.setEndDate(endDate);
        bill.setPrice(countAll(orderList));
        if (bill.getPrice().equals(0F)) {
            bill.setAdditionalInformation("Brak zamówień w wybranym terminie.");
        }
        return bill;
    }

    private Float countAll(List<Order> orderList) {
        return (float) orderList.stream().mapToDouble(Order::getPrice).sum();
    }

}
