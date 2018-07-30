package com.example.temo.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
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
    String cost;

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

        editText1.setText(intent.getStringExtra("name") + "");
        editText2.setText(intent.getStringExtra("phone" + ""));
        editText3.setText(intent.getStringExtra("cost") + "");
        editText4.setText(intent.getStringExtra("method") + "");
        newCost = intent.getStringExtra("cost");
        Calendar calendar = Calendar.getInstance();
        date = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.edit_page);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionBare)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        actionBar.show();
        textView.setText(date + "");
        manager = new DB_manager(this);
        isStoragePermissionGranted();
    }


    private void writeToFile(String dataa) {
        try {
            File file = new File("/data/data/com.example.temo.myapplication/files");
            file.mkdir();
            File file1 = new File("/data/data/com.example.temo.myapplication/files/"+s+"config.txt");

            FileWriter fileWriter = new FileWriter(file1, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(dataa);
            bufferedWriter.newLine();

            bufferedWriter.flush();
            bufferedWriter.close();
            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {

            e.getMessage();

        }
    }

    public void save(View view) {
        final String name = editText1.getText().toString().trim();
        final String phone = editText2.getText().toString().trim();
        cost = editText3.getText().toString().trim();
        final String method = editText4.getText().toString().trim();


        if (isStoragePermissionGranted()) {

            manager.update(name, phone, cost, method, s);
            manager.updateDate(date, s);


            if (!cost.equals(newCost)) {

                if (newCost.equals("")) {
                    writeToFile(getString(R.string.this_member_had_update_price_from) + newCost + getString(R.string._0_to) + cost + getString(R.string._at_) + date + "\n");
                } else {
                    writeToFile(getString(R.string.this_member_had_update_price_from) +newCost+ getString(R.string._to_) + cost + getString(R.string._at_) + date + "\n");
                }

            }
            Toast.makeText(this, R.string.updated, Toast.LENGTH_SHORT).show();
            finish();
        } else
            Toast.makeText(this, R.string.you_should_accept_first_to_continue, Toast.LENGTH_SHORT).show();


    }


    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {

                return true;
            } else {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation

            return true;
        }
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
            }

        }
        return true;
    }

}
