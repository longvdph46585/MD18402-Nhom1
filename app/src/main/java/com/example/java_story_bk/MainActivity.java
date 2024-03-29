package com.example.java_story_bk;

import android.annotation.SuppressLint;
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

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.java_story_bk.fragments.DashboardFragment;
import com.example.java_story_bk.fragments.LoveStoriesFragment;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {
    final int dashboard =1;
    final  int follow_stories_page=2;
    final int history_read =3;
    final int account =4;
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

        @SuppressLint("MissingInflatedId")
        MeowBottomNavigation bottomNavigation = findViewById(R.id.meowBottomNavigation);
        bottomNavigation.add(new MeowBottomNavigation.Model(dashboard, R.drawable.dashboard_icon_black));
        bottomNavigation.add(new MeowBottomNavigation.Model(follow_stories_page, R.drawable.book_love_black));
        bottomNavigation.add(new MeowBottomNavigation.Model(history_read, R.drawable.book_note_black));
        bottomNavigation.add(new MeowBottomNavigation.Model(account, R.drawable.account));

        bottomNavigation.show(dashboard,true);
        replaceFragment(new DashboardFragment());

    bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
        @Override
        public Unit invoke(MeowBottomNavigation.Model model) {
            Toast.makeText(MainActivity.this, model.getId() + "", Toast.LENGTH_SHORT).show();
            switch (model.getId()) {
                case dashboard:{
                    replaceFragment(new DashboardFragment());
                }
                case follow_stories_page:{
                    replaceFragment(new LoveStoriesFragment());


                }
                case history_read:{

                }
                case account:{

                }
            }
            return null;
        }
    });
    bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
        @Override
        public Unit invoke(MeowBottomNavigation.Model model) {
            String name;
            switch (model.getId()) {
                case dashboard:name = "dashboard";

                break;
                case follow_stories_page:name = "follow_stories_page";

                    break;
                case history_read:name = "history_read";

                    break;
                case account:name = "account";
                    break;
            }
            bottomNavigation.setCount(follow_stories_page, "9");
            return null;
        }
    });
    }

    void replaceFragment (Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.wrap_frame_el, fragment);

        fragmentTransaction.commit();
    }
}