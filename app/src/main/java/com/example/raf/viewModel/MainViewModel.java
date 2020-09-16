package com.example.raf.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.raf.model.TimetableFilter;
import com.example.raf.repository.AuthRepository;
import com.example.raf.repository.TimetableRepository;
import com.example.raf.repository.database.entity.TermEntity;
import com.example.raf.repository.web.model.Resource;

import java.util.List;


public class MainViewModel extends AndroidViewModel {

    private TimetableRepository timetableRepository;
    private AuthRepository authRepository;
    private MutableLiveData<TimetableFilter> filterLiveData;
    private LiveData<List<TermEntity>> filteredTimetable;

    public MainViewModel(@NonNull Application application) {
        super(application);

        timetableRepository = new TimetableRepository(application);
        authRepository = new AuthRepository(application);
        filterLiveData = new MutableLiveData<>();
        filteredTimetable = Transformations.switchMap(filterLiveData, new Function<TimetableFilter, LiveData<List<TermEntity>>>() {
            @Override
            public LiveData<List<TermEntity>> apply(TimetableFilter input) {
                return timetableRepository.getFilteredTimetable(input.getItem(),input.getGroup(),input.getDay(),input.getClassroom());
            }
        });
    }

    public LiveData<Resource<Void>> fetchTimetable(){
        return timetableRepository.fetchTimetable();
    }

    public LiveData<List<TermEntity>> getTimetable(){
        return timetableRepository.getTimetable();
    }

    public LiveData<List<TermEntity>> getFilteredTimetable() {
        return filteredTimetable;
    }

    public List<String> getGroups(){
        return timetableRepository.getGroupList();
    }

    public List<String> getDays(){
        return timetableRepository.getDayList();
    }

    public void setFilter(TimetableFilter filter) {
        filterLiveData.setValue(filter);
    }

    public void logOut(){
        authRepository.clearUser();
    }
}
