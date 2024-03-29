package com.example.java_story_bk;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.java_story_bk.adapters.Viewpager2AdapterMain;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {
    final int dashboard =1;
    final  int follow_stories_page=2;
    final int history_read =3;
    final int account =4;
    private ViewPager2 mViewPager2;
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

        mViewPager2 = findViewById(R.id.viewPager2Main);
        Viewpager2AdapterMain viewpager2AdapterMain = new Viewpager2AdapterMain(this);
        mViewPager2.setAdapter(viewpager2AdapterMain);

        MeowBottomNavigation bottomNavigation = findViewById(R.id.meowBottomNavigation);
        bottomNavigation.add(new MeowBottomNavigation.Model(dashboard, R.drawable.dashboard_icon_black));
        bottomNavigation.add(new MeowBottomNavigation.Model(follow_stories_page, R.drawable.book_love_black));
        bottomNavigation.add(new MeowBottomNavigation.Model(history_read, R.drawable.book_note_black));
        bottomNavigation.add(new MeowBottomNavigation.Model(account, R.drawable.account));
        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handelChangePageController(position+ 1 , bottomNavigation);
            }
        });
        bottomNavigation.setCount(follow_stories_page, "9");
    bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
        @Override
        public Unit invoke(MeowBottomNavigation.Model model) {
            handleChangeItemNavigation(model.getId()-1, mViewPager2);
            return null;
        }
    });
    }


    private void handelChangePageController (int pageIndex,MeowBottomNavigation bottomNavigation) {
        switch (pageIndex) {
            case dashboard : {

                break;
            }
            case  follow_stories_page: {
                break;
            }
            case  history_read: {
                break;
            }
            case  account: {
                break;
            }
        }
        bottomNavigation.show(pageIndex,true);
    }
    private void handleChangeItemNavigation (int pageIndex,ViewPager2 mViewPager2) {
        mViewPager2.setCurrentItem(pageIndex );
    }
}