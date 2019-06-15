package pl.agh.kis.soa.catering.implementation;

import pl.agh.kis.soa.catering.api.ICategoryRepository;
import pl.agh.kis.soa.catering.model.Category;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;


@Stateless
@Remote(ICategoryRepository.class)
public class CategoryRepository implements ICategoryRepository {
    @Override
    public void addCategory(Category category) {

    }

    @Override
    public Category getCategory(Long categoryId) {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return null;
    }

    @Override
    public void updateCategory(Category category) {

    }

    @Override
    public void deleteCategory(Long categoryId) {

    }
}
