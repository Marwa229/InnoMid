package com.example.innomid.Fragments;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.innomid.R;
import com.example.innomid.Activities.ServiceReview;

public class FragmentServices extends Fragment {
    private LinearLayout doctorcv;
    private LinearLayout rateus;
    private Fragment fragment;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_services,container,false);
        doctorcv=root.findViewById(R.id.doctorcvss);
        rateus=root.findViewById(R.id.rateus);
        doctorcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment=new FragmentDoctorCV();
                loadFragment(fragment);

            }
        });
        rateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ServiceReview.class);
                startActivity(intent);
            }
        });

        return root;
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentManager manager=getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.tab_list_recipe_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
