package com.example.raf.repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.raf.repository.database.TimetableDatabase;
import com.example.raf.repository.database.dao.TimetableDao;
import com.example.raf.repository.database.entity.TermEntity;
import com.example.raf.repository.web.api.TimetableApi;
import com.example.raf.repository.web.model.Resource;
import com.example.raf.repository.web.model.TimetableApiModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimetableRepository {

    private int i;

    private TimetableApi timetableApi;
    private TimetableDao timetableDao;
    private MutableLiveData<Resource<Void>> resourceLiveData;
    private ExecutorService executorService;

    private List<String> groupList;
    private List<String> dayList;

    public TimetableRepository(Application application){
        i = 0;
        groupList = new ArrayList<>();
        dayList = new ArrayList<>();

        timetableApi = new TimetableApi();
        timetableDao = TimetableDatabase.getDatabase(application).getTimetableDao();
        resourceLiveData = new MutableLiveData<>();
        executorService = Executors.newCachedThreadPool();
    }

    public LiveData<Resource<Void>> fetchTimetable(){
        timetableApi.getTimetable().enqueue(new Callback<List<TimetableApiModel>>() {
            @Override
            public void onResponse(Call<List<TimetableApiModel>> call, Response<List<TimetableApiModel>> response) {
                if(!response.isSuccessful()){
                    Log.d("fetchTimetable","FAIL");
                }
                insertTimetable(response.body());
            }

            @Override
            public void onFailure(Call<List<TimetableApiModel>> call, Throwable t) {

            }
        });
        return resourceLiveData;
    }

    public LiveData<List<TermEntity>> getTimetable(){
        return  timetableDao.getTimetable();
    }

    public LiveData<List<TermEntity>> getFilteredTimetable(String item,String group,String day,String classroom){
        return timetableDao.getFilteredTimetable(item,group,day,classroom);
    }

    private void insertTimetable(List<TimetableApiModel> timetableApiModels){
        List<TermEntity> termEntityList = transformApiToEntity(timetableApiModels);
        getGroups(termEntityList);
        getDays(termEntityList);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                timetableDao.insertTimetable(termEntityList);
            }
        });
    }

    private List<TermEntity> transformApiToEntity(List<TimetableApiModel> timetableApiModels){
        List<TermEntity> termEntityList = new ArrayList<>();

        for(TimetableApiModel tModel : timetableApiModels){
            String id = String.valueOf(this.generateId());
            String subject = tModel.getSubject();
            String type = tModel.getType();
            String professor = tModel.getProfessor();
            String groups = tModel.getGroups();
            String day = tModel.getDay();
            String time = tModel.getTime();
            String classroom = tModel.getClassroom();

            TermEntity termEntity = new TermEntity(id,subject,type,professor,groups,day,time,classroom);
            termEntityList.add(termEntity);
        }
        return termEntityList;
    }

    private void getGroups(List<TermEntity> timetable){
        for (TermEntity termEntity : timetable) {
            String[] groups = termEntity.getGroups().split(",");
            for(String group : groups){
                group = group.trim();
                if(!groupList.contains(group)){
                    groupList.add(group);
                }
            }
        }
    }

    private void getDays(List<TermEntity> timetable){
        for (TermEntity termEntity : timetable) {
            if (!dayList.contains(termEntity.getDay())) {
                dayList.add(termEntity.getDay());
            }
        }
    }

    public List<String> getGroupList(){
        return groupList;
    }

    public List<String> getDayList(){
        return dayList;
    }

    private int generateId(){
        return i++;
    }

}
