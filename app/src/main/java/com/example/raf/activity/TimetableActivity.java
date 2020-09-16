package com.example.raf.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.raf.R;
import com.example.raf.adapter.TimetableAdapter;
import com.example.raf.model.TimetableFilter;
import com.example.raf.repository.database.entity.TermEntity;
import com.example.raf.viewModel.MainViewModel;

import java.util.List;

public class TimetableActivity extends AppCompatActivity {

    public static final String FILTER_KEY = "FilterKey";
    public static final String FILTER_TYPE = "FilterType";

    private TimetableAdapter adapter;
    private String filter;
    private String classroom;
    private String type;

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        getFilter();
        initRecycler();
        initViewModel();
    }

    private void getFilter(){
        type = getIntent().getStringExtra(FILTER_TYPE);

        if(type.equals("classroom")){
            filter="";
            classroom=getIntent().getStringExtra(FILTER_KEY);
        }   else{
            filter = getIntent().getStringExtra(FILTER_KEY);
            classroom = "";
        }
    }

    private void initRecycler(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView_timetable_activity);
        adapter = new TimetableAdapter(false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    private void initViewModel(){
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getFilteredTimetable().observe(this, new Observer<List<TermEntity>>() {
            @Override
            public void onChanged(List<TermEntity> termEntities) {
                adapter.setData(termEntities);
            }
        });

        TimetableFilter timetableFilter= new TimetableFilter(filter, "",  "",classroom);
        viewModel.setFilter(timetableFilter);
    }
}
