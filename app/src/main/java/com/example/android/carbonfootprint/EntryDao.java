package com.example.android.carbonfootprint;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface EntryDao {

    @Insert
    void insert(Entry entry);

    @Query("DELETE FROM entry_table")
    void deleteAll();

    @Query("SELECT * from entry_table ORDER BY entry ASC, timestamp ASC")
    LiveData<List<Entry>> getAllEntry();

    @Query ("DELETE FROM entry_table WHERE entry LIKE :entryName")
    void delete(String entryName);
}
