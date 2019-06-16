package pl.agh.kis.soa.catering.managers;


import lombok.Getter;
import lombok.Setter;
import pl.agh.kis.soa.catering.api.*;
import pl.agh.kis.soa.catering.model.Subscription;
import pl.agh.kis.soa.catering.utils.Bill;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Named("UserManager")
@ManagedBean
@SessionScoped
public class UserManager implements Serializable {

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

    private String passwordChanged;
    private Long changePasswordUser;
    private String changeSelectedPassword;

    private Date billStartDate;
    private Date billEndDate;
    private Bill bill;

    public UserManager() {
    }

    public String changePassword(Long userId) {
        if (passwordChanged != null) {
            changePasswordUser = userId;
            userRepository.changePasswordByAdmin(passwordChanged, userId);
            passwordChanged = null;
            changePasswordUser = -1L;
            userRepository.logout();
            return "/login.xhtml";
        }
        return null;
    }

    public String changePasswordByAdmin() {
        if (passwordChanged != null && changePasswordUser != -1L) {
            userRepository.changePasswordByAdmin(passwordChanged, changePasswordUser);
            return null;
        } else {
            return null;
        }
    }

    public List<Object> getUsersByAdmin() {
        return userRepository.getAllUsers();
    }

    public String generateBill(Long userId) {
        this.bill = (Bill) orderRepository.generateBill(userId, billStartDate, billEndDate);
        if (bill != null)
            return "/bill.xhtml";
        return "";
    }

    public String printBill() {
        return "";
    }

    public String getBillDishes() {
        String positions = "";
        for (String o : bill.getOrderedPosition()) {
            positions += "*" + o + "\n";
        }
        return positions;
    }

    public String getDateFromSet(Set<Subscription> dates) {
        StringBuilder out = new StringBuilder();
        for (Subscription d : dates) {
            out.append(d.getFrequency()).append(" * ");
        }
        return out.toString();
    }

}
