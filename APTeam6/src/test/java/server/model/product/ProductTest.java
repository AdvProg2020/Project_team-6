package server.model.product;

import server.controller.ProgramManager;
import org.junit.Test;

public class ProductTest {

    @Test
    public void productTest(){
        ProgramManager.getProgramManagerInstance().createAllProductHashMap();
        ProgramManager.getProgramManagerInstance().createAllCategoriesHashMapForTest();
        Product.nextId = 0;
        //Category category = new Category("electrical");
        //SubCategory subCategory = new SubCategory("tv");
        //category.addSubcategory(subCategory);
        /*Product product = new Product("tv samsung","electrical","tv");
        Assert.assertEquals(0,product.getId());
        Assert.assertEquals("tv samsung",product.getName());
        product.changeField(Byte.parseByte("1"),"tv LG");
        Assert.assertEquals("tv LG",product.getName());
        product.changeField(Byte.parseByte("2"),"nothing...");
        Assert.assertEquals("nothing...",product.getDescription());
        HashMap<String,String> adInfo = product.getCategoryAdditionalInfo();
        HashMap<String,String> adInfoSub = product.getSubCategoryAdditionalInfo();
        Assert.assertEquals("electrical",product.getCategoryName());
        Assert.assertEquals("tv",product.getSubCategoryName());
        Assert.assertEquals(0,product.getVisitCount());
        LocalDateTime dateTime = product.getCreationDate();
        Score score = new Score("ali",Byte.parseByte("20"),product.getId());
        product.createAllArrayLists();
        product.addScore(score);
        Assert.assertEquals(1,product.getScores().size());
        Assert.assertEquals(20,product.getScores().get(0).getScore());
        Assert.assertEquals(product.getId(),product.getScores().get(0).getProductId());
        Assert.assertEquals("ali",score.getUsername());
        Comment comment = new Comment("ali",product.getId(),"not bad","its very costly",true);
*/


    }
}
