package com.example.innomid.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.IdlingResource;

import android.app.SearchManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.innomid.Adapters.HospitalsAdapter;
import com.example.innomid.Data.Result;
import com.example.innomid.Fragments.FragmentHospital;
import com.example.innomid.R;
import com.example.innomid.Utils.ApiInterface;
import com.example.innomid.Utils.HospitalUtils;
import com.example.innomid.Utils.SimpleIdlingResource;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.List;
import java.util.zip.Inflater;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
public class HospitalsActivity extends AppCompatActivity {


    private static boolean mTwoPane;

    RecyclerView recipeRecyclerView;
    String resultJson;
    Gson gson;
    SimpleIdlingResource idlingResource;
    ProgressBar doubleArcProgress;
    private ApiInterface apiInterface;
    private List<Result> resultList;
    private HospitalsAdapter hospitalsAdapter;


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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals);



        getIdlingResource();

        recipeRecyclerView=findViewById(R.id.recipe_recycler_view);

        apiInterface = HospitalUtils.getRetrofit().create(ApiInterface.class);
        idlingResource = (SimpleIdlingResource) getIdlingResource();
        if (idlingResource != null) {
            idlingResource.setIdleState(false);
        }
        doubleArcProgress=findViewById(R.id.double_progress_arc);

        if (isNetworkConnected()) {
            resultList = getRecipeList();
        }else {
            doubleArcProgress.setVisibility(View.VISIBLE);
          //  Snackbar.make(getApplicationContext(),getString(R.string.network_error), Snackbar.LENGTH_LONG).show();

        }
        gson = new Gson();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
       SearchView searchView = (SearchView) menu.findItem(R.id.search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
             //   FragmentHospital
                hospitalsAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                hospitalsAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    public static boolean getNoPane() {
        return mTwoPane;
    }


    private boolean isNetworkConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

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
                        hospitalsAdapter = new HospitalsAdapter(getBaseContext(), resultList);
                        mTwoPane = HospitalsActivity.getNoPane();
                        if (mTwoPane) {
                            //GridLayout
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(HospitalsActivity.this, 2);
                            recipeRecyclerView.setLayoutManager(gridLayoutManager);
                            recipeRecyclerView.setAdapter(hospitalsAdapter);
                            idlingResource.setIdleState(true);
                        } else {
                            //LinearVerticalLayout
                            LinearLayoutManager linearLayoutManager = new
                                    LinearLayoutManager(HospitalsActivity.this);
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
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }


}
