package com.example.innomid.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.innomid.Adapters.HistoryAdapter;
import com.example.innomid.R;

public class FragmentHistory extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.history_fragment,container,false);
        RecyclerView recyclerView=root.findViewById(R.id.hisrecyc);
        HistoryAdapter adapter=new HistoryAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new
                LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        return root;
    }
}
