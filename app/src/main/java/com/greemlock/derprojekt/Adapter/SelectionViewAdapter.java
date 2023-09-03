package com.greemlock.derprojekt.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
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

    private List<Service> serviceList;

    public SelectionViewAdapter(List<Service> serviceList) {
        this.serviceList = serviceList;

    }

    public static class ViewHolderSelection extends RecyclerView.ViewHolder {

        public TextView textViewServiceTitle;
        public TextView textViewServiceInfo;
        public TextView textViewServiceCharge;
        public Button buttonBuyService;


        public ViewHolderSelection(@NonNull View itemView){
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
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.selection_offer_recycler,parent,false);
        return new ViewHolderSelection(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Service service = serviceList.get(position);

        ViewHolderSelection viewHolder = (ViewHolderSelection) holder;
        viewHolder.textViewServiceTitle.setText(service.getServiceTitle());
        viewHolder.textViewServiceInfo.setText(service.getServiceInfo());
        viewHolder.textViewServiceCharge.setText(String.valueOf(service.getServiceCharge()));

        viewHolder.buttonBuyService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }


}
