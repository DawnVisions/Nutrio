package com.dawnvisions.nutrio;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Services extends Fragment
{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_services, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Sanford Services");

        final List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new NewArrivalsFragment());
        fragmentList.add(new SupportLineFragment());
        fragmentList.add(new GroupFragment());

        final List<String> fragmentTitleList = new ArrayList<>();
        fragmentTitleList.add(getString(R.string.new_arrivals_group));
        fragmentTitleList.add(getString(R.string.feeding_support_line));
        fragmentTitleList.add(getString(R.string.new_baby_and_me_group));

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        ViewPager viewPager = view.findViewById(R.id.pager);
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getFragmentManager())
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