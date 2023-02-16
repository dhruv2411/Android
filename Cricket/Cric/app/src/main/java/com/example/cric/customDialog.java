package com.example.cric;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class customDialog extends AppCompatActivity {

    public Activity c;
    public Dialog d;
    public Button ok;
    EditText bat1, toss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_custom_dialog);
        ok = (Button) findViewById(R.id.btn_ok);
        toss = (EditText) findViewById(R.id.toss);
        bat1 = (EditText) findViewById(R.id.bat1);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(com.example.cric.customDialog.this,RunActivity.class);
                i.putExtra("key", (toss.getText().toString()));
                i.putExtra("key2", (bat1.getText().toString()));
                startActivity(i);
            }
        });
    }
}
