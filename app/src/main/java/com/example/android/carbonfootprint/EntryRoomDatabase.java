package com.example.android.carbonfootprint;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Entry.class}, version = 1)
public abstract class EntryRoomDatabase extends RoomDatabase {
    public abstract EntryDao entryDao();

    private static volatile EntryRoomDatabase INSTANCE;

    static EntryRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (EntryRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            EntryRoomDatabase.class, "entry_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
