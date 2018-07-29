package com.example.temo.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class Main4Activity extends AppCompatActivity {
    Intent intent;
    EditText editText1, editText2, editText3, editText4;
    TextView textView;
    DB_manager manager;
    String date;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        intent = getIntent();
        s = intent.getStringExtra("id");
        editText1 = findViewById(R.id.update1);
        editText2 = findViewById(R.id.update2);
        editText3 = findViewById(R.id.update3);
        editText4 = findViewById(R.id.update4);
        textView = findViewById(R.id.update5);
        Calendar calendar = Calendar.getInstance();
        date = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        textView.setText(date + "");
        manager = new DB_manager(this);
    }

    public void save(View view) {
        String name = editText1.getText().toString().trim();
        String phone = editText2.getText().toString().trim();
        String cost = editText3.getText().toString().trim();
        String method = editText4.getText().toString().trim();
        manager.update(name, phone, cost, method, s);
        Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
        manager.updateDate(date,s);
        Intent intent1 = new Intent(Main4Activity.this, Main2Activity.class);
        startActivity(intent1);
        finish();
    }
}
