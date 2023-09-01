package com.greemlock.derprojekt.Fragments;

import android.os.Bundle;

import androidx.annotation.NavigationRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greemlock.derprojekt.R;
import com.greemlock.derprojekt.databinding.FragmentTherapyTypeBinding;
public class TherapyTypeFragment extends Fragment {

    private FragmentTherapyTypeBinding tasarim;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        tasarim = FragmentTherapyTypeBinding.inflate(inflater,container,false);

        tasarim.buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.therapy_type_to_user_info);
            }
        });

        return tasarim.getRoot();
    }
}