package com.example.innomid.Utils;

import com.example.innomid.Data.MedicineData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MedicineInterface {

    @GET("topher/2017/May/59121517_baking/baking.json")
    Observable<List<MedicineData>> getDetails();
}
