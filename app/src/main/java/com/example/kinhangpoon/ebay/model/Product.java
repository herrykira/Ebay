package com.example.kinhangpoon.ebay.model;

/**
 * Created by KinhangPoon on 24/2/2018.
 */

public class Product {
    String productId,productName,productQuantity,productPrize,productDescription,productImageUrl;

    public Product(String productId, String productName, String productQuantity, String productPrize, String productDescription, String productImageUrl) {
        this.productId = productId;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productPrize = productPrize;
        this.productDescription = productDescription;
        this.productImageUrl = productImageUrl;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductPrize() {
        return productPrize;
    }

    public void setProductPrize(String productPrize) {
        this.productPrize = productPrize;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }
}
