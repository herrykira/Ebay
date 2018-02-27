package com.example.kinhangpoon.ebay.model;

/**
 * Created by KinhangPoon on 26/2/2018.
 */

public class OrderHistory {
    String OrderID,ItemName,ItemQuantity,FinalPrice,OrderStatus;

    public OrderHistory(String orderID, String itemName, String itemQuantity, String finalPrice, String orderStatus) {
        OrderID = orderID;
        ItemName = itemName;
        ItemQuantity = itemQuantity;
        FinalPrice = finalPrice;
        OrderStatus = orderStatus;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemQuantity() {
        return ItemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        ItemQuantity = itemQuantity;
    }

    public String getFinalPrice() {
        return FinalPrice;
    }

    public void setFinalPrice(String finalPrice) {
        FinalPrice = finalPrice;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }
}
