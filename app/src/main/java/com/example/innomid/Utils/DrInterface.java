package com.example.innomid.Utils;

import com.example.innomid.Data.DrnameData;
import com.example.innomid.Data.Result;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface DrInterface {


    @GET("topher/2017/May/59121517_baking/baking.json")
    Observable<List<DrnameData>> getDetails();

}
