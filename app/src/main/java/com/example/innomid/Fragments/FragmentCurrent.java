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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.IdlingResource;

import com.example.innomid.Activities.MainActivity;
import com.example.innomid.Adapters.CurrentAdapter;
import com.example.innomid.Adapters.LaboratoryAdapter;
import com.example.innomid.Data.CurrentData;
import com.example.innomid.Data.LaboratoryData;
import com.example.innomid.R;
import com.example.innomid.Utils.CurrentInterface;
import com.example.innomid.Utils.CurrentUtils;
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

public class FragmentCurrent extends Fragment {

    private RecyclerView currentrecyclar;
    private String resultJson;
    private Gson gson;
    private CurrentInterface apiInterface;
    private List<CurrentData> resultList;
    private boolean mTwoPane;
    private CurrentAdapter adapter;
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
        View root=inflater.inflate(R.layout.current_fragment,container,false);
        currentrecyclar=root.findViewById(R.id.current_recyc);
        apiInterface = CurrentUtils.getRetrofit().create(CurrentInterface.class);
        mIdlingResource = (SimpleIdlingResource) getIdlingResource();
        mIdlingResource.setIdleState(false);



        if (isNetworkConnected()) {
            resultList = getRecipeList();
        }else {

           // Snackbar.make(root, getActivity().getString(R.string.network_error), Snackbar.LENGTH_LONG).show();
            Toast.makeText(getContext(), R.string.network_error, Toast.LENGTH_SHORT).show();

        }
        gson = new Gson();
        return root;
    }



    public List<CurrentData> getRecipeList() {
        apiInterface.getDetails()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CurrentData>>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(List<CurrentData> value) {
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
                        adapter=new CurrentAdapter(getContext(),resultList);
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
                        currentrecyclar.setLayoutManager(linearLayoutManager);
                        currentrecyclar.setAdapter(adapter);
                        mIdlingResource.setIdleState(true);
                    }
//                        doubleArcProgress.setVisibility(View.GONE);
                    // }
                });
        return resultList;
    }


    private boolean isNetworkConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();

    }



}
