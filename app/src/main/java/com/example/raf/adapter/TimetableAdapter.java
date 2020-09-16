package com.example.raf.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.raf.R;
import com.example.raf.repository.database.entity.TermEntity;

import java.util.ArrayList;
import java.util.List;

public class TimetableAdapter extends RecyclerView.Adapter<TimetableAdapter.TimetableHolder> {

    private OnItemClickedListener onItemClickedListener;
    private List<TermEntity> dataSet;
    private boolean clickable;

    public TimetableAdapter(boolean clickable){
        this.dataSet = new ArrayList<>();
        this.clickable = clickable;
    }

    @NonNull
    @Override
    public TimetableHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.listitem_raspored, parent, false);
        return new TimetableHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimetableHolder holder, int position) {
        TermEntity term = dataSet.get(position);
        holder.subject.setText(term.getSubject()+" - "+term.getType());
        holder.groups.setText(term.getGroups());
        holder.day.setText(term.getDay());
        holder.professor.setText(term.getProfessor());
        holder.time.setText(term.getTime());
        holder.classroom.setText(term.getClassroom());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void setData(List<TermEntity> timetable){
        dataSet.clear();
        dataSet.addAll(timetable);
        notifyDataSetChanged();
    }

    public class TimetableHolder extends RecyclerView.ViewHolder{

        List<TextView> textViews = new ArrayList<>();
        TextView subject;
        TextView professor;
        TextView groups;
        TextView day;
        TextView time;
        TextView classroom;

        public TimetableHolder(@NonNull View itemView) {
            super(itemView);

            subject = itemView.findViewById(R.id.txt_subject);
            professor = itemView.findViewById(R.id.txt_professor);
            groups = itemView.findViewById(R.id.txt_groups);
            day = itemView.findViewById(R.id.txt_day);
            time = itemView.findViewById(R.id.txt_time);
            classroom = itemView.findViewById(R.id.txt_classroom);

            if(clickable) {
                subject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onItemClickedListener != null) {
                            onItemClickedListener.onItemClicked(subject.getText().toString(),"subject");
                        }
                    }
                });
                professor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onItemClickedListener != null) {
                            onItemClickedListener.onItemClicked(professor.getText().toString(),"professor");
                        }
                    }
                });
                classroom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onItemClickedListener != null) {
                            onItemClickedListener.onItemClicked(classroom.getText().toString(),"classroom");
                        }
                    }
                });
            }

        }
    }

    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener){
        this.onItemClickedListener = onItemClickedListener;
    }

    public interface OnItemClickedListener{
        void onItemClicked(String item,String type);
    }
}
