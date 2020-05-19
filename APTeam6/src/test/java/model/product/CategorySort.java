package model.product;

import controller.ProgramManager;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class CategorySort {
    @Test
    public void categoryTest() {
        ProgramManager.getProgramManagerInstance().createAllCategoriesHashMapForTest();
        Category category = new Category("electrical");
        Category category1 = new Category("toys");
        category.addField("power");
        category.addField("aaa");
        category.removeField("aaa");
        category.addSubcategory(new SubCategory("High_consumption"));
        category.addSubcategory(new SubCategory("Low_consumption"));
        category.addSubcategory(new SubCategory("without_consumption"));
        category.removeSubcategory(category.getSubCategoryByName("without_consumption"));
        Assert.assertEquals("electrical", category.getName());
        category.removeSubcategory(new SubCategory("sss"));
        ArrayList<Category> categoryArrayList = new ArrayList<>();
        categoryArrayList.add(category);
        categoryArrayList.add(category1);
        Assert.assertEquals(categoryArrayList, Category.sortCategory(1));

        SubCategory subCategory = new SubCategory("tv");
        SubCategory subCategory1 = new SubCategory("pc");
        category1.addSubcategory(subCategory);
        category1.addSubcategory(subCategory1);
        category1.removeSubcategory(subCategory1);
        System.out.println(category1.getAllSubCategories().size());
        if (category1.getAllSubCategories().size() == 1) {
            Assert.assertEquals(subCategory, category1.getAllSubCategories().get(0));
        } else {
            Assert.fail();
        }
        subCategory.addField("size");
        subCategory.addField("sizz");
        subCategory.removeField("sizz");
        Product product = new Product("tv", "electrical", "tv");
        Product product1 = new Product("tvvvv", "electrical", "tv");
        subCategory.addProduct(product.getId());
        subCategory.addProduct(product1.getId());
        subCategory.removeProduct(product1.getId());


    }

}
