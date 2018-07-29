package com.example.temo.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {
    TextView textView5, textView6, textView7, textView8, textView9;
    Intent intent;
    Mitem mitem;
    Mitem mitem1;
    DB_manager db_manager;
    ListView listView;
    ArrayAdapter arrayAdapter;
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
        listView = findViewById(R.id.list);
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
        List<String> list = new ArrayList<>();
        list.add("sad");
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);
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
                        db_manager.delete(id + "");
                        new File("/data/data/com.example.temo.myapplication/files/" + id + "config.txt").delete();

                        finish();
                    }
                });
                builder.show();

            }

        }
        return true;
    }


}
