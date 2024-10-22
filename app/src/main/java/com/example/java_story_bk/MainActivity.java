package com.example.java_story_bk;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.example.java_story_bk.adapters.Viewpager2AdapterMain;
import com.example.java_story_bk.fragments.AccountFragment;
import com.example.java_story_bk.fragments.DashboardFragment;
import com.example.java_story_bk.fragments.HistoryStoriesFragment;
import com.example.java_story_bk.fragments.LoveStoriesFragment;
import com.example.java_story_bk.fragments.RatingBarShow;
import com.example.java_story_bk.services.AccountService;
import com.example.java_story_bk.services.MainServices;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
BottomNavigationView navigationView;
Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        navigationView=findViewById(R.id.bot);
        navigationView.setOnItemSelectedListener(v-> {
            if(v.getItemId()==R.id.account){
                fragment=new AccountFragment();
                get(fragment);
            }if(v.getItemId()==R.id.dashboard){
                fragment=new DashboardFragment();
                get(fragment);
            }if(v.getItemId()==R.id.history){
                fragment=new HistoryStoriesFragment();
                get(fragment);
            }if(v.getItemId()==R.id.loveStories){
                fragment=new LoveStoriesFragment();
                get(fragment);
            }
            return true;

        });
    }

    public void get(Fragment fragment){
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


   



}