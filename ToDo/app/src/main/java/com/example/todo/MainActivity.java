package com.example.todo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextClock;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class MainActivity extends AppCompatActivity {
    // defindes widgets and adapter
    MyListAdapter adapter;
    ListView list;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Loads saved data
        Data.LoadData(this);


        // assignes widgits and adapter
        adapter=new MyListAdapter(this, Data.maintitle, Data.checkBox);
        list=(ListView)findViewById(R.id.list);
        addBtn = (Button) findViewById(R.id.btnAdd);

        // puts all rows from adapter into list
        list.setAdapter(adapter);

        // add button clicked
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartNewActivity();
                adapter.notifyDataSetChanged();
            }
        });

    }

    // saves data when activity stops
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("test","Activity Stop");
        Data.SaveData(this);
    }

    // starts the add a todo activity
    public void StartNewActivity() {
        Intent intent = new Intent(this, NewActivity.class);
        this.startActivity(intent);
    }
}