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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
                return "/catering_products.xhtml?faces-redirect=true";
            } else
                return null;
        } else
            return "/login.xhtml?faces-redirect=true";
    }

    public boolean checkRoleAuthority(String requiredRole) {
        return checkAuthorization(true, requiredRole) == null;
    }

    public String signIn() {
        if (user.getLogin() == null || user.getPassword() == null) {
            FacesContext.getCurrentInstance().addMessage("form:loginButton", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niepoprawny login lub haslo", "Sprawdź dane!"));
            return "/login.xhtml?faces-redirect=true";
        }
        user = userRepository.login(user.getLogin(), user.getPassword());
        if (user != null) {
            loggedUser = (User) userRepository.getLoggedUser();
            System.out.println("zalogowal sie: " + user.getLogin());
            user = new User();
            return "/catering_products.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage("form:loginButton", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niepoprawny login lub haslo", "Sprawdź dane!"));
            return "/login.xhtml?faces-redirect=true";
        }
    }

    public String register() {
        Boolean success = userRepository.register(user);
        user = new User();
        if (success) {
            return "/login.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage("form:registerButton", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nieprawidłowe dane przy rejestracji", "Sprawdź dane!"));
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

}
