package pl.agh.kis.soa.catering.dao;

import pl.agh.kis.soa.catering.model.User;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


public class UserDao extends AbstractModelDao<User> {

    private static UserDao instance;

    private UserDao() {
        super(User.class);
    }

    public synchronized static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    public User login(String username, String password) {
        TypedQuery<User> query = em
                .createQuery("SELECT data FROM User data " +
                        "WHERE data.login = :login " +
                        "AND data.password = :password", User.class);

        query.setParameter("login", username);
        query.setParameter("password", password);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("nie zalogowano");
            return null;
        }
    }

    public User getUserByLogin(String login) {
        Query query = em
                .createQuery("SELECT data FROM User data " +
                        "WHERE data.login =:login", User.class);

        query.setParameter("login", login);

        try {
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("nie zalogowano");
            return null;
        }
    }

}
