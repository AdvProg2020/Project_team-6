package model.product;

import controller.ProgramManager;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class ProductTest {

    @Test
    public void productTest(){
        ProgramManager.getProgramManagerInstance().createAllProductHashMap();
        ProgramManager.getProgramManagerInstance().createAllCategoriesHashMapForTest();
        Product.nextId = 0;
        Category category = new Category("electrical");
        SubCategory subCategory = new SubCategory("tv");
        category.addSubcategory(subCategory);
        Product product = new Product("tv samsung","electrical","tv");
        Assert.assertEquals(0,product.getId());
        Assert.assertEquals("tv samsung",product.getName());
        product.changeField(Byte.parseByte("1"),"tv LG");
        Assert.assertEquals("tv LG",product.getName());
        product.changeField(Byte.parseByte("2"),"nothing...");
        Assert.assertEquals("nothing...",product.getDescription());
        HashMap<String,String> adInfo = product.getCategoryAdditionalInfo();
        HashMap<String,String> adInfoSub = product.getSubCategoryAdditionalInfo();


    }
}
