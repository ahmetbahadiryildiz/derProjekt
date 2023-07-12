package com.greemlock.derprojekt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.greemlock.derprojekt.Adapter.ViewPagerAdapter;
import com.greemlock.derprojekt.Fragments.SessionFragment;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new SessionFragment(),"Session");
        

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(view -> logout());
    }

    public void logout(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(BaseActivity.this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.pop_up_log_out,null);

        Button b_yes = contactPopupView.findViewById(R.id.b_yes);
        Button b_no = contactPopupView.findViewById(R.id.b_no);

        dialogBuilder.setView(contactPopupView);
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();

        b_no.setOnClickListener(view -> dialog.cancel());
        b_yes.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "You logged out!", Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();
            finish();

        });

    }
}