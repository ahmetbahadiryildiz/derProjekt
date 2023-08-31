package com.greemlock.derprojekt.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.greemlock.derprojekt.Objects.Service;
import com.greemlock.derprojekt.R;

import java.util.List;

public class SelectionViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Service> serviceList;

    public SelectionViewAdapter(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewServiceTitle;
        public TextView textViewServiceInfo;
        public TextView textViewServiceCharge;
        public Button buttonBuyService;


        public ViewHolder(@NonNull View itemView){
            super(itemView);
            textViewServiceTitle = itemView.findViewById(R.id.textViewServiceTitle);
            textViewServiceInfo = itemView.findViewById(R.id.textViewServiceInfo);
            textViewServiceCharge = itemView.findViewById(R.id.textViewServiceCharge);
            buttonBuyService = itemView.findViewById(R.id.buttonBuyService);

        }


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Service service = serviceList.get(position);

        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.textViewServiceTitle.setText(service.getServiceTitle());
        viewHolder.textViewServiceInfo.setText(service.getServiceInfo());
        viewHolder.textViewServiceCharge.setText(String.valueOf(service.getServiceCharge()));
    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
