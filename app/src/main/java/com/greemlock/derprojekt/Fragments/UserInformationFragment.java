package com.greemlock.derprojekt.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greemlock.derprojekt.R;
import com.greemlock.derprojekt.databinding.FragmentUserInformationBinding;

public class UserInformationFragment extends Fragment {

    private FragmentUserInformationBinding tasarim;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        tasarim = FragmentUserInformationBinding.inflate(inflater,container,false);
        // Inflate the layout for this fragment
        return tasarim.getRoot();
    }
}