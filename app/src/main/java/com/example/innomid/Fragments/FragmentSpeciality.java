package com.example.innomid.Fragments;

import android.app.SearchManager;
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
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.IdlingResource;

import com.example.innomid.Activities.MainActivity;
import com.example.innomid.Adapters.DrAdapter;
import com.example.innomid.Adapters.SpecialityAdapter;
import com.example.innomid.Data.DrnameData;
import com.example.innomid.Data.SpecialityData;
import com.example.innomid.MyDividerItemDecoration;
import com.example.innomid.R;
import com.example.innomid.Utils.DrInterface;
import com.example.innomid.Utils.DrUtils;
import com.example.innomid.Utils.SimpleIdlingResource;
import com.example.innomid.Utils.SpecialityInterface;
import com.example.innomid.Utils.SpecialityUtils;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FragmentSpeciality extends Fragment {


    private RecyclerView drrecyclar;
    private String resultJson;
    private Gson gson;
    private SpecialityInterface apiInterface;
    private List<SpecialityData> resultList;
    private boolean mTwoPane;
    private SpecialityAdapter adapter;
    private SearchView searchView;
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
        View view=inflater.inflate(R.layout.fragment_speciality,container,false);
        MainActivity.toolbar.setTitle(R.string.search_by_speciality);
        MainActivity.navigation.getMenu().findItem(R.id.navigation_home).setChecked(false);
        searchView=view.findViewById(R.id.srh);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                adapter.getFilter().filter(query);
                return false;
            }
        });

        drrecyclar=view.findViewById(R.id.spec_recyc);
        drrecyclar.setItemAnimator(new DefaultItemAnimator());
        drrecyclar.addItemDecoration(new MyDividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL, 36));
        apiInterface = SpecialityUtils.getRetrofit().create(SpecialityInterface.class);
        mIdlingResource = (SimpleIdlingResource) getIdlingResource();
        mIdlingResource.setIdleState(false);


        if (isNetworkConnected()) {
            resultList = getRecipeList();
        }else {
            Toast.makeText(getContext(), R.string.network_error, Toast.LENGTH_SHORT).show();

        }
        gson = new Gson();

        return view;
    }



    public List<SpecialityData> getRecipeList() {
        apiInterface.getDetails()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<SpecialityData>>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(List<SpecialityData> value) {
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
                        adapter=new SpecialityAdapter(getContext(),resultList);
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
                        drrecyclar.setLayoutManager(linearLayoutManager);
                        drrecyclar.setAdapter(adapter);
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
