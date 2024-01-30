package com.greemlock.derprojekt.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.greemlock.derprojekt.Adapter.BlogCardAdapter;
import com.greemlock.derprojekt.Objects.Blog;
import com.greemlock.derprojekt.R;

import java.util.ArrayList;

public class BlogFragment extends Fragment {

    private RecyclerView recyclerView;
    private BlogCardAdapter blogCardAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<Blog> blogList = new ArrayList<>();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("blogs");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                blogList.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Blog blog = dataSnapshot.getValue(Blog.class);
                    blogList.add(blog);
                }
                blogCardAdapter = new BlogCardAdapter(getActivity(),blogList);
                recyclerView.setAdapter(blogCardAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recyclerView = getActivity().findViewById(R.id.recyclerViewBlog);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        blogCardAdapter = new BlogCardAdapter(getActivity(),blogList);
        recyclerView.setAdapter(blogCardAdapter);


    }
}