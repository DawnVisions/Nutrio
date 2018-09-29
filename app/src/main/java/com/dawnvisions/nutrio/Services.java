package com.dawnvisions.nutrio;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Services extends Navi
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_services);
        super.onCreate(savedInstanceState);

        final List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new NewArrivalsFragment());
        fragmentList.add(new SupportLineFragment());
        fragmentList.add(new GroupFragment());

        final List<String> fragmentTitleList = new ArrayList<>();
        fragmentTitleList.add(getString(R.string.new_arrivals_group));
        fragmentTitleList.add(getString(R.string.feeding_support_line));
        fragmentTitleList.add(getString(R.string.new_baby_and_me_group));

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.pager);
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager())
        {
            @Override
            public Fragment getItem(int i)
            {
                return fragmentList.get(i);
            }

            @Override
            public int getCount()
            {
                return fragmentList.size();
            }

            @Override
            public CharSequence getPageTitle(int position)
            {
                return fragmentTitleList.get(position);
            }
        };

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
