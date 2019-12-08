package com.example.roomatebillsplitter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String EACH = "Each roommate pays $";

    ImageView rentImageView;
    ImageView electricityImageView;
    ImageView waterImageView;
    ImageView internetImageView;
    ImageView groceriesImageView;

    EditText rentEditText;
    EditText electricityEditText;
    EditText waterEditText;
    EditText internetEditText;
    EditText groceriesEditText;

    Button splitButton;

    ArrayList<String> items;

    private SharedPreferences savedValues;
    private SharedPreferences prefs;

    private int numRoommates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rentImageView = findViewById(R.id.rent);
        Drawable rentDraw = getResources().getDrawable(R.mipmap.rent_round);
        rentImageView.setImageDrawable(rentDraw);

        electricityImageView = findViewById(R.id.electricity);
        Drawable electricityDraw = getResources().getDrawable(R.mipmap.electricity_round);
        electricityImageView.setImageDrawable(electricityDraw);

        waterImageView = findViewById(R.id.water);
        Drawable waterDraw = getResources().getDrawable(R.mipmap.water_round);
        waterImageView.setImageDrawable(waterDraw);

        internetImageView = findViewById(R.id.internet);
        Drawable internetDraw = getResources().getDrawable(R.mipmap.internet_round);
        internetImageView.setImageDrawable(internetDraw);

        groceriesImageView = findViewById(R.id.groceries);
        Drawable groceriesDraw = getResources().getDrawable(R.mipmap.groceries_round);
        groceriesImageView.setImageDrawable(groceriesDraw);

        rentEditText = findViewById(R.id.rentEditText);
        electricityEditText = findViewById(R.id.electricityEditText);
        waterEditText = findViewById(R.id.electricityEditText);
        internetEditText = findViewById(R.id.internetEditText);
        groceriesEditText = findViewById(R.id.groceriesEditText);

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);

        splitButton = findViewById(R.id.splitButton);
        this.splitButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_splitter_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                return true;
            case R.id.menu_about:
                Toast.makeText(this, "About", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.splitButton:
//                startActivity(new Intent(getApplicationContext(), SplitActivity.class));
                Intent intent = new Intent(this, SplitActivity.class);
                intent.putExtra("rent", String.valueOf(rentEditText.getText()));
                intent.putExtra("electricity", String.valueOf(electricityEditText.getText()));
                intent.putExtra("water", String.valueOf(waterEditText.getText()));
                intent.putExtra("internet", String.valueOf(internetEditText.getText()));
                intent.putExtra("groceries", String.valueOf(groceriesEditText.getText()));
                intent.putExtra("num_roommates", String.valueOf(prefs.))
                this.startActivity(intent);
                break;
        }
    }

    @Override
    public void onPause() {
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putString("rent", String.valueOf(rentEditText.getText()));
        editor.putString("electricity", String.valueOf(electricityEditText.getText()));
        editor.putString("water", String.valueOf(waterEditText.getText()));
        editor.putString("internet", String.valueOf(internetEditText.getText()));
        editor.putString("groceries", String.valueOf(groceriesEditText.getText()));
        editor.commit();
        super.onPause();
    }
    @Override
    public void onResume(){
        super.onResume();
        rentEditText.setText(savedValues.getString("rent", ""));
        electricityEditText.setText(savedValues.getString("electricity", ""));
        waterEditText.setText(savedValues.getString("water", ""));
        internetEditText.setText(savedValues.getString("internet", ""));
        groceriesEditText.setText(savedValues.getString("groceries", ""));
    }
}
