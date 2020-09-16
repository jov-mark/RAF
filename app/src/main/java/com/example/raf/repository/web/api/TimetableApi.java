package com.example.raf.repository.web.api;


import com.example.raf.repository.web.model.TimetableApiModel;
import com.example.raf.repository.web.service.ServiceGenerator;
import com.example.raf.repository.web.service.TimetableService;

import java.util.List;

import retrofit2.Call;

public class TimetableApi {

    private TimetableService timetableService;

    public TimetableApi(){
        timetableService = ServiceGenerator.createService(TimetableService.class);
    }

    public Call<List<TimetableApiModel>> getTimetable(){
        return timetableService.getTimetable();
    }
}
