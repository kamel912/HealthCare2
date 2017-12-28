package com.teamvii.healthcare.connection;

import com.teamvii.healthcare.models.Doctor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by MK on 12/24/2017.
 */

public interface ApiInterface {
    @GET("doctors.php?")
    Call<List<Doctor>> DOCTOR_CALL(@Query("id") String id);

    @GET("doctors.php")
    Call<List<Doctor>> LIST_CALL();
}
