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

public class NewActivity extends AppCompatActivity {
    Button saveBtn;
    EditText titleText;
    EditText notesText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        //define widgets
        saveBtn = (Button) findViewById(R.id.saveBtn);
        titleText = (EditText) findViewById(R.id.titleText);
        notesText = (EditText) findViewById(R.id.notesText);


        // saves data when clicked and stars main activity
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleText.getText().toString();
                if (!title.equals("")) {
                    Data.maintitle.add(titleText.getText().toString());
                    Data.note.add(notesText.getText().toString());
                    Data.checkBox.add("no");
                }
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