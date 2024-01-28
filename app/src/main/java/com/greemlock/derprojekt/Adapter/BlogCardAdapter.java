package com.greemlock.derprojekt.Adapter;

import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.greemlock.derprojekt.R;

public class BlogCardAdapter extends RecyclerView.Adapter<BlogCardAdapter.BlogCardHolder2> {
    class BlogCardHolder2 extends RecyclerView.ViewHolder{
        private TextView textViewBlogTitle, textViewBlogSubtitle;
        private ImageView imageViewBlog;

        BlogCardHolder2(View itemView){
            super(itemView);
            textViewBlogTitle = itemView.findViewById(R.id.textViewBlogTitle);
            textViewBlogSubtitle = itemView.findViewById(R.id.textViewBlogSubtite);
            imageViewBlog = itemView.findViewById(R.id.imageViewBlog);
        }

    }
}
