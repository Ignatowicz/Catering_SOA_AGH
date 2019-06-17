package pl.agh.kis.soa.catering.managers;

import lombok.Getter;
import lombok.Setter;
import pl.agh.kis.soa.catering.api.*;
import pl.agh.kis.soa.catering.model.Category;
import pl.agh.kis.soa.catering.model.Dish;
import pl.agh.kis.soa.catering.model.Order;
import pl.agh.kis.soa.catering.model.Subscription;
import pl.agh.kis.soa.catering.utils.OrderStatus;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;


@Getter
@Setter
@Named("MenuManager")
@ManagedBean
@SessionScoped
public class MenuManager implements Serializable {

    @EJB(lookup = "java:global/business-logic-implementation/UserRepository")
    private IUserRepository userRepository;

    @EJB(lookup = "java:global/business-logic-implementation/CategoryRepository")
    private ICategoryRepository categoryRepository;

    @EJB(lookup = "java:global/business-logic-implementation/DishRepository")
    private IDishRepository dishRepository;

    @EJB(lookup = "java:global/business-logic-implementation/OrderRepository")
    private IOrderRepository orderRepository;

    @EJB(lookup = "java:global/business-logic-implementation/SubscriptionRepository")
    private ISubscriptionRepository subscriptionRepository;

    private Long idSelectedCategory;
    private Long idSelectedDish;
    private Long idSelectedOrder;
    private Long idSelectedUser;

    private List<Dish> dishesOrder;

    private boolean cyclicOrder;
    private Date orderDeliver;
    private List<Date> cyclicOrderDeliver;
    private Set<Dish> selectedDishesView;
    private Set<Order> selectedOrderView;
    private int deliverHour;
    private int deliverMinute;

    private Date startDeliver;
    private Date endDeliver;

    private final OrderStatus ORDERED = OrderStatus.ORDERED;
    private final OrderStatus READY = OrderStatus.READY;
    private final OrderStatus SUPPLIED = OrderStatus.SUPPLIED;
    private final OrderStatus PAID = OrderStatus.PAID;

    public MenuManager() {
        dishesOrder = new ArrayList<Dish>();
        cyclicOrderDeliver = new ArrayList<Date>();
        selectedDishesView = new HashSet<Dish>();
        cyclicOrder = false;
    }

    public List<Object> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    public void filterDishes() {
        if (idSelectedCategory != null) {
            System.out.println((categoryRepository.getCategory(idSelectedCategory)).getName());
            Category category = categoryRepository.getCategory(idSelectedCategory);
            if (category == null)
                selectedDishesView.clear();
            else {
                selectedDishesView = category.getDishes();
            }
        } else
            selectedDishesView.clear();
    }

    public void addSelectedDishToOrder() {
        if (idSelectedDish != null) {
            Dish dish = dishRepository.getDish(idSelectedDish);
            if (dish != null)
                dishesOrder.add(dish);
        }
    }

    public void removeSelectedDishFromOrder(Dish dish) {
        dishesOrder.remove(dish);
    }

    public Float sumOrderPrice() {
        Float sum = 0.0f;
        for (Dish dish : dishesOrder) {
            sum += dish.getPrice();
        }
        return sum;
    }

    public void addCyclicDates() throws IOException {
        if (orderDeliver != null) {
            orderDeliver.setHours(deliverHour);
            orderDeliver.setMinutes(deliverMinute);
            cyclicOrderDeliver.add(orderDeliver);
        }
    }

    public void removeDateFromCyclicOrder(Date dt) {
        if (dt != null)
            cyclicOrderDeliver.remove(dt);
    }

    public String makeOrder(Long userID) {
        if (cyclicOrder)
            return makeCyclicOrder(userID);
        return makeNormalOrder(userID);

    }

    public String makeCyclicOrder(Long userId) {
        Date orderDate = new Date();
        orderDate.setHours(deliverHour);
        orderDate.setMinutes(deliverMinute);

        Subscription subscription = new Subscription();
        subscription.setUser(userRepository.getUser(userId));
        subscription.setDishes(dishesOrder);
        subscription.setFrequency("day");
        subscriptionRepository.addSubscription(subscription);

        Calendar c = Calendar.getInstance();
        c.setTime(startDeliver);
        int start = c.get(Calendar.DAY_OF_MONTH);
        c.setTime(endDeliver);
        int end = c.get(Calendar.DAY_OF_MONTH);


        for (int i = start; i< end; i++) {
            orderDeliver = orderDate;
            completeOrder(userId);
        }

        clearAll();
        return redirectToPage("catering_wall");
    }

    public String makeNormalOrder(Long userId) {
        orderDeliver.setHours(deliverHour);
        orderDeliver.setMinutes(deliverMinute);

        completeOrder(userId);
        clearAll();
        return redirectToPage("catering_wall");
    }

    private void completeOrder(Long userId) {
        Order order = new Order();
        order.setUser(userRepository.getUser(userId));
        order.setStatus(OrderStatus.ORDERED);
        order.setDishes(dishesOrder);
        order.setDate(orderDeliver);
        order.setPrice(sumOrderPrice());
        orderRepository.addOrder(order);
    }

    public void approvedDish(Long dishId) {
        dishRepository.acceptDish(dishId);
    }

    public void clearAll() {
        orderDeliver = null;
        cyclicOrder = false;
        cyclicOrderDeliver.clear();
        selectedDishesView.clear();
        dishesOrder.clear();
        deliverHour = 0;
        deliverMinute = 0;
    }

    public String getChosenDishes(Set<Dish> dishes) {
        if (dishes != null && dishes.size() > 0) {
            StringBuilder dishesToStr = new StringBuilder();
            for (Dish dish : dishes) {
                dishesToStr.append("* ").append(dish.getName());
            }
            return dishesToStr.toString();
        } else
            return " Brak pozycji";
    }

    public List<Dish> getAllDishesToAccept() {
        return dishRepository.getAllDishesToAccept();
    }

    public static String redirectToPage(String pageName) {
        return "/" + pageName + ".xhtml?faces-redirect=true";
    }

    public String cancelOrder(Long orderId) {
        orderRepository.deleteOrder(orderId);
        return redirectToPage("user_panel");
    }

    public String cancelSubscription(Long subscriptionId) {
        subscriptionRepository.deleteSubscription(subscriptionId);
        return redirectToPage("user_panel");
    }

    public void prepare() {
        Order order = orderRepository.getOrder(idSelectedOrder);
        order.setStatus(READY);
        orderRepository.updateOrder(order);
    }

    public void deliver() {
        Order order = orderRepository.getOrder(idSelectedOrder);
        order.setStatus(SUPPLIED);
        orderRepository.updateOrder(order);
    }

    public void setPaid() {
        Order order = orderRepository.getOrder(idSelectedOrder);
        order.setStatus(PAID);
        orderRepository.updateOrder(order);
    }

    public String setDishDay(Long dishId) {
        dishRepository.setDishDay(dishId);
        return redirectToPage("catering_wall");
    }

    public String getDishDay() {
        Dish dish = dishRepository.getDishDay();
        if (dish == null)
            return "Pozycja dnia nie została jeszcze wybrana!";
        else {
            return dish.getName() + " za " + dish.getPrice() + "zł";
        }
    }

}
