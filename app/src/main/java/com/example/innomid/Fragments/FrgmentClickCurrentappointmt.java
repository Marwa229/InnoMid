package com.example.innomid.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.innomid.Activities.ChatActivity;
import com.example.innomid.Activities.MainActivity;
import com.example.innomid.R;

public class FrgmentClickCurrentappointmt  extends Fragment {

    private LinearLayout sendmsg;
    private LinearLayout bookagain;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_click_currentappointment,container,false);
        sendmsg=root.findViewById(R.id.sendmsg);
        bookagain=root.findViewById(R.id.bookagain);
        bookagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new FragmentDrDetail();
                loadFragment(fragment);

            }
        });

        sendmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ChatActivity.class);
                startActivity(intent);
            }
        });
        MainActivity. navigation.getMenu().findItem(R.id.navigation_dashboard).setChecked(true);
        MainActivity.toolbar.setTitle("Appointement Detail");
        return root;
    }

    public  void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.tab_list_recipe_container, fragment);
        // transaction.addToBackStack(null);
        //  getFragmentManager().popBackStack();
        //transaction.addToBackStack("TAG");
        transaction.setCustomAnimations(android.R.anim.anticipate_overshoot_interpolator, android.R.anim.anticipate_overshoot_interpolator);
        transaction.commit();


    }

}
