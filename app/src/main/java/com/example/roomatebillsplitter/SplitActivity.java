package com.example.roomatebillsplitter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

public class SplitActivity extends Activity {

    Double rent;
    Double electricity;
    Double water;
    Double internet;
    Double groceries;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_bills);

        Intent intent = getIntent();

        BillSplitter b = new BillSplitter();

        rent = Double.parseDouble(intent.getStringExtra("rent"));
        electricity = Double.parseDouble(intent.getStringExtra("electricity"));
        water = Double.parseDouble(intent.getStringExtra("water"));
        internet = Double.parseDouble(intent.getStringExtra("internet"));
        groceries = Double.parseDouble(intent.getStringExtra("groceries"));

        ArrayList<HashMap<String, String>> data = new ArrayList<>();

        HashMap<String, String> map = new HashMap<>();

        map.put("Rent: ", "each person pays $" + b.splitRent(rent))
    }
}
