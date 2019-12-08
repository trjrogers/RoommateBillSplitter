package com.example.roomatebillsplitter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SimpleAdapter;
import android.widget.ListView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class SplitActivity extends Activity {

    Double rent;
    Double electricity;
    Double water;
    Double internet;
    Double groceries;
    int numRoommates;

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
        numRoommates = Integer.parseInt(intent.getStringExtra("num_roommates"));


        NumberFormat currency = NumberFormat.getCurrencyInstance();
//        totalAmount.setText(currency.format(totalDouble));

        ArrayList<HashMap<String, String>> data = new ArrayList<>();

        HashMap<String, String> map = new HashMap<>();

        map.put("Bill", "Rent");
        map.put("Amount", "Each person pays: " + b.splitRent(rent, numRoommates));
        data.add(map);

        map.put("Bill", "Electricity");
        map.put("Amount", "Each person pays: " + currency.format(b.splitElectricity(electricity, numRoommates)));
        data.add(map);

        map.put("Bill", "Water");
        map.put("Amount", "Each person pays: " + currency.format(b.splitWater(water, numRoommates)));
        data.add(map);

        map.put("Bill", "Internet");
        map.put("Amount", "Each person pays: " + currency.format(b.splitInternet(internet, numRoommates)));
        data.add(map);

        map.put("Bill", "Groceries");
        map.put("Amount", "Each person pays: " + currency.format(b.splitGroceries(groceries, numRoommates)));
        data.add(map);

        int resource = R.layout.listview_item;
        String[] from = {"Bill", "Amount"};
        int[] to = {R.id.bill_textview, R.id.amount_textview};

        SimpleAdapter adapter =
                new SimpleAdapter(this, data, resource, from, to);

        ListView listView = findViewById(R.id.itemsListView);
        listView.setAdapter(adapter);
    }
}
