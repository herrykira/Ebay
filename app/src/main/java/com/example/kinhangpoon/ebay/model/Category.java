package com.example.kinhangpoon.ebay.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KinhangPoon on 22/2/2018.
 */

public class Category {
    String categoryName,categoryDescription,categoryImageUrl;
    public static List<Category> categoryList = new ArrayList<>();

    public Category(String categoryName, String categoryDescription, String categoryImageUrl) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.categoryImageUrl = categoryImageUrl;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryImageUrl() {
        return categoryImageUrl;
    }

    public void setCategoryImageUrl(String categoryImageUrl) {
        this.categoryImageUrl = categoryImageUrl;
    }
}
