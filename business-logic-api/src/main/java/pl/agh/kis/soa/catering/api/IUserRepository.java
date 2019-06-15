package pl.agh.kis.soa.catering.api;

import pl.agh.kis.soa.catering.model.Subscription;
import pl.agh.kis.soa.catering.model.User;
import pl.agh.kis.soa.catering.utils.UserRole;

import java.util.List;
import java.util.Set;


public interface IUserRepository {

    void register(User user);

    User getUser(Long userId);

    User getUser(String login);

    List<Object> getAllUsers();

    void updateUser(User user);

    void deleteUser(Long userId);


    User getLoggedUser();

    Boolean login(String login, String password);

    Boolean logout();

    Boolean changePassword(String newPassword, Long userId);

    Boolean changePasswordByAdmin(String newPassword, Long userId);

    UserRole getUserRole(Long userId);

    Set<Subscription> getAllUserSubscriptions(Long userId);
    
}
