package pl.agh.kis.soa.catering.dao;

import pl.agh.kis.soa.catering.model.AbstractModel;

import javax.persistence.*;
import java.util.List;


public abstract class AbstractModelDao<T extends AbstractModel> {

    protected static final String PERSISTENCE_UNIT_NAME = "Catering-postgreSQL";

    protected EntityManagerFactory emFactory;
    protected EntityManager em;

    private Class specificClass;
    private String className;

    protected AbstractModelDao(Class specificClass) {
        this.specificClass = specificClass;
        this.className = specificClass.getSimpleName();
        emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = emFactory.createEntityManager();
    }

    public List<T> getItems() {
        TypedQuery query = em.createQuery("SELECT data FROM " + className + " data", specificClass);
        return (List<T>) query.getResultList();
    }

    public T getItem(Long itemId) {
        TypedQuery query = em.createQuery("SELECT data FROM " + className + " data WHERE data.id = :id", specificClass);
        query.setParameter("id", itemId);
        return (T) query.getSingleResult();
    }

    public void addItem(T item) {
        try {
            em.persist(item);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void updateItem(T item) {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        em.merge(item);
        em.getTransaction().commit();
    }

    public void deleteItem(Long itemId) {
        T item = getItem(itemId);
        deleteItem(item);
    }

    public void deleteItem(T item) {
        em.remove(em.contains(item) ? item : em.merge(item));
        em.getTransaction().commit();
    }

}
