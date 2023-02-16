package com.example.sukrit.demo_cric_f1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;


public class Filters extends Activity implements OnItemSelectedListener {

    static String item;
    ArrayAdapter<String> dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);

        Spinner spinner=(Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        ArrayList<String> categories=new ArrayList<>();
        categories.add("");
        categories.add("Delhi Daredevils");
        categories.add("Rising Pune Supergiant");
        categories.add("Kolkata Knight Riders");
        categories.add("Mumbai Indians");
        categories.add("Royal Challengers Bangalore");
        categories.add("Kings XI Punjab");
        categories.add("Sunrisers Hyderabad");
        categories.add("Gujarat Lions");

        dataAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        if (parent.getItemAtPosition(position).toString() != "") {
            Intent i = new Intent(Filters.this, FiltersTeamSchedule.class);
            item = parent.getItemAtPosition(position).toString();
            startActivity(i);
        }

        // Showing selected spinner item
        if (parent.getItemAtPosition(position).toString() != "") {
            Toast.makeText(Filters.this, "Fetching The Schedule Of " + item, Toast.LENGTH_LONG).show();
        }

    }
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
        return;
    }
    public static String getItem()
    {
        return item;
    }


}
