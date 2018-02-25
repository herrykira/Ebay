package com.example.kinhangpoon.ebay.model;

/**
 * Created by KinhangPoon on 23/2/2018.
 */

public class SubCategory {
    String subCategoryId, subCategoryName,subCategoryDescription,subCategoryImageUrl;

    public SubCategory(String subCategoryId, String subCategoryName, String subCategoryDescription, String subCategoryImageUrl) {
        this.subCategoryId = subCategoryId;
        this.subCategoryName = subCategoryName;
        this.subCategoryDescription = subCategoryDescription;
        this.subCategoryImageUrl = subCategoryImageUrl;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getSubCategoryDescription() {
        return subCategoryDescription;
    }

    public void setSubCategoryDescription(String subCategoryDescription) {
        this.subCategoryDescription = subCategoryDescription;
    }

    public String getSubCategoryImageUrl() {
        return subCategoryImageUrl;
    }

    public void setSubCategoryImageUrl(String subCategoryImageUrl) {
        this.subCategoryImageUrl = subCategoryImageUrl;
    }
}
