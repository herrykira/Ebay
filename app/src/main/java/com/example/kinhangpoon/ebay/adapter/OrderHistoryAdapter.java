package com.example.kinhangpoon.ebay.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kinhangpoon.ebay.R;
import com.example.kinhangpoon.ebay.model.OrderHistory;

import java.util.List;

/**
 * Created by KinhangPoon on 26/2/2018.
 */

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderHistoryViewHolder> {
    List<OrderHistory> orderHistoryList;
    Context context;

    public OrderHistoryAdapter(List<OrderHistory> orderHistoryList, Context context) {
        this.orderHistoryList = orderHistoryList;
        this.context = context;
    }

    @Override
    public OrderHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_history_item, parent, false);

        return new OrderHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderHistoryViewHolder holder, int position) {
        OrderHistory orderHistory = orderHistoryList.get(position);
        holder.textViewOrderId.setText("OrderID " + orderHistory.getOrderID());
        holder.textViewItemQuantity.setText("Quantity " + orderHistory.getItemQuantity());
        String status = getStatus(orderHistory.getOrderStatus());
        holder.textViewStatus.setText("Status: " + status);
        holder.textViewItemName.setText("Name: " + orderHistory.getItemName());
        holder.textViewPrice.setText("Price: & " + orderHistory.getFinalPrice());
    }

    @Override
    public int getItemCount() {
        return orderHistoryList.size();
    }

    public class OrderHistoryViewHolder extends RecyclerView.ViewHolder {
        TextView textViewOrderId, textViewItemName, textViewItemQuantity, textViewPrice, textViewStatus;

        public OrderHistoryViewHolder(View itemView) {
            super(itemView);
            textViewOrderId = itemView.findViewById(R.id.textView_order_history_id);
            textViewItemName = itemView.findViewById(R.id.textView_order_history_name);
            textViewPrice = itemView.findViewById(R.id.textView_order_history_price);
            textViewItemQuantity = itemView.findViewById(R.id.textView_order_history_quantity);
            textViewStatus = itemView.findViewById(R.id.textView_order_history_status);
        }
    }
    public String getStatus(String num) {
        switch (num) {
            case "1":
                return "Order Confirm";
            case "2":
                return "Order Dispatch";
            case "3":
                return "Order On the Way";
            default:
                return "Order Delivered";
        }

    }
}
