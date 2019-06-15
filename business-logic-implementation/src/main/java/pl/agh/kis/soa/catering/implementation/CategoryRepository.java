package pl.agh.kis.soa.catering.implementation;

import lombok.Getter;
import lombok.Setter;
import pl.agh.kis.soa.catering.api.ICategoryRepository;
import pl.agh.kis.soa.catering.dao.CategoryDao;
import pl.agh.kis.soa.catering.model.Category;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Getter
@Setter
@Stateless
@Remote(ICategoryRepository.class)
public class CategoryRepository implements ICategoryRepository {

    @Override
    public void addCategory(Category category) {
        CategoryDao.getInstance().addItem(category);
    }

    @Override
    public Category getCategory(Long categoryId) {
        return CategoryDao.getInstance().getItem(categoryId);
    }

    @Override
    public List<Object> getAllCategories() {
        return new ArrayList<>(Arrays.asList(CategoryDao.getInstance().getItems().toArray()));
    }

    @Override
    public void updateCategory(Category category) {
        CategoryDao.getInstance().updateItem(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        CategoryDao.getInstance().deleteItem(categoryId);
    }

}
