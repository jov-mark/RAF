package com.example.raf.repository.web.service;

import com.example.raf.repository.web.model.TimetableApiModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TimetableService {

    @GET("raspored/json.php")
    Call<List<TimetableApiModel>> getTimetable();
}
