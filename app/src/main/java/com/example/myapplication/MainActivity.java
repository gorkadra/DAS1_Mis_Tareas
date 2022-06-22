package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText usuario, contra;
    private ListView lv1;
    private CheckBox cb;
    private Button btreg;

    private String nombres [] = {};
    private String edades [] = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = (EditText) findViewById(R.id.desc);
        contra = (EditText) findViewById(R.id.contra);
        cb = (CheckBox) findViewById(R.id.recordar);
        btreg = (Button) findViewById(R.id.button);

        btreg.setOnClickListener(view -> {
            Intent toreg = new Intent(this, Registro.class);
            startActivity(toreg);
        });


        SharedPreferences pref = getSharedPreferences("log", Context.MODE_PRIVATE);
        usuario.setText(pref.getString("usuario", ""));
        contra.setText(pref.getString("contraseña", ""));
    }

    /*public void log(View view){
        Intent toLV = new Intent(this, ActivityLV.class);
        String us = usuario.getText().toString();
        String con = contra.getText().toString();
        if(us.compareTo("admin") == 0){
            if (con.compareTo("admin") == 0) {
                if (cb.isChecked() == true){
                    gPref(view);
                    Toast.makeText(this, "CB pulsada", Toast.LENGTH_LONG).show();

                }
                startActivity(toLV);
            } else{

                Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                contra.setText("");
            }
        } else {
            Toast.makeText(this, "Este usuario no existe", Toast.LENGTH_SHORT).show();
            contra.setText("");
            usuario.setText("");
        }
    } */

    public void log(View view){
        Intent toLV = new Intent(this, ActivityLV.class);
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 2);
        SQLiteDatabase db = admin.getWritableDatabase();

        String us = usuario.getText().toString();
        String con = contra.getText().toString();

        if(!us.isEmpty() && !con.isEmpty()){
            String req = "select nombre, contra from usuario where nombre ='"+us+"'";
            Log.d("sentencia SQL ", req);
            Cursor fila = db.rawQuery
                    (req, null);//where nombre ='"+us+"'

            if(fila.moveToFirst()){
                if(us.compareTo(fila.getString(0)) == 0){
                    if (con.compareTo(fila.getString(1)) == 0) {
                        if (cb.isChecked() == true){
                            gPref(view);
                            Toast.makeText(this, "CB pulsada", Toast.LENGTH_LONG).show();

                        }
                        toLV.putExtra("usuario", us);
                        startActivity(toLV);
                    } else{

                        Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                        contra.setText("");
                    }
                } else {
                    Toast.makeText(this, "Este usuario no existe", Toast.LENGTH_SHORT).show();
                    contra.setText("");
                    usuario.setText("");
                }

                fila.getString(0);//nombre
                fila.getString(1);//contra
            }else{
                Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_LONG).show();
                db.close();
            }
        }else {
            Toast.makeText(this, "Revisa haber metido el nombre de usuario y la password", Toast.LENGTH_LONG).show();
        }
    }

    public void gPref(View view){

        SharedPreferences prefGuardar = getSharedPreferences("log", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefGuardar.edit();
        editor.putString("usuario", usuario.getText().toString());
        editor.putString("contraseña", contra.getText().toString());
        editor.commit();
        Toast.makeText(this, "Contraseñ y usuario guardados correctamente", Toast.LENGTH_LONG).show();
    }



}