package model.product;

import controller.ProgramManager;
import org.junit.Assert;
import org.junit.Test;

public class ProductTest {

    @Test
    public void productTest(){
        ProgramManager.getProgramManagerInstance().createAllProductHashMap();
        ProgramManager.getProgramManagerInstance().createAllCategoriesHashMapForTest();
        Category category = new Category("electrical");
        SubCategory subCategory = new SubCategory("tv");
        category.addSubcategory(subCategory);
        Product product = new Product("tv samsung","electrical","tv");
        Assert.assertEquals(0,product.getId());

    }
}
