package com.example.android.carbonfootprint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class NewEntry extends AppCompatActivity {
    EditText weight;
    Spinner food;
    Spinner type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.food_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        weight= findViewById(R.id.weight);

        type = findViewById(R.id.type);
        type.setAdapter(adapter);

        if (type.getSelectedItem().toString().equals("Dairy")) {
            ArrayAdapter<CharSequence> adapter1 = new ArrayAdapter<CharSequence>(this,
                    android.R.layout.simple_spinner_item, dairy);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        } else if (type.getSelectedItem().toString().equals("Meats")) {
            ArrayAdapter<CharSequence> adapter1 = new ArrayAdapter<CharSequence>(this,
                    android.R.layout.simple_spinner_item, meats);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        } else if (type.getSelectedItem().toString().equals("Vegetables")) {
            ArrayAdapter<CharSequence> adapter1 = new ArrayAdapter<CharSequence>(this,
                    android.R.layout.simple_spinner_item, dairy);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        } else if (type.getSelectedItem().toString().equals("Dairy")) {
            ArrayAdapter<CharSequence> adapter1 = new ArrayAdapter<CharSequence>(this,
                    android.R.layout.simple_spinner_item, dairy);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        } else if (type.getSelectedItem().toString().equals("Dairy")) {
            ArrayAdapter<CharSequence> adapter1 = new ArrayAdapter<CharSequence>(this,
                    android.R.layout.simple_spinner_item, dairy);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        food = findViewById(R.id.food);
        food.setAdapter();

    }

    //Send task back to main page
    public void Submit(View view) {
        Intent i = new Intent();
        String name = type.getSelectedItem();

        i.putExtra("Name", food);
        i.putExtra("Day", day);
        i.putExtra("Month",month);
        i.putExtra("Year", year);
        i.putExtra("Hour", hour);
        i.putExtra("Minutes", minutes);
        i.putExtra("Duration", duration);
        i.putExtra("Rating", rating);
        setResult(RESULT_OK, i);

        finish();
    }
}
}
