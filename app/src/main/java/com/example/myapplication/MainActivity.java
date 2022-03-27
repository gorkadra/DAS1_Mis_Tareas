package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText n1;
    private EditText n2;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        n1 = (EditText) findViewById(R.id.numero1);
        n2 = (EditText) findViewById(R.id.numero2);
        result = (TextView) findViewById(R.id.Resultado);
    }

    public void sumar(View view){
        String val1 = n1.getText().toString();
        String val2 = n2.getText().toString();

        int num1;
    }
}