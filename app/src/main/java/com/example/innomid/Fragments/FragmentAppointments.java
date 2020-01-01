package com.example.innomid.Fragments;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.innomid.Adapters.AppointmentsAdapter;
import com.example.innomid.R;
import com.google.android.material.tabs.TabLayout;


public class FragmentAppointments extends Fragment {

    AppointmentsAdapter.DemoCollectionPagerAdapter demoCollectionPagerAdapter;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_appoint, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        demoCollectionPagerAdapter = new AppointmentsAdapter.DemoCollectionPagerAdapter(getChildFragmentManager(), getContext());
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(demoCollectionPagerAdapter);
         tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
      //  tabLayout.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.tapcolorunselect)));



    }

}

