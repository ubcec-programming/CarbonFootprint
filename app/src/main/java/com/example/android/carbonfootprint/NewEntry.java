package com.example.android.carbonfootprint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewEntry extends AppCompatActivity {
    private Map<String, Double> vegMap;
    private Map<String, Double> meatMap;
    private Map<String, Double> fruitMap;
    private Map<String, Double> grainMap;
    private Map<String, Double> seafoodMap;
    private Map<String, Double> dairyMap;
    EditText weight;
    Spinner food;
    Spinner type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);

        //creating all the maps
        vegMap = new HashMap<>();
        meatMap = new HashMap<>();
        fruitMap =new HashMap<>();
        grainMap = new HashMap<>();
        seafoodMap = new HashMap<>();
        dairyMap = new HashMap<>();
        ArrayAdapter<String> adapter1;

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

        List<String[]> vegList = vegFile.read();
        List<String[]> meatList = meatFile.read();
        List<String[]> fruitList = fruitFile.read();
        List<String[]> grainList = grainFile.read();
        List<String[]> seafoodList = seafoodFile.read();
        List<String[]> dairyList = dairyFile.read();

        for(int i =0;i<vegList.size()-1;i++){
            vegMap.put(vegList.get(i)[0],Double.parseDouble(vegList.get(i)[1]));
        }
        for(int i =0;i<meatList.size()-1;i++){
            meatMap.put(meatList.get(i)[0],Double.parseDouble(meatList.get(i)[1]));
        }
        for(int i =0;i<fruitList.size()-1;i++){
            fruitMap.put(fruitList.get(i)[0],Double.parseDouble(fruitList.get(i)[1]));
        }
        for(int i =0;i<grainList.size()-1;i++){
            grainMap.put(grainList.get(i)[0],Double.parseDouble(grainList.get(i)[1]));
        }
        for(int i =0;i<seafoodList.size()-1;i++){
            seafoodMap.put(seafoodList.get(i)[0],Double.parseDouble(seafoodList.get(i)[1]));
        }
        for(int i =0;i<dairyList.size()-1;i++){
            dairyMap.put(dairyList.get(i)[0],Double.parseDouble(dairyList.get(i)[1]));
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.food_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        weight= findViewById(R.id.weight);

        type = findViewById(R.id.type);
        type.setAdapter(adapter);

        if (type.getSelectedItem().toString().equals("Dairy")) {
            adapter1 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, new ArrayList<String>(dairyMap.keySet()));
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        } else if (type.getSelectedItem().toString().equals("Meats")) {
            adapter1 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, new ArrayList<String>(meatMap.keySet()));
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        } else if (type.getSelectedItem().toString().equals("Vegetables")) {
            adapter1 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, new ArrayList<String>(vegMap.keySet()));
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        } else if (type.getSelectedItem().toString().equals("Fruits")) {
            adapter1 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, new ArrayList<String>(fruitMap.keySet()));
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        } else  {
            adapter1 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, new ArrayList<String>(seafoodMap.keySet()));
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        }
        food = findViewById(R.id.food);
        food.setAdapter(adapter1);

    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    //Send task back to main page
    public void Submit (View view) {
        Intent i = new Intent();
        String name = type.getSelectedItem().toString();
        Double gm = Double.parseDouble(weight.getText().toString());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis()/1000);

        i.putExtra("Name", name);
        i.putExtra("weight", gm);
        i.putExtra("Timestamp",timestamp);

        setResult(RESULT_OK, i);

        finish();
    }
}

