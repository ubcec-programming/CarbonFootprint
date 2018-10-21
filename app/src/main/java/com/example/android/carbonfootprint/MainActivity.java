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
    private Map<String, Integer> meatMap;
    private Map<String, Integer> fruitMap;
    private Map<String, Integer> grainMap;
    private Map<String, Integer> seafoodMap;
    private Map<String, Integer> dairyMap;

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
        meatMap = new HashMap<>();
        fruitMap =new HashMap<>();
        grainMap = new HashMap<>();
        seafoodMap = new HashMap<>();
        dairyMap = new HashMap<>();

        InputStream vegStream = getResources().openRawResource(R.raw.vegetables);
        CSVFile vegFile = new CSVFile(vegStream);

        InputStream meatStream = getResources().openRawResource(R.raw.meat);
        CSVFile meatFile = new CSVFile(meatStream);

        InputStream fruitStream = getResources().openRawResource(R.raw.fruits);
        CSVFile fruitFile = new CSVFile(fruitStream);

        InputStream grainStream = getResources().openRawResource(R.raw.grains);
        CSVFile grainFile = new CSVFile(grainStream);

        InputStream seafoodStream = getResources().openRawResource(R.raw.seafood);
        CSVFile seafoodFile = new CSVFile(seafoodStream);

        InputStream dairyStream = getResources().openRawResource(R.raw.dairy);
        CSVFile dairyFile = new CSVFile(dairyStream);

        List vegList = vegFile.read();
        List meatList = meatFile.read();
        List fruitList = fruitFile.read();
        List grainList = grainFile.read();
        List seafoodList = seafoodFile.read();
        List dairyList = dairyFile.read();

        for(int i =0;i<vegList.size()-1;i++){
            vegMap.put((String)vegList.get(i),Integer.parseInt((String)vegList.get(i++)));
        }
        for(int i =0;i<meatList.size()-1;i++){
            meatMap.put((String)meatList.get(i),Integer.parseInt((String)meatList.get(i++)));
        }
        for(int i =0;i<fruitList.size()-1;i++){
            fruitMap.put((String)fruitList.get(i),Integer.parseInt((String)fruitList.get(i++)));
        }
        for(int i =0;i<grainList.size()-1;i++){
            grainMap.put((String)grainList.get(i),Integer.parseInt((String)grainList.get(i++)));
        }
        for(int i =0;i<seafoodList.size()-1;i++){
            seafoodMap.put((String)seafoodList.get(i),Integer.parseInt((String)seafoodList.get(i++)));
        }
        for(int i =0;i<dairyList.size()-1;i++){
            dairyMap.put((String)dairyList.get(i),Integer.parseInt((String)dairyList.get(i++)));
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
