package com.example.innomid.Fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.innomid.Activities.HospitalsActivity;
import com.example.innomid.Adapters.HospitalsAdapter;
import com.example.innomid.Data.Result;
import com.example.innomid.R;
import com.example.innomid.Utils.ApiInterface;
import com.example.innomid.Utils.HospitalUtils;
import com.example.innomid.Utils.SimpleIdlingResource;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FragmentHospital extends Fragment {



    RecyclerView recipeRecyclerView;
    String resultJson;
    Gson gson;
    SimpleIdlingResource idlingResource;
    ProgressBar doubleArcProgress;
    private ApiInterface apiInterface;
    private List<Result> resultList;
    private boolean mTwoPane;
    private HospitalsAdapter hospitalsAdapter;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.hospital_fragment, container, false);
        recipeRecyclerView=view.findViewById(R.id.recipe_recycler_view);

        apiInterface = HospitalUtils.getRetrofit().create(ApiInterface.class);
        idlingResource = (SimpleIdlingResource) ((HospitalsActivity) getActivity()).getIdlingResource();
        if (idlingResource != null) {
            idlingResource.setIdleState(false);
        }
        doubleArcProgress=view.findViewById(R.id.double_progress_arc);

        if (isNetworkConnected()) {
            resultList = getRecipeList();
        }else {
               doubleArcProgress.setVisibility(View.VISIBLE);
            Toast.makeText(getContext(), R.string.network_error, Toast.LENGTH_SHORT).show();

        }
        gson = new Gson();
        return view;
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();

    }

    public List<Result> getRecipeList() {
        apiInterface.getDetails()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Result>>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(List<Result> value) {
                        resultList = value;
                    }

                    @Override
                    public void onError(Throwable e) {
                        showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        if (!disposable.isDisposed()) {
                            disposable.dispose();
                        }
                        resultJson = gson.toJson(resultList);
                        hospitalsAdapter = new HospitalsAdapter(getActivity(), resultList);
                        mTwoPane = HospitalsActivity.getNoPane();
                        if (mTwoPane) {
                            //GridLayout
                            GridLayoutManager gridLayoutManager = new
                                    GridLayoutManager(getActivity(), 2);
                            recipeRecyclerView.setLayoutManager(gridLayoutManager);
                            recipeRecyclerView.setAdapter(hospitalsAdapter);
                            idlingResource.setIdleState(true);
                        } else {
                            //LinearVerticalLayout
                            LinearLayoutManager linearLayoutManager = new
                                    LinearLayoutManager(getActivity());
                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            recipeRecyclerView.setLayoutManager(linearLayoutManager);
                            recipeRecyclerView.setAdapter(hospitalsAdapter);
                            idlingResource.setIdleState(true);
                        }
//                        doubleArcProgress.setVisibility(View.GONE);
                    }
                });
        return resultList;
    }


    private void showError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }



}






