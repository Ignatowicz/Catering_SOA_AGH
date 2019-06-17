package pl.agh.kis.soa.catering.utils;

import pl.agh.kis.soa.catering.api.*;
import pl.agh.kis.soa.catering.model.*;

import javax.ejb.EJB;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class InitDatabase {

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

    private static InitDatabase instance;

    private User admin;
    private User manager;
    private User employee;
    private User supplier;
    private User customer;

    private Category breakfasts;
    private Category soups;
    private Category platsPrincipal;
    private Category vege;
    private Category desserts;
    private Category beverages;

    private Dish schabowy;
    private Dish rosol;
    private Dish szarlotka;

    private InitDatabase() {

    }

    public static InitDatabase getInstance() {
        if (instance == null) {
            instance = new InitDatabase();
            instance.init();
        }
        return instance;
    }

    private void init() {
        System.out.println();
        initUsers();
        initCategories();
        initDishes();
        initOrders();
        initSubscriptions();
    }

    private void initUsers() {
        admin = new User();
        admin.setName("a");
        admin.setSurname("a");
        admin.setLogin("a");
        admin.setPassword("d");
        admin.setRole(UserRole.ADMIN);
        userRepository.register(admin);

        manager = new User();
        manager.setName("m");
        manager.setSurname("m");
        manager.setLogin("m");
        manager.setPassword("p");
        manager.setRole(UserRole.MANAGER);
        userRepository.register(manager);

        employee = new User();
        employee.setName("e");
        employee.setSurname("e");
        employee.setLogin("e");
        employee.setPassword("h");
        employee.setRole(UserRole.EMPLOYEE);
        userRepository.register(employee);

        supplier = new User();
        supplier.setName("a");
        supplier.setSurname("a");
        supplier.setLogin("a");
        supplier.setPassword("d");
        supplier.setRole(UserRole.SUPPLIER);
        userRepository.register(supplier);

        customer = new User();
        customer.setName("a");
        customer.setSurname("a");
        customer.setLogin("a");
        customer.setPassword("d");
        customer.setRole(UserRole.CUSTOMER);
        userRepository.register(customer);

    }

    private void initCategories() {
        breakfasts = new Category();
        breakfasts.setName("śniadania");
        categoryRepository.addCategory(breakfasts);

        soups = new Category();
        soups.setName("zupy");
        categoryRepository.addCategory(soups);

        platsPrincipal = new Category();
        platsPrincipal.setName("drugie dania");
        categoryRepository.addCategory(platsPrincipal);

        vege = new Category();
        vege.setName("vege");
        categoryRepository.addCategory(vege);

        desserts = new Category();
        desserts.setName("desery");
        categoryRepository.addCategory(desserts);

        beverages = new Category();
        beverages.setName("napoje");
        categoryRepository.addCategory(beverages);
    }

    private void initDishes() {
        schabowy = new Dish();
        schabowy.setName("Schabowy");
        schabowy.setCategory(platsPrincipal);
        schabowy.setDishDay(true);
        schabowy.setAccepted(false);
        schabowy.setPrice(12.0F);
        dishRepository.addDish(schabowy);

        rosol = new Dish();
        rosol.setName("Rosół");
        rosol.setCategory(soups);
        rosol.setDishDay(false);
        rosol.setAccepted(false);
        rosol.setPrice(7.0F);
        dishRepository.addDish(rosol);

        szarlotka = new Dish();
        szarlotka.setName("Szarlotka");
        szarlotka.setCategory(desserts);
        szarlotka.setDishDay(false);
        szarlotka.setAccepted(false);
        szarlotka.setPrice(9.0F);
        dishRepository.addDish(szarlotka);
    }

    private void initOrders() {
        Order order1 = new Order();
        order1.setDate(new Date());
        order1.setPrice(20.0F);
        order1.setDishes(new ArrayList<Dish>(Arrays.asList(szarlotka, rosol)));
        order1.setStatus(OrderStatus.ORDERED);
        order1.setUser(customer);
        orderRepository.addOrder(order1);

        Order order2 = new Order();
        order2.setDate(new Date());
        order2.setPrice(23.0F);
        order2.setDishes(new ArrayList<Dish>(Arrays.asList(rosol, schabowy)));
        order2.setStatus(OrderStatus.ORDERED);
        order2.setUser(customer);
        orderRepository.addOrder(order2);
    }

    private void initSubscriptions() {
        Subscription subscription = new Subscription();
        subscription.setUser(customer);
        subscription.setDishes(new ArrayList<Dish>(Arrays.asList(rosol, schabowy)));
        subscription.setFrequency("week");
        subscriptionRepository.addSubscription(subscription);
    }

}
