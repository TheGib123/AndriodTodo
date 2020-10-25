package com.example.todo;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

class MyListAdapter extends ArrayAdapter<String> {

    private final Activity context;

    public MyListAdapter(Activity context, ArrayList<String> maintitle, ArrayList<String> checkBox) {
        super(context, R.layout.mylist, maintitle);
        this.context=context;
        Data.maintitle=maintitle;
        Data.checkBox=checkBox;
    }

    public View getView(final int position, final View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);

        //defines widgits
        LinearLayout row = (LinearLayout) rowView.findViewById(R.id.row);
        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        final Switch checkBoxBool = (Switch) rowView.findViewById(R.id.checkBox);

        // puts maintitle in the row
        titleText.setText(Data.maintitle.get(position));

        // fills in check box from data
        if (Data.checkBox.get(position).equals("yes")){
            checkBoxBool.setChecked(true);
        }
        else {
            checkBoxBool.setChecked(false);
        }

        // changes checkbox data on click and saves it
        checkBoxBool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkBoxBool.isChecked() == true){
                    Data.checkBox.set(position,"yes");
                }
                else {
                    Data.checkBox.set(position,"no");
                }
                Data.SaveData(context);
            }
        });

        // starts edit activity when row is clicked
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartEditActivity(position);
            }
        });

        return rowView;
    };

    // starts edit activity
    public void StartEditActivity(Integer position) {
        Intent intent = new Intent(context, EditActivity.class);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }

}