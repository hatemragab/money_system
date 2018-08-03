package com.example.temo.myapplication;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Main4Activity extends AppCompatActivity {
    private Intent intent;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private EditText editText5;

    private DB_manager manager;
    private String date;
    private String s;
    private String newCost;
    private String cost;

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
        editText5 = findViewById(R.id.updatedate);


        editText1.setText(intent.getStringExtra("name") + "");
        editText2.setText(intent.getStringExtra("phone" + ""));
        editText3.setText(intent.getStringExtra("cost") + "");
        editText4.setText(intent.getStringExtra("method") + "");
        editText5.setText(intent.getStringExtra("dateee") + "");
        newCost = intent.getStringExtra("cost");


        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setTitle(R.string.edit_page);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionBare)));
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        actionBar.show();

        manager = new DB_manager(this);
        isStoragePermissionGranted();
    }


    private void writeToFile(String dataa) {
        try {
            File file = new File("/data/data/com.example.temo.myapplication/files");
            file.mkdir();
            File file1 = new File("/data/data/com.example.temo.myapplication/files/" + s + "config.txt");

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
        date = editText5.getText().toString().trim();

        if (isStoragePermissionGranted()) {

            manager.update(name, phone, cost, method, s);
            manager.updateDate(date, s);


            if (!cost.equals(newCost)) {

                if (newCost.equals("")) {
                    writeToFile(getString(R.string.this_member_had_update_price_from) + newCost + getString(R.string._0_to) + cost + getString(R.string._at_) + date + "\n");
                } else {
                    writeToFile(getString(R.string.this_member_had_update_price_from) + newCost + getString(R.string._to_) + cost + getString(R.string._at_) + date + "\n");
                }
                finish();

            } else {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setMessage(R.string.Massege);
                alertDialog.setPositiveButton(R.string.continu, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.setCancelable(false);
                alertDialog.show();
            }


        } else
            Toast.makeText(this, R.string.you_should_accept_first_to_continue, Toast.LENGTH_SHORT).show();


    }


    private boolean isStoragePermissionGranted() {
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
