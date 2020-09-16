package com.example.raf.repository.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.raf.repository.database.entity.TermEntity;

import java.util.List;

@Dao
public interface TimetableDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTimetable(List<TermEntity> timetable);

    @Query("SELECT * FROM timetable")
    LiveData<List<TermEntity>> getTimetable();


    @Query("SELECT * FROM timetable WHERE " +
            "(:item=='' OR subject LIKE :item || '%' OR professor LIKE '%' || :item || '%') AND" +
            "(:group=='' OR groups LIKE '%' || :group || '%') AND " +
            "(:day=='' OR day==:day) AND " +
            "(:ucionica=='' OR classroom==:ucionica)")
    LiveData<List<TermEntity>> getFilteredTimetable(String item, String group, String day, String ucionica);

}
