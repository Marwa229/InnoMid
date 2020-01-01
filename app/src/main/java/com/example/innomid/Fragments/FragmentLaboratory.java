package com.example.innomid.Fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.IdlingResource;

import com.example.innomid.Activities.MainActivity;
import com.example.innomid.Adapters.LaboratoryAdapter;
import com.example.innomid.Data.LaboratoryData;
import com.example.innomid.R;
import com.example.innomid.Utils.LabInterface;
import com.example.innomid.Utils.LaboratoryUtils;
import com.example.innomid.Utils.SimpleIdlingResource;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FragmentLaboratory extends Fragment {

    private RecyclerView labrecyclar;
    private String resultJson;
    private Gson gson;
    private LabInterface apiInterface;
    private List<LaboratoryData> resultList;
    private boolean mTwoPane;
    private LaboratoryAdapter adapter;
    @Nullable
    private SimpleIdlingResource mIdlingResource;

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_laboratory,container,false);
        labrecyclar=root.findViewById(R.id.labrecyc);
        apiInterface = LaboratoryUtils.getRetrofit().create(LabInterface.class);
        mIdlingResource = (SimpleIdlingResource) getIdlingResource();
        mIdlingResource.setIdleState(false);


        if (isNetworkConnected()) {
            resultList = getRecipeList();
        }else {

            Toast.makeText(getContext(), R.string.network_error, Toast.LENGTH_SHORT).show();

        }
        gson = new Gson();


        return root;
    }


    private boolean isNetworkConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();

    }

    public List<LaboratoryData> getRecipeList() {
        apiInterface.getDetails()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<LaboratoryData>>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(List<LaboratoryData> value) {
                        resultList = value;
                    }

                    @Override
                    public void onError(Throwable e) {
                       // showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        if (!disposable.isDisposed()) {
                            disposable.dispose();
                        }
                        resultJson = gson.toJson(resultList);
                        adapter=new LaboratoryAdapter(getContext(),resultList);
                        mTwoPane = MainActivity.getNoPane();
//                        if (mTwoPane) {
//                            //GridLayout
//                            GridLayoutManager gridLayoutManager = new
//                                    GridLayoutManager(getActivity(), 2);
//                            labrecyclar.setLayoutManager(gridLayoutManager);
//                            labrecyclar.setAdapter(adapter);
//                            mIdlingResource.setIdleState(true);
//                        } else {
                            //LinearVerticalLayout
                            LinearLayoutManager linearLayoutManager = new
                                    LinearLayoutManager(getActivity());
                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            labrecyclar.setLayoutManager(linearLayoutManager);
                            labrecyclar.setAdapter(adapter);
                            mIdlingResource.setIdleState(true);
                        }
//                        doubleArcProgress.setVisibility(View.GONE);
                   // }
                });
        return resultList;
    }




}
