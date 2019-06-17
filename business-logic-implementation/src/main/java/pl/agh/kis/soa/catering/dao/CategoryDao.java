package pl.agh.kis.soa.catering.dao;

import pl.agh.kis.soa.catering.model.Category;

import javax.persistence.Persistence;


public class CategoryDao extends AbstractModelDao<Category> {

    private static CategoryDao instance;

    private CategoryDao() {
        super(Category.class);
    }

    public static CategoryDao getInstance() {
        if (instance == null) {
            instance = new CategoryDao();
        }
        return instance;
    }

    public void reinitFactory() {
        emFactory = null;
        em = null;
        emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = emFactory.createEntityManager();
    }

}
