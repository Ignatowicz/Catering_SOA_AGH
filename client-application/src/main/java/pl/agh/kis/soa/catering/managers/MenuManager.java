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

    private List<Dish> dishesOrder;

    private boolean cyclicOrder;
    private String orderDetails;
    private Date orderDeliver;
    private List<Date> cyclicOrderDeliver;
    private Set<Dish> selectedDishesView;
    private int deliverHour;
    private int deliverMinute;

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
            Dish dish = (Dish) dishRepository.getDish(idSelectedDish);
            if (dish != null)
                dishesOrder.add(dish);
        }
    }

    public void removeSelectedDishFromOrder(Dish dish) {
        dishesOrder.remove(dish);
    }

    public Float sumOrderPrice() {
        Float sum = 0.0f;
        for (Dish dish : dishesOrder)
            sum += dish.getPrice();
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
        // TODO
        Set<Subscription> subscriptions = new HashSet<>();
//        for (Date d : cyclicOrderDeliver) {
//            subscriptions.add(new Subscription());
//        }
        Subscription subscription = new Subscription();
        subscription.setUser(userRepository.getUser(userId));
        subscription.setDishes(dishesOrder);
        subscription.setFrequency("week");
        subscriptionRepository.addSubscription(subscription);
        clearAll();
        return redirectToPage("catering_wall");
    }

    public String makeNormalOrder(Long userId) {
        Order order = new Order();
        orderDeliver.setHours(deliverHour);
        orderDeliver.setMinutes(deliverMinute);
        order.setUser(userRepository.getUser(userId));
        order.setStatus(OrderStatus.ORDERED);
        order.setDishes(dishesOrder);
        order.setDate(orderDeliver);
        orderRepository.addOrder(order);
        clearAll();
        return redirectToPage("catering_wall");
    }

    public void approvedDish(Long dishId) {
        dishRepository.acceptDish(dishId);
    }

    public void clearAll() {
        orderDeliver = null;
        orderDetails = null;
        cyclicOrder = false;
        cyclicOrderDeliver.clear();
        selectedDishesView.clear();
        dishesOrder.clear();
        deliverHour = 0;
        deliverMinute = 0;
    }

    public String getChosenDishes(Set<Object> objectSet) {
        if (objectSet != null && objectSet.size() > 0) {
            StringBuilder dishes = new StringBuilder();
            for (Object o : objectSet) {
                Dish dish = (Dish) o;
                dishes.append("* ").append(dish.getName()).append("\n");
            }
            return dishes.toString();
        } else
            return " Brak pozycji";
    }

    public List<Dish> getAllDishesToAccept() {
        return dishRepository.getAllDishesToAccept();
    }

    public static String redirectToPage(String pageName) {
        return "/" + pageName + ".xhtml?faces-redirect=true";
    }

}
