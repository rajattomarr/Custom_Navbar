package com.example.android.customnavigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SNavigationDrawer sNavigationDrawer;
    Class fragmentClass;
    public static Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sNavigationDrawer = findViewById(R.id.navigationDrawer);

        // menu add
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Home", R.drawable.ic_baseline_home_24));
        menuItems.add(new MenuItem("Menu", R.drawable.ic_baseline_restaurant_menu_24));
        menuItems.add(new MenuItem("Combo", R.drawable.ic_baseline_fastfood_24));
        menuItems.add(new MenuItem("Orders", R.drawable.cartfood));

        sNavigationDrawer.setMenuItemList(menuItems);

        fragmentClass = homefragment.class;

        try {
            fragment =(Fragment) fragmentClass.newInstance();

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        if (fragment!=null)
        {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in,
                    android.R.animator.fade_out).replace(R.id.frameLayout,fragment).commit();

        }

        sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {
            @Override
            public void onDrawerOpening() {

            }

            @Override
            public void onDrawerClosing() {

                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }

                if (fragment!=null)
                {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in,
                            android.R.animator.fade_out).replace(R.id.frameLayout,fragment).commit();

                }

            }

            @Override
            public void onDrawerOpened() {

            }

            @Override
            public void onDrawerClosed() {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

    }
}