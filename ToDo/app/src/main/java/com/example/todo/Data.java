package com.example.todo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Data {
    public static String testVar = new String();
    public static ArrayList<String> maintitle = new ArrayList<String>();
    public static ArrayList<String> note = new ArrayList<String>();
    public static ArrayList<String> checkBox = new ArrayList<String>();

    public static void SaveData(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("shared preference", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String t = gson.toJson(Data.maintitle);
        String n = gson.toJson(Data.note);
        String c = gson.toJson(Data.checkBox);
        editor.putString("t", t);
        editor.putString("n", n);
        editor.putString("c", c);
        editor.apply();
    }

    public static void LoadData(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("shared preference", MODE_PRIVATE);
        Gson gson = new Gson();
        String t = sharedPreferences.getString("t",null);
        String n = sharedPreferences.getString("n",null);
        String c = sharedPreferences.getString("c",null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        Data.maintitle = gson.fromJson(t, type);
        Data.note = gson.fromJson(n, type);
        Data.checkBox = gson.fromJson(c, type);
        if (Data.maintitle == null){
            Data.maintitle = new ArrayList<String>();
            Data.note = new ArrayList<String>();
            Data.checkBox = new ArrayList<String>();
        }
    }
}
