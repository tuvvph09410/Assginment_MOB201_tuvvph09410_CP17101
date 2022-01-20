package com.example.assginment_mob201.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.assginment_mob201.Adapter.ItemMenuAdapter;
import com.example.assginment_mob201.Entity.ItemMenu;
import com.example.assginment_mob201.Fragment.Fragment_Course;
import com.example.assginment_mob201.Fragment.Fragment_Maps;
import com.example.assginment_mob201.Fragment.Fragment_News;
import com.example.assginment_mob201.Fragment.Fragment_Social;
import com.example.assginment_mob201.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ImageButton ibtnMenu, ibtnBack;
    private TextView tvAppName;
    private View viewIclude;
    private NavigationView navigationView;
    private ListView lvItemMenu;
    private List<ItemMenu> itemMenuList;
    private ItemMenuAdapter itemMenuAdapter;

    private static final int FRAGMENT_COURSE = 0;
    private static final int FRAGMENT_MAPS = 1;
    private static final int FRAGMENT_NEWS = 2;
    private static final int FRAGMENT_SOCIAL = 3;
    private int currentFragment = FRAGMENT_COURSE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ánh xạ
        this.initViewByID();

        //khởi tạo toolbar
        this.initToolbar();

        //khởi tạo drawerItem
        this.initItemDrawer();

        //khi click item trên drawer chuyển sang fragment khác
        this.clickItemDrawer();

    }

    private void clickItemDrawer() {
        this.lvItemMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // khi nằm ở fragment nào hiện background khác màu
                try {
                    for (int positon = 0; positon < lvItemMenu.getChildCount(); positon++) {
                        if (positon == i) {
                            lvItemMenu.getChildAt(positon).setBackgroundColor(Color.parseColor("#767dff"));
                        } else {
                            lvItemMenu.getChildAt(positon).setBackgroundColor(Color.TRANSPARENT);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //lựa chọn fragment
                switch (i) {
                    case 0:
                        openFragment(FRAGMENT_COURSE, new Fragment_Course());
                        setDrawerLayout();
                        break;
                    case 1:
                        openFragment(FRAGMENT_MAPS, new Fragment_Maps());
                        setDrawerLayout();
                        break;
                    case 2:
                        openFragment(FRAGMENT_NEWS, new Fragment_News());
                        setDrawerLayout();
                        break;
                    case 3:
                        openFragment(FRAGMENT_SOCIAL, new Fragment_Social());
                        setDrawerLayout();
                        break;

                }
            }
        });
    }

    private void setDrawerLayout() {
        this.drawerLayout.closeDrawer(GravityCompat.START);
        this.toolbarBack(false);
    }

    private void initFragment(Fragment fragment) {
        this.currentFragment = FRAGMENT_COURSE;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.layout_fragmentContainer, fragment);
        fragmentTransaction.addToBackStack(fragment.getClass().getName());
        fragmentTransaction.commit();
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.layout_fragmentContainer, fragment);
        fragmentTransaction.addToBackStack(fragment.getClass().getName());
        fragmentTransaction.commit();
    }

    private void openFragment(int currentFragment, Fragment fragment) {
        if (this.currentFragment != currentFragment) {
            loadFragment(fragment);
            this.currentFragment = currentFragment;
        }
    }

    private void initItemDrawer() {
        this.itemMenuList = new ArrayList<>();
        this.itemMenuList.add(0, new ItemMenu(0, "https://img.icons8.com/nolan/64/courses.png", "Course"));
        this.itemMenuList.add(1, new ItemMenu(1, "https://img.icons8.com/external-photo3ideastudio-gradient-photo3ideastudio/64/000000/external-map-travel-checklist-photo3ideastudio-gradient-photo3ideastudio.png", "Maps"));
        this.itemMenuList.add(2, new ItemMenu(2, "https://img.icons8.com/external-icongeek26-outline-gradient-icongeek26/64/000000/external-news-news-icongeek26-outline-gradient-icongeek26.png", "News"));
        this.itemMenuList.add(3, new ItemMenu(3, "https://img.icons8.com/external-photo3ideastudio-gradient-photo3ideastudio/64/000000/external-social-digital-business-photo3ideastudio-gradient-photo3ideastudio.png", "Social"));
        this.itemMenuList.add(4, new ItemMenu(4, "https://img.icons8.com/external-icongeek26-outline-gradient-icongeek26/64/000000/external-exit-museum-icongeek26-outline-gradient-icongeek26.png", "Exit"));
        this.itemMenuAdapter = new ItemMenuAdapter(this.itemMenuList, getApplicationContext());
        this.lvItemMenu.setAdapter(itemMenuAdapter);
    }

    private void initToolbar() {
        this.ibtnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
        this.ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbarBack(false);
                //gọi đến hàm onBackPressed để thực thi back fragment
                onBackPressed();
            }
        });

        //khởi tạo fragment Course khi mở ứng dụng
        this.initFragment(new Fragment_Course());
    }

    private void initViewByID() {
        this.drawerLayout = findViewById(R.id.layout_Drawer);
        this.viewIclude = findViewById(R.id.layout_toolbar);
        this.navigationView = findViewById(R.id.layout_navigaitonView);
        this.lvItemMenu = findViewById(R.id.lv_ItemMenu);
        //ánh xạ layout con của toolbar iclude
        this.initViewIcludeToolbar(this.viewIclude);
    }

    private void initViewIcludeToolbar(View viewIclude) {
        this.ibtnMenu = viewIclude.findViewById(R.id.ibtn_Menu);
        this.ibtnBack = viewIclude.findViewById(R.id.ibtn_Back);
        this.tvAppName = viewIclude.findViewById(R.id.tv_AppName);
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }

    }

    //kiểm tra khi ấn vào fragment mới thì ấn đi menu và hiện back
    public void toolbarBack(boolean isEnable) {
        this.ibtnBack.setVisibility(isEnable ? View.VISIBLE : View.GONE);
        this.ibtnMenu.setVisibility(isEnable ? View.GONE : View.VISIBLE);
    }

}