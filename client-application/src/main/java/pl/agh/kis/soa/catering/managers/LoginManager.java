package pl.agh.kis.soa.catering.managers;

import lombok.Getter;
import lombok.Setter;
import pl.agh.kis.soa.catering.api.*;
import pl.agh.kis.soa.catering.model.User;
import pl.agh.kis.soa.catering.utils.InitDatabase;
import pl.agh.kis.soa.catering.utils.UserRole;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@Named("LoginManager")
@ManagedBean
@SessionScoped
public class LoginManager implements Serializable {

    @EJB(lookup = "java:global/business-logic-implementation/UserRepository")
    private IUserRepository userRepository;

    @EJB
    private MainManager mainManager;

    private User user;

    private User loggedUser;


    private LoginManager() {
        user = new User();
    }

    public String checkAuthorization(boolean enableRequiredRole, String requiredRole) {
        if (loggedUser != null) {
            if (enableRequiredRole) {
                switch (UserRole.valueOf(requiredRole)) {
                    case CUSTOMER:
                        return null;
                    case ADMIN:
                        if (loggedUser.getRole() == UserRole.ADMIN)
                            return null;
                    case EMPLOYEE:
                        if (loggedUser.getRole() == UserRole.EMPLOYEE || loggedUser.getRole() == UserRole.ADMIN || loggedUser.getRole() == UserRole.MANAGER)
                            return null;
                    case MANAGER:
                        if (loggedUser.getRole() == UserRole.MANAGER || loggedUser.getRole() == UserRole.ADMIN)
                            return null;
                    case SUPPLIER:
                        if (loggedUser.getRole() == UserRole.SUPPLIER || loggedUser.getRole() == UserRole.ADMIN || loggedUser.getRole() == UserRole.MANAGER)
                            return null;
                }
                return "/catering_wall.xhtml?faces-redirect=true";
            } else
                return null;
        } else
            return "/login.xhtml?faces-redirect=true";
    }

    public boolean checkRoleAuthority(String requiredRole) {
        return checkAuthorization(true, requiredRole) == null;
    }

    public String signIn() {
        User user1 = userRepository.login(user.getLogin(), user.getPassword());
        if (user1 != null) {
            loggedUser = userRepository.getLoggedUser();
            System.out.println("zalogowal sie: " + user1.getLogin());
            user = new User();
            return "/catering_wall.xhtml?faces-redirect=true";
        } else {
            user = new User();
            return "/login.xhtml?faces-redirect=true";
        }
    }

    public String register() {
        Boolean success = userRepository.register(user);
        user = new User();
        if (success) {
            return "/login.xhtml?faces-redirect=true";
        } else {
            return "/login.xhtml?faces-redirect=true";
        }
    }

    public String logout() {
        List<User> loggedUsers = mainManager.getLoggedUsers();
        loggedUsers.remove(loggedUser);
        mainManager.setLoggedUsers(loggedUsers);
        loggedUser = null;
        userRepository.logout();
        return "/login.xhtml?faces-redirect=true";
    }

    public static void initDB() {
//        InitDatabase.getInstance();
//        InitDatabase initDatabase = new InitDatabase();
    }
}
