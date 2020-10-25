package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;



public class EditActivity extends AppCompatActivity {
    //define varibles and widgets
    Button saveBtn;
    Button deleteBtn;
    Integer position;
    EditText titleText;
    EditText notesText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //assignes varibles and widgets
        saveBtn = (Button) findViewById(R.id.saveBtn);
        deleteBtn = (Button) findViewById(R.id.deleteBtn);
        position = (Integer) getIntent().getIntExtra("position",0);
        titleText = (EditText) findViewById(R.id.titleText);
        notesText = (EditText) findViewById(R.id.notesText);

        // fills data in to text boxes
        titleText.setText(Data.maintitle.get(position));
        notesText.setText(Data.note.get(position));

        // save button listener
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data.maintitle.set(position,titleText.getText().toString());
                Data.note.set(position,notesText.getText().toString());
                Data.SaveData(getApplicationContext());
                StartMainActivity();
            }
        });

        // delete button listener
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newPosition = position;
                Data.maintitle.remove(newPosition);
                Data.note.remove(newPosition);
                Data.checkBox.remove(newPosition);
                Data.SaveData(getApplicationContext());
                StartMainActivity();
            }
        });
    }

    // goes back to main activity
    public void StartMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }
}