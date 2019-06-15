package pl.agh.kis.soa.catering.implementation;

import lombok.Getter;
import lombok.Setter;
import pl.agh.kis.soa.catering.api.IUserRepository;
import pl.agh.kis.soa.catering.dao.UserDao;
import pl.agh.kis.soa.catering.model.Subscription;
import pl.agh.kis.soa.catering.model.User;
import pl.agh.kis.soa.catering.utils.UserRole;

import javax.ejb.*;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static pl.agh.kis.soa.catering.utils.LoginService.*;


@Getter
@Setter
@Stateless
@Remote(IUserRepository.class)
public class UserRepository implements IUserRepository {

    private User loggedUser;

    @EJB
    private MainManager mainManager;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void register(User user) {
        if (!checkIfExistPasswordOrLogin(hashPassword(user.getPassword()), user.getLogin())) {
            user.setPassword(hashPassword(user.getPassword()));
            UserDao.getInstance().addItem(user);
        }
    }

    @Override
    public User getUser(Long userId) {
        return UserDao.getInstance().getItem(userId);
    }

    @Override
    public User getUser(String login) {
        return UserDao.getInstance().getUserByLogin(login);
    }

    @Override
    public List<Object> getAllUsers() {
        return new ArrayList<>(Arrays.asList(UserDao.getInstance().getItems().toArray()));
    }

    @Override
    public void updateUser(User user) {
        UserDao.getInstance().updateItem(user);
    }

    @Override
    public void deleteUser(Long userId) {
        UserDao.getInstance().deleteItem(userId);
    }

    @Override
    public User getLoggedUser() {
        return loggedUser;
    }

    @Override
    public Boolean login(String login, String password) {
        String hashedPassword = hashPassword(password);
        User user = UserDao.getInstance().login(login, hashedPassword);
        if (user != null) {
            loggedUser = UserDao.getInstance().getUserByLogin(login);
            return true;
        }
        return null;
    }

    @Override
    public Boolean logout() {
        List<User> loggedUsers = mainManager.getLoggedUsers();
        loggedUsers.remove(loggedUser);
        mainManager.setLoggedUsers(loggedUsers);
        loggedUser = null;
        return true;
    }

    @Override
    public Boolean changePassword(String newPassword, Long userId) {
        User user = new User();
        user.setPassword(newPassword);
        UserDao.getInstance().updateItem(user);
        return true;
    }

    @Override
    public Boolean changePasswordByAdmin(String newPassword, Long userId) {
        UserDao.getInstance().getItem(userId).setPassword(hashPassword(newPassword));
        return null;
    }

    @Override
    public UserRole getUserRole(Long userId) {
        User user = UserDao.getInstance().getItem(userId);
        try {
            return user.getRole();
        } catch (NoResultException e) {
            UserDao.getInstance().getItem(userId).setRole(UserRole.CUSTOMER);
            return UserRole.CUSTOMER;
        }
    }

    @Override
    public Set<Subscription> getAllUserSubscriptions(Long userId) {
        User user = UserDao.getInstance().getItem(userId);
        return user.getSubcriptions();
    }

}
