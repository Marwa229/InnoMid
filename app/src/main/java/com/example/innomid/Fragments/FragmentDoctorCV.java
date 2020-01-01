package com.example.innomid.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.innomid.R;

public class FragmentDoctorCV  extends Fragment {
    Fragment fragment;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_doctorscv,container,false);
        LinearLayout speciality = root.findViewById(R.id.speciality);
        LinearLayout drname = root.findViewById(R.id.drname);
        speciality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 fragment=new FragmentSpeciality();
                loadFragment(fragment);
            }
        });

        drname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment=new FragmentDrName();
                loadFragment(fragment);


            }
        });
        return root;
    }


    // load fragment
    public  void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.tab_list_recipe_container, fragment);
        // transaction.addToBackStack(null);
        //  getFragmentManager().popBackStack();
        //transaction.addToBackStack("TAG");
        transaction.commit();
    }

}
