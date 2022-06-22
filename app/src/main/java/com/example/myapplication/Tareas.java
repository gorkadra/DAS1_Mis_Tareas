package com.example.myapplication;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLData;

public class Tareas extends AppCompatActivity {

    private TextView idTV, nombreTV, descTV;
    //private Button btA, btEdit, btElim;
    private String propietario;
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tareas);

        idTV = (TextView) findViewById(R.id.id);
        nombreTV = (TextView) findViewById(R.id.nom);
        descTV = (TextView) findViewById(R.id.desc);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.propietario = extras.getString("usuario");
        }


    }

    public void Registrar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 2);
        SQLiteDatabase db = admin.getWritableDatabase();

        String id = idTV.getText().toString();
        String nombre = nombreTV.getText().toString();
        String desc = descTV.getText().toString();

        if(!id.isEmpty() && !nombre.isEmpty() && !desc.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("codigo", id);
            registro.put("nombre", nombre);
            registro.put("descripcion", desc);
            registro.put("propietario", propietario);
            db.insert("tarea", null, registro);
            db.close();
            idTV.setText("");
            nombreTV.setText("");
            descTV.setText("");
            Toast.makeText(this, "Registro realizado", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "Revisa haber completado todos los campos", Toast.LENGTH_LONG).show();
        }
    }

    public void Buscar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 2);
        SQLiteDatabase db = admin.getWritableDatabase();

        String id = idTV.getText().toString();

        if(!id.isEmpty()){
            Cursor fila = db.rawQuery
                    ("select nombre, descripcion from tarea where codigo ="+id, null);

            if(fila.moveToFirst()){
                nombreTV.setText(fila.getString(0));
                descTV.setText(fila.getString(1));
            }else{
                Toast.makeText(this, "Tarea no encontrada", Toast.LENGTH_LONG).show();
                db.close();
            }
        }else {
            Toast.makeText(this, "Revisa haber metido el identificador", Toast.LENGTH_LONG).show();
        }
    }

    public void Eliminar(View view){


        String idSTR = idTV.getText().toString();

        if(!idSTR.isEmpty()){
            Log.d("id", "es"+id);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Estás seguro de que deseas eliminar esta tarea?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Elm();
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            builder.show();

        }else {
            Toast.makeText(this, "Revisa haber introducido el identificador", Toast.LENGTH_LONG).show();

        }
    }

    public void Elm(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 2);
        SQLiteDatabase db = admin.getWritableDatabase();

        String idSTR = idTV.getText().toString();

        int cantidad = db.delete("tarea", "codigo ="+idSTR, null);
        Log.d("cantidad", "es"+cantidad);
        db.close();
        idTV.setText("");
        nombreTV.setText("");
        descTV.setText("");

        if(cantidad == 1){
            Log.d("objeto eliminado ", "si");
        }else{
            Log.d("objeto eliminado ", "no");
        }
    }

    public void Editar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 2);
        SQLiteDatabase db = admin.getWritableDatabase();

        String id = idTV.getText().toString();
        String nombre = nombreTV.getText().toString();
        String desc = descTV.getText().toString();

        if(!id.isEmpty() && !nombre.isEmpty() && !desc.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("codigo", id);
            registro.put("nombre", nombre);
            registro.put("descripcion", desc);
            int cantidad = db.update("tarea", registro, "codigo =" + id, null);
            db.close();
            if (cantidad == 1){
                Toast.makeText(this, "Tarea editada correctamente", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this, "La tarea que tratas de editar no existe", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(this, "Revisa haber completado todos los campos", Toast.LENGTH_LONG).show();
        }
    }

}