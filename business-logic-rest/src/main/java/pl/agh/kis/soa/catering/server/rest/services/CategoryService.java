package pl.agh.kis.soa.catering.server.rest.services;


import io.swagger.annotations.Api;
import pl.agh.kis.soa.catering.api.ICategoryRepository;

import javax.ejb.EJB;
import javax.ws.rs.*;
import java.util.List;


@Path("category")
@Api(value = "category", description = "Retrieving dish categories")
public class CategoryService {

    @EJB(lookup = "java:global/business-logic-implementation/CategoryRepository")
    ICategoryRepository categoryRepository;

    @GET
    @Produces({"application/xml", "application/json"})
    public List<pl.agh.kis.soa.catering.model.Category> getAllCategories(@HeaderParam("Accept-Language") String language) {
        if ("en".equals(language)) {
            return getAllCategoriesEN();
        }
        return getAllCategoriesPL();
    }

    @GET
    @Produces({"application/xml", "application/json"})
    @Path("{menuCategoryId}")
    public pl.agh.kis.soa.catering.model.Category getCategoryById(@PathParam("menuCategoryId") Long menuCategoryId) {
        return categoryRepository.getCategory(menuCategoryId);
    }

    @SuppressWarnings("unchecked")
    private List<pl.agh.kis.soa.catering.model.Category> getAllCategoriesPL() {
        return (List<pl.agh.kis.soa.catering.model.Category>) (Object) categoryRepository.getAllCategories();
    }

    @SuppressWarnings("unchecked")
    private List<pl.agh.kis.soa.catering.model.Category> getAllCategoriesEN() {
        List<pl.agh.kis.soa.catering.model.Category> categories = (List<pl.agh.kis.soa.catering.model.Category>) (Object) categoryRepository.getAllCategories();

        for (pl.agh.kis.soa.catering.model.Category category : categories)
            category.setName(category.getName() + "EN");

        return categories;
    }

}
