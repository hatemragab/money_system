package com.example.temo.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {
    TextView textView5, textView6, textView7, textView8, textView9;
    Intent intent;
    Mitem mitem;
    Mitem mitem1;
    DB_manager db_manager;
    TextView read;

    int id = 0;
    int postion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        textView5 = findViewById(R.id.text5);
        textView6 = findViewById(R.id.text6);
        textView7 = findViewById(R.id.text7);
        textView8 = findViewById(R.id.text8);
        textView9 = findViewById(R.id.text9);
        read = findViewById(R.id.read);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.view_page);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionBare)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        actionBar.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        intent = getIntent();
        mitem = (Mitem) intent.getSerializableExtra("items");
        postion = intent.getIntExtra("id", 0);
        db_manager = new DB_manager(this);
        ArrayList mitem3 = db_manager.getArrayList();
        mitem1 = (Mitem) mitem3.get(postion);
        id = mitem.getId();
        textView5.setText(mitem1.getName() + "");
        textView6.setText(mitem1.getPhone() + "");
        textView7.setText(mitem1.getCost() + "");
        textView8.setText(mitem1.getMethod() + "");
        textView9.setText(mitem1.getDate() + "");

        File file = new File("/data/data/com.example.temo.myapplication/files/" + id + "config.txt");
        try {
            FileReader filereader = new FileReader(file);
            String readed = " ";
            BufferedReader reader = new BufferedReader(filereader);
            StringBuffer buffer = new StringBuffer();
            while ((readed = reader.readLine()) != null) {
                buffer.append(readed + "\n").toString();

            }
            read.setText(buffer + "\n");
            filereader.close();
            reader.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //int x= db_manager.getId2();
        //Toast.makeText(this, "this id is"+x, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu3, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.eee: {
                Intent intent1 = new Intent(Main3Activity.this, Main4Activity.class);
                int id = mitem.getId();
                String name = mitem1.getName();
                String phone = mitem1.getPhone();
                String cost = mitem1.getCost();
                String method = mitem1.getMethod();
                intent1.putExtra("id", id + "");
                intent1.putExtra("name", name + "");
                intent1.putExtra("phone", phone + "");
                intent1.putExtra("cost", cost + "");
                intent1.putExtra("method", method + "");

                startActivity(intent1);
                break;
            }
            case R.id.ddd: {


                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Delete");
                builder.setMessage("The selected item will be deleted");
                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                db_manager.delete(id + "");
                                new File("/data/data/com.example.temo.myapplication/files/" + id + "config.txt").delete();
                            }
                        });
                        thread.start();
                      finish();
                    }
                });
                builder.show();
                break;
            }
            case android.R.id.home:{
                finish();
            }

        }
        return true;
    }


}
