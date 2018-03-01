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
/**
 * *Order History Adapter: puts data to the order history recyclerView
 */
public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderHistoryViewHolder> {
    /**
     * Declaration for context and interface
     */
    List<OrderHistory> orderHistoryList;
    Context context;

    /**
     * Constructor for OrderHistory
     * @param orderHistoryList
     * @param context
     */
    public OrderHistoryAdapter(List<OrderHistory> orderHistoryList, Context context) {
        this.orderHistoryList = orderHistoryList;
        this.context = context;
    }

    @Override
    public OrderHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         * initialize view
         */
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_history_item, parent, false);

        return new OrderHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderHistoryViewHolder holder, int position) {
        /**
         * get Item from orderhistory list
         */
        OrderHistory orderHistory = orderHistoryList.get(position);
        /**
         * assigns values for items in viewholder
         */
        holder.textViewOrderId.setText("OrderID " + orderHistory.getOrderID());
        holder.textViewItemQuantity.setText("Quantity " + orderHistory.getItemQuantity());
        String status = getStatus(orderHistory.getOrderStatus());
        holder.textViewStatus.setText("Status: " + status);
        holder.textViewItemName.setText("Name: " + orderHistory.getItemName());
        holder.textViewPrice.setText("Price: $ " + orderHistory.getFinalPrice());
    }

    @Override
    public int getItemCount() {
        return orderHistoryList.size();
    }
    /**
     * Define ViewHolder for order history page
     */
    public class OrderHistoryViewHolder extends RecyclerView.ViewHolder {
        /**
         * Declaration
         */
        TextView textViewOrderId, textViewItemName, textViewItemQuantity, textViewPrice, textViewStatus;

        public OrderHistoryViewHolder(View itemView) {
            super(itemView);
            /**
             * initialization
             */
            textViewOrderId = itemView.findViewById(R.id.textView_order_history_id);
            textViewItemName = itemView.findViewById(R.id.textView_order_history_name);
            textViewPrice = itemView.findViewById(R.id.textView_order_history_price);
            textViewItemQuantity = itemView.findViewById(R.id.textView_order_history_quantity);
            textViewStatus = itemView.findViewById(R.id.textView_order_history_status);
        }
    }

    /**
     * get status information based on number
     * @param num
     * @return
     */
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
