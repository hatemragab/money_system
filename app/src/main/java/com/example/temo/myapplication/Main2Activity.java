package com.example.temo.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Main2Activity extends AppCompatActivity implements MaterialSearchView.OnQueryTextListener, RecAdapter.cominucation {

    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private MaterialSearchView materialSearchView;
    private RecAdapter recAdapter;
    private DB_manager manager;
    private ArrayList<Mitem> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        manager = new DB_manager(this);
        Toolbar toolbar = findViewById(R.id.toole);

        materialSearchView = findViewById(R.id.search_view);
        materialSearchView.setHint(getString(R.string.search_by_name));


        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.rec);

        arrayList = manager.getArrayList();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        floatingActionButton = findViewById(R.id.fab);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            EditText name;
            EditText phone;
            EditText cost;
            EditText method;


            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
                builder.setTitle(R.string.add_new_member);
                builder.setCancelable(false);
                View view = getLayoutInflater().inflate(R.layout.alert_dialog, null, true);
                builder.setView(view);

                Button CANCEL = view.findViewById(R.id.btn1);
                Button ADD = view.findViewById(R.id.btn2);
                name = view.findViewById(R.id.edit3);
                phone = view.findViewById(R.id.edit4);
                cost = view.findViewById(R.id.edit5);
                method = view.findViewById(R.id.edit6);


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
                        // Calendar calendar = Calendar.getInstance();

                        // String date = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

                        Date dateee = new Date(); // your date
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(dateee);
                        int year = cal.get(Calendar.YEAR);
                        int month = cal.get(Calendar.MONTH) + 1;
                        int day = cal.get(Calendar.DAY_OF_MONTH);
                        String date = day + "/" + month + "/" + year;
                        //Toast.makeText(Main2Activity.this, "" + day + "/" + month + "/" + year, Toast.LENGTH_SHORT).show();

                        manager.insert(nName, nPhone, date, nCost, nMethod);

                        Toast.makeText(Main2Activity.this, R.string.inserted, Toast.LENGTH_SHORT).show();

                        arrayList = manager.getArrayList();

                        recAdapter = new RecAdapter(Main2Activity.this, arrayList);//Adapter
                        recAdapter.Adaptorsync(Main2Activity.this);
                        recyclerView.setAdapter(recAdapter);


                        alertDialog.dismiss();

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
        materialSearchView.setMenuItem(item);
        materialSearchView.setOnQueryTextListener(this);
        // searchView.setOnQueryTextListener(this);
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
                break;
            }
            case R.id.iii: {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.delete_all);
                builder.setMessage(R.string.all_data_will_deleted);
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
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
                                for (int id = 0; id < 100000; id++) {
                                    new File("/data/data/com.example.temo.myapplication/files/" + id + "config.txt").delete();

                                }
                            }
                        });
                        thread.start();


                    }
                });
                builder.show();
                break;
            }
            case R.id.about: {
                View view = getLayoutInflater().inflate(R.layout.about, null, true);
                final Dialog dialog = new Dialog(this);
                dialog.setContentView(view);
                dialog.setCancelable(false);
                Button button = view.findViewById(R.id.bbb);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }

        }
        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Mitem> newList;

    @Override
    public boolean onQueryTextSubmit(String query) {
        recAdapter.setFilter(newList);
        return true;

    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        newList = new ArrayList<>();
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
        intent.putExtra("id", postion);
        startActivity(intent);

    }
}
