package com.example.innomid.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.innomid.Activities.SelectDateAndTimeActivity;
import com.example.innomid.R;

public class FragmentDrDetail extends Fragment {
Button button;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_dr_detail,container,false);
        button=root.findViewById(R.id.bookapp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), SelectDateAndTimeActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }
}
