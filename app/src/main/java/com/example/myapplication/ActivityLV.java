package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Adapters.AdapterTarea;

public class ActivityLV extends AppCompatActivity {

    private TextView tvLV;
    private ListView lv1;
    private int[] ids;
    private String[] nombres;
    private String[] descripciones;
    private String propietario;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lv);

        tvLV = (TextView) findViewById(R.id.tv1);
        if (this.propietario == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                this.propietario = extras.getString("usuario");
            }
        }

        setContentView(R.layout.activity_lv);
        ListView tareas = (ListView) findViewById(R.id.lv1);
        AdapterTarea elAdaptador = new AdapterTarea(getApplicationContext(), llenarTareas());
        tareas.setAdapter(elAdaptador);
        //ArrayAdapter <ObjetoTarea> adapter = new ArrayAdapter<ObjetoTarea>(this, R.layout.list_item_gorka, llenarTareas());
        //lv1.setAdapter(adapter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Desea salir al login?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(getApplicationContext(), com.example.myapplication.MainActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            builder.show();
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_lv);

        tvLV = (TextView) findViewById(R.id.tv1);

        setContentView(R.layout.activity_lv);
        ListView tareas = (ListView) findViewById(R.id.lv1);
        AdapterTarea elAdaptador = new AdapterTarea(getApplicationContext(), llenarTareas());
        tareas.setAdapter(elAdaptador);

    }



    public void reg(View view){
        Intent toTareas = new Intent(this, Tareas.class);
        toTareas.putExtra("usuario", propietario);
        startActivity(toTareas);
    }

    public ArrayList<ObjetoTarea> llenarTareas(){
        ArrayList<ObjetoTarea> lista = new ArrayList<>();
        ObjetoTarea tarea = new ObjetoTarea
                (0, "Tarea de muestra", "Así deberían verse las tareas");
        lista.add(tarea);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 2);
        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor fila = db.rawQuery
                    ("select * from tarea where propietario ='"+propietario+"'", null);

        if(fila.moveToNext()){
            tarea = new ObjetoTarea
                    ((int)fila.getInt(0), fila.getString(1), fila.getString(2));
            lista.add(tarea);
            while (fila.moveToNext()){
                 tarea = new ObjetoTarea
                       ((int)fila.getInt(0), fila.getString(1), fila.getString(2));
                lista.add(tarea);
            }
        }else{
            tarea.update(0, "No hay tareas", "Aún no ha sido introducida ninguna tarea en la base de daros");
            lista.add(tarea);
        }




        return lista;
    }

}