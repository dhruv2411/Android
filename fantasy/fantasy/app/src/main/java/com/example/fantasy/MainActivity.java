package com.example.fantasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn1,btn2,btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.one);
        btn2 = findViewById(R.id.two);
        btn3 = findViewById(R.id.three);

        btn1.setOnClickListener(v -> {
            Intent on = new Intent(MainActivity.this, First.class);
            startActivity(on);
        });
        btn2.setOnClickListener(v -> {
            Intent on1 = new Intent(MainActivity.this, Second.class);
            startActivity(on1);
        });
        btn3.setOnClickListener(v -> {
            Intent on2 = new Intent(MainActivity.this, Third.class);
            startActivity(on2);
        });
    }
}