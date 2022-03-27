package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText usuario, contra;
    private ListView lv1;

    private String nombres [] = {};
    private String edades [] = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = (EditText) findViewById(R.id.nombre);
        contra = (EditText) findViewById(R.id.contra);
    }

    public void log(View view){
        Intent toLV = new Intent(this, ActivityLV.class);
        String us = usuario.getText().toString();
        String con = contra.getText().toString();
        /*if(us == 'admin'){
            if (con == 'admin') {
                startActivity(toLV);
            } else{
                Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                contra.setText("");
            }
        } else {
            Toast.makeText(this, "Este usuario no existe", Toast.LENGTH_SHORT).show();
            contra.setText("");
            usuario.setText("");
        }*/
        startActivity(toLV);



    }

}