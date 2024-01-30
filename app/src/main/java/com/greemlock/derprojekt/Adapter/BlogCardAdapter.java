package com.greemlock.derprojekt.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.greemlock.derprojekt.Objects.Blog;
import com.greemlock.derprojekt.R;

import java.io.File;
import java.util.List;

public class BlogCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Blog> blogList;

    public BlogCardAdapter(Context context, List<Blog> blogList){
        this.context = context;
        this.blogList = blogList;
    }

    class BlogCardHolder2 extends RecyclerView.ViewHolder{
        private TextView textViewBlogTitle, textViewBlogSubtitle;
        private ImageView imageViewBlog;
        public ConstraintLayout constraintLayoutBlog;

        BlogCardHolder2(View itemView){
            super(itemView);
            textViewBlogTitle = itemView.findViewById(R.id.textViewBlogTitle);
            textViewBlogSubtitle = itemView.findViewById(R.id.textViewBlogSubtite);
            imageViewBlog = itemView.findViewById(R.id.imageViewBlog);
            constraintLayoutBlog = itemView.findViewById(R.id.constraintLayoutBlog);
        }

    }

    @NonNull
    @Override
    public BlogCardHolder2 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.blog_post,viewGroup,false);
        return new BlogCardHolder2(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Blog blog = blogList.get(position);

        BlogCardHolder2 blogCardHolder2= (BlogCardHolder2) holder;
        blogCardHolder2.textViewBlogTitle.setText(blog.getBlogTitle());
        blogCardHolder2.textViewBlogSubtitle.setText(blog.getBlogSubtitle());

        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference blogPhoto = storageReference.child("blogPhotos/"+ blog.getBlogKey());

        try{
            File file = File.createTempFile("images","jpg");
            blogPhoto.getFile(file).addOnSuccessListener(taskSnapshot -> {
                String filePath = file.getPath();
                Bitmap bitmap = BitmapFactory.decodeFile(filePath);
                blogCardHolder2.imageViewBlog.setImageBitmap(bitmap);
            });
        }
        catch (Exception e){
            Log.e("error",e.getLocalizedMessage());
        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
