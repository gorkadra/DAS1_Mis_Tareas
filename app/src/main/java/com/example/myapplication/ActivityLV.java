package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivityLV extends AppCompatActivity {

    private TextView tvLV;
    private ListView lv1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lv);

        tvLV = (TextView) findViewById(R.id.tv1);
        lv1 = (ListView) findViewById(R.id.lv1);
        Objetos tarea1= new Objetos(1, "cargar", "cargar los datos");


        ArrayAdapter <Objetos> adapter = new ArrayAdapter<Objetos>(this, R.layout.list_item_gorka, llenarTareas());
        lv1.setAdapter(adapter);
    }

    public void reg(View view){
        Intent toTareas = new Intent(this, Tareas.class);
        startActivity(toTareas);
    }

    public ArrayList<Objetos> llenarTareas(){
        ArrayList<Objetos> lista = new ArrayList<>();

        lista.add(new Objetos(1,"cargar", "cargar los datos"));
        lista.add(new Objetos(1,"editar", "editar los datos"));
        lista.add(new Objetos(1,"eliminar", "eliminar los datos"));
        return lista;
    }

}