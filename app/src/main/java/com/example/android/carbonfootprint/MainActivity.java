package com.example.android.carbonfootprint;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.io.InputStream;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Map<String, Integer> vegMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final WordListAdapter adapter = new WordListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //creating all the maps
        vegMap = new HashMap<>();

        InputStream vegStream = getResources().openRawResource(R.raw.vegetables);
        CSVFile vegFile = new CSVFile(vegStream);
        List vegList = vegFile.read();

        for(String item : vegList){

        }
    }



}

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Entry> mEntry; // Cached copy of entries

    WordListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        if (mEntry != null) {
            Entry current = mEntry.get(position);
            holder.wordItemView.setText(current.getEntry());
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No Entry");
        }
    }

    void setEntry(List<Entry> entries) {
        mEntry = entries;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mEntry has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mEntry != null)
            return mEntry.size();
        else return 0;
    }
}
