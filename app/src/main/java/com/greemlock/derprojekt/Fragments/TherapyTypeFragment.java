package com.greemlock.derprojekt.Fragments;

import android.os.Bundle;

import androidx.annotation.NavigationRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.greemlock.derprojekt.Adapter.SelectionQuizAdapter;
import com.greemlock.derprojekt.R;
import com.greemlock.derprojekt.databinding.FragmentTherapyTypeBinding;

import java.util.ArrayList;
import java.util.List;

public class TherapyTypeFragment extends Fragment {

    private FragmentTherapyTypeBinding tasarim;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        int therapyTypeSelectionID = -1;

        ArrayList<String> options = new ArrayList<>();
        options.add("Selection 1");
        options.add("Selection 2");
        options.add("Selection 3");



        tasarim = FragmentTherapyTypeBinding.inflate(inflater,container,false);

        tasarim.recyclerViewQuestion1.setHasFixedSize(true);
        tasarim.recyclerViewQuestion1.setLayoutManager(new LinearLayoutManager(getContext()));

        SelectionQuizAdapter selectionQuizAdapter = new SelectionQuizAdapter(options);
        tasarim.recyclerViewQuestion1.setAdapter(selectionQuizAdapter);

        tasarim.buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (therapyTypeSelectionID == -1){

                } else{
                    Navigation.findNavController(view).navigate(R.id.therapy_type_to_user_info);
                }

            }
        });

        return tasarim.getRoot();



    }
}