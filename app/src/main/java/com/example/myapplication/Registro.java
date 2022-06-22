package com.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Registro extends AppCompatActivity {

    private TextView nomtv, contv, conrtv;
    private Button btreg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        nomtv = (TextView) findViewById(R.id.rnom);
        contv = (TextView) findViewById(R.id.nrcon);
        conrtv = (TextView) findViewById(R.id.rconr);
        btreg = (Button) findViewById(R.id.btrer);
        btreg.setOnClickListener(view -> {
            registrar(getCurrentFocus());
        });

    }

    public void registrar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 2);
        SQLiteDatabase db = admin.getWritableDatabase();

        String nombre = nomtv.getText().toString();
        String contra = contv.getText().toString();
        String contrarepe = conrtv.getText().toString();

        if(!contra.isEmpty() && !nombre.isEmpty() && !contrarepe.isEmpty()){
            if(contra.compareTo(contrarepe)==0){
                ContentValues registro = new ContentValues();
                registro.put("nombre", nombre);
                registro.put("contra", contra);
                db.insert("usuario", null, registro);
                db.close();
                nomtv.setText("");
                conrtv.setText("");
                contv.setText("");
            }

            Toast.makeText(this, "Registro realizado", Toast.LENGTH_LONG).show();
            Intent toreg = new Intent(this, ActivityLV.class);
            toreg.putExtra("usuario", nombre);
            startActivity(toreg);
        }else {
            Toast.makeText(this, "Revisa haber completado todos los campos", Toast.LENGTH_LONG).show();
        }
    }



}
