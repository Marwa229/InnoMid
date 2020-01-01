package com.example.innomid.Adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.innomid.Fragments.FragmentCurrent;
import com.example.innomid.Fragments.FragmentHistory;
import com.example.innomid.R;
import com.google.android.material.tabs.TabLayout;


public class AppointmentsAdapter extends Fragment {
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
     static TabLayout tabLayout;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root=  inflater.inflate(R.layout.fragment_appoint, container, false);
         tabLayout = root.findViewById(R.id.tab_layout);
        return root;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
       // ((TextView) view.findViewById(android.R.id.text1))
              //  .setText(Integer.toString(args.getInt(ARG_OBJECT)));
    }

    public static final class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {
        private final Context mContext;
        public  DemoCollectionPagerAdapter(FragmentManager fm, Context mContext) {
            super(fm);
            this.mContext = mContext;
        }

        @Override
        public Fragment getItem(int i) {

            switch (i){
                case 0:
                    Fragment fragment=new FragmentCurrent();
                    return fragment;

                case 1:
                    Fragment fragment1=new FragmentHistory();
                    return fragment1;

                 default:
                     return null;

            }


        }
        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return mContext.getResources().getString(TAB_TITLES[position]);


        }
    }



}
