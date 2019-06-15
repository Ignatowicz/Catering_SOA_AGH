package pl.agh.kis.soa.catering.implementation;

import pl.agh.kis.soa.catering.api.IUserRepository;
import pl.agh.kis.soa.catering.model.Subscription;
import pl.agh.kis.soa.catering.model.User;
import pl.agh.kis.soa.catering.utils.UserRole;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;


@Stateless
@Remote(IUserRepository.class)
public class UserRepository implements IUserRepository {
    @Override
    public void register(User user) {

    }

    @Override
    public User getUser(Long userId) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public User getUserByLogin(String user) {
        return null;
    }

    @Override
    public User getLoggedUser() {
        return null;
    }

    @Override
    public Boolean login(String login, String password) {
        return null;
    }

    @Override
    public Boolean logout() {
        return null;
    }

    @Override
    public Boolean changePassword(String newPassword, Long userId) {
        return null;
    }

    @Override
    public Boolean changePasswordByAdmin(String newPassword, Long userId) {
        return null;
    }

    @Override
    public UserRole getUserRole(Long userId) {
        return null;
    }

    @Override
    public List<Subscription> getAllUserSubscriptions(Long userId) {
        return null;
    }

    @Override
    public Object generateBill(Long userId, Date date) {
        return null;
    }
}
