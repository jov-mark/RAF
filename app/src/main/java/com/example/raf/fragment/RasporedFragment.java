package com.example.raf.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.raf.R;
import com.example.raf.activity.TimetableActivity;
import com.example.raf.adapter.TimetableAdapter;
import com.example.raf.model.TimetableFilter;
import com.example.raf.repository.database.entity.TermEntity;
import com.example.raf.repository.web.model.Resource;
import com.example.raf.viewModel.MainViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RasporedFragment extends Fragment {

    private MainViewModel viewModel;
    private RecyclerView recyclerView;
    private TimetableAdapter adapter;

    private ArrayAdapter<String> spnAdapterGroup;
    private ArrayAdapter<String> spnAdapterDay;
    private Spinner spnGroup;
    private Spinner spnDay;

    public static RasporedFragment newInstance(){
        return new RasporedFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_raspored,container,false);

        initRecycler(fragmentView);
        initSpinners(fragmentView);
        initFilter(fragmentView);

        return fragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViewModel();
    }

    private void initRecycler(View fragmentView){
        recyclerView = fragmentView.findViewById(R.id.recyclerView_timetable);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TimetableAdapter(true);
        adapter.setOnItemClickedListener(new TimetableAdapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(String item,String type) {
                Intent intent = new Intent(getContext(), TimetableActivity.class);
                intent.putExtra(TimetableActivity.FILTER_KEY,item);
                intent.putExtra(TimetableActivity.FILTER_TYPE,type);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void initSpinners(View fragmentView){
        spnGroup = fragmentView.findViewById(R.id.spn_group);
        spnDay = fragmentView.findViewById(R.id.spn_day);

        spnAdapterGroup = new ArrayAdapter<>(fragmentView.getContext(),android.R.layout.simple_list_item_1);
        spnAdapterDay = new ArrayAdapter<>(fragmentView.getContext(),android.R.layout.simple_list_item_1);
        spnAdapterGroup.add("Sve");
        spnAdapterDay.add("Svi");
        spnGroup.setAdapter(spnAdapterGroup);
        spnDay.setAdapter(spnAdapterDay);
    }

    private void initViewModel(){
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.fetchTimetable().observe(this, new Observer<Resource<Void>>() {
            @Override
            public void onChanged(Resource<Void> voidResource) {
                showToast(voidResource.isSuccessful());
            }
        });
        viewModel.getTimetable().observe(this, new Observer<List<TermEntity>>() {
            @Override
            public void onChanged(List<TermEntity> termEntities) {
                adapter.setData(termEntities);
                spnAdapterGroup.addAll(viewModel.getGroups());
                spnAdapterDay.addAll(viewModel.getDays());
                spnGroup.setAdapter(spnAdapterGroup);
                spnDay.setAdapter(spnAdapterDay);
            }
        });
        viewModel.getFilteredTimetable().observe(this, new Observer<List<TermEntity>>() {
            @Override
            public void onChanged(List<TermEntity> termEntities) {

                adapter.setData(termEntities);
            }
        });
    }

    private void initFilter(View fragmentView){
        ImageButton btnSearch = fragmentView.findViewById(R.id.img_search);
        EditText etxtItem = fragmentView.findViewById(R.id.etxt_prof);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimetableFilter timetableFilter = new TimetableFilter(etxtItem.getText().toString(),getSpnGroup(),getSpnDay(),"");
                viewModel.setFilter(timetableFilter);
            }
        });
    }

    private void showToast(boolean isSuccess) {
        String message = isSuccess ? "Data yay" : "Data nay..";
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    private String getSpnGroup(){
        String group = spnGroup.getSelectedItem().toString();
        if(group.equals("Sve"))
            group = "";
        return group;
    }

    private String getSpnDay(){
        String day = spnDay.getSelectedItem().toString();
        if(day.equals("Svi"))
            day = "";
        return day;
    }
}
