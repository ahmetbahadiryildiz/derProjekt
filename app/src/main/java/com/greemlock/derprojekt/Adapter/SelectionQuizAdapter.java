package com.greemlock.derprojekt.Adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.greemlock.derprojekt.R;

import java.util.List;

public class SelectionQuizAdapter extends RecyclerView.Adapter {

    private List<String> options;
    boolean isClicked = false;


    public SelectionQuizAdapter(List<String> options){
        this.options = options;
    }

    public static class ViewHolderQuiz extends RecyclerView.ViewHolder {

        public TextView textViewSelection;

        public ViewHolderQuiz(@NonNull View itemView){
            super(itemView);
            textViewSelection = itemView.findViewById(R.id.textViewSelection);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.selection_recycler,parent,false);
        return new ViewHolderQuiz(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final String option = options.get(position);
        ViewHolderQuiz viewHolder = (ViewHolderQuiz) holder;
        viewHolder.textViewSelection.setText(option);

        viewHolder.textViewSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }


    @Override
    public int getItemCount() {
        return options.size();
    }


}
