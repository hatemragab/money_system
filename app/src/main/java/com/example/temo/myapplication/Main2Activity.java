package com.example.temo.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements SearchView.OnQueryTextListener, RecAdapter.cominucation {

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;

    RecAdapter recAdapter;
    DB_manager manager;
    ArrayList<Mitem> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        manager = new DB_manager(this);
        Toolbar toolbar = findViewById(R.id.toole);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.rec);

        arrayList = manager.getArrayList();
//        recAdapter = new RecAdapter(Main2Activity.this,arrayList);//Adapter
//        recAdapter.Adaptorsync(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(recAdapter);

        floatingActionButton = findViewById(R.id.fab);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            EditText name;
            EditText phone;
            EditText cost;
            EditText method;


            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
                builder.setTitle("Add new Member");
                builder.setCancelable(false);
                View view = getLayoutInflater().inflate(R.layout.alert_dialog, null, true);
                builder.setView(view);

                Button CANCEL = view.findViewById(R.id.btn1);
                Button ADD = view.findViewById(R.id.btn2);
                name = view.findViewById(R.id.edit3);
                phone = view.findViewById(R.id.edit4);
                cost = view.findViewById(R.id.edit5);
                method = view.findViewById(R.id.edit6);
                // spinner = findViewById(R.id.spinner);
                //  String[] items = new String[]{"by Day", "by week", "by month", "by year"};
                // ArrayAdapter adapterSpinner = new ArrayAdapter(Main2Activity.this, android.R.layout.simple_spinner_dropdown_item, items);
                //spinner.setAdapter(adapterSpinner);

                final AlertDialog alertDialog = builder.show();
                CANCEL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        alertDialog.dismiss();

                    }
                });
                ADD.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String nName = name.getText().toString().trim();
                        String nPhone = phone.getText().toString().trim();
                        String nCost = cost.getText().toString().trim();
                        String nMethod = method.getText().toString().trim();
                        // String nMethod = spinner.getSelectedItem().toString();
                        //SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyy HH:MM a");
                        //String date = s.format(new Date());
                        Calendar calendar = Calendar.getInstance();
                        String date = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

                        long x = manager.insert(nName, nPhone, date, nCost, nMethod);
                        if (x >= 0) {
                           // Toast.makeText(Main2Activity.this, "inseted", Toast.LENGTH_SHORT).show();

                            arrayList = manager.getArrayList();

                            recAdapter = new RecAdapter(Main2Activity.this, arrayList);//Adapter
                            recAdapter.Adaptorsync(Main2Activity.this);
                            recyclerView.setAdapter(recAdapter);
 //                           FileOutputStream out = new FileOutputStream();

                            alertDialog.dismiss();
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        arrayList = manager.getArrayList();
        recAdapter = new RecAdapter(Main2Activity.this, arrayList);//Adapter
        recAdapter.Adaptorsync(this);
        recyclerView.setAdapter(recAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.search23);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setQueryHint("search by name ");
        searchView.setIconifiedByDefault(true);
        searchView.setIconified(true);

        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logout: {
                SharedPreferences sharedPreferences = getSharedPreferences("hatem", Context.MODE_PRIVATE);
                sharedPreferences.edit().clear().apply();

                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
            case R.id.iii: {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Delete All");
                builder.setMessage("All data will deleted");
                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        manager.deleteAll();
                        arrayList.clear();
                        recAdapter = new RecAdapter(Main2Activity.this, arrayList);//Adapter

                        recyclerView.setAdapter(recAdapter);

                        dialog.dismiss();
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                for (int id =0 ;id<100000;id++)
                                {
                                    new File("/data/data/com.example.temo.myapplication/files/" + id + "config.txt").delete();

                                }
                            }
                        });
                        thread.start();


                    }
                });
                builder.show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        ArrayList<Mitem> newList = new ArrayList<>();
        for (Mitem mitem : arrayList) {
            String name = mitem.getName().toLowerCase();
            if (name.startsWith(newText)) {
                newList.add(mitem);

            }


        }
        recAdapter.setFilter(newList);
        return true;
    }


    @Override
    public void transfer(int postion) {
        Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
        intent.putExtra("items", arrayList.get(postion));
        intent.putExtra("id",postion);
        startActivity(intent);

    }
}
