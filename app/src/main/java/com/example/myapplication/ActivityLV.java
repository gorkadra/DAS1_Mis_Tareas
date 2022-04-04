package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ActivityLV extends AppCompatActivity {

    private TextView tvLV;
    private ListView lv1;

    private String pelis [] = {""};
    private Integer valoraciones [] = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lv);

        tvLV = (TextView) findViewById(R.id.tv1);
        lv1 = (ListView) findViewById(R.id.lv1);

        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, R.layout.list_item_gorka, pelis);
        lv1.setAdapter(adapter);
    }

    public void reg(View view){
        Intent toTareas = new Intent(this, Tareas.class);
        startActivity(toTareas);
    }
}