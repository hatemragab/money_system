package com.example.temo.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.util.Calendar;

public class Main4Activity extends AppCompatActivity {
    Intent intent;
    EditText editText1, editText2, editText3, editText4;
    TextView textView;
    DB_manager manager;
    String date;
    String s;
    String newCost;

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
        Toast.makeText(this, "" + s + "", Toast.LENGTH_SHORT).show();
        editText1.setText(intent.getStringExtra("name") + "");
        editText2.setText(intent.getStringExtra("phone" + ""));
        editText3.setText(intent.getStringExtra("cost") + "");
        editText4.setText(intent.getStringExtra("method") + "");
        newCost = intent.getStringExtra("cost");
        Calendar calendar = Calendar.getInstance();
        date = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());


        textView.setText(date + "");
        manager = new DB_manager(this);
    }

    private void writeToFile(String dataa, Context context) {
        try {
            // OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(s + "config.txt", Context.MODE_PRIVATE));
            // outputStreamWriter.write(dataa);
            // outputStreamWriter.close();
            // FileOutputStream fileOutputStream = openFileOutput(s + "config.txt", Context.MODE_PRIVATE);
            FileWriter fileWriter = new FileWriter("/data/data/com.example.temo.myapplication/files/" + s + "config.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(dataa);
            bufferedWriter.newLine();

            bufferedWriter.flush();
            bufferedWriter.close();
            fileWriter.flush();
            fileWriter.close();
            // outputStreamWriter.flush();
            // outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public void save(View view) {
        String name = editText1.getText().toString().trim();
        String phone = editText2.getText().toString().trim();
        String cost = editText3.getText().toString().trim();
        String method = editText4.getText().toString().trim();
        manager.update(name, phone, cost, method, s);
        Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
        manager.updateDate(date, s);
        int ccost = Integer.valueOf(cost);
        int nncost = Integer.valueOf(newCost);

        if (ccost != nncost) {
            writeToFile("this user has update the cost from \n" + newCost + " to " + cost + " at " + date, this);
        }

        finish();
       // Intent intent1 = new Intent(Main4Activity.this, Main2Activity.class);
       // startActivity(intent1);

    }
}
