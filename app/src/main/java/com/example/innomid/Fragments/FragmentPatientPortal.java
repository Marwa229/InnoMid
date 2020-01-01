package com.example.innomid.Fragments;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.example.innomid.Adapters.PatientPortalAdapter;
import com.example.innomid.R;
import com.google.android.material.tabs.TabLayout;

public class FragmentPatientPortal extends Fragment {

    PatientPortalAdapter.DemoCollectionPagerAdapter demoCollectionPagerAdapter;
    ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.fragment_patientportal,container,false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        demoCollectionPagerAdapter = new PatientPortalAdapter.DemoCollectionPagerAdapter(getChildFragmentManager(), getContext());
        viewPager = view.findViewById(R.id.pager1);
        viewPager.setAdapter(demoCollectionPagerAdapter);
        final TabLayout tabLayout = view.findViewById(R.id.tab_layout1);
        tabLayout.setupWithViewPager(viewPager);

       // tabLayout.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.tapcolorunselect)));

     //   tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#c1c1c1"));


    }
}
