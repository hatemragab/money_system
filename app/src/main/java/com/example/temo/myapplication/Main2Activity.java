package com.example.temo.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    ArrayList<Mitem> arrayList;
    RecAdapter recAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        arrayList = new ArrayList<>();

        arrayList.add(new Mitem("hatem", "01012345678", "480$", "by year", "1/1/2018 12:22 AM"));
        arrayList.add(new Mitem("ali", "01012345678", "480$", "by year", "1/1/2018 12:22 AM"));
        arrayList.add(new Mitem("atef", "01012345678", "480$", "by year", "1/1/2018 12:22 AM"));
        arrayList.add(new Mitem("mohamed", "01012345678", "480$", "by year", "1/1/2018 12:22 AM"));
        arrayList.add(new Mitem("ahmed", "01012345678", "480$", "by year", "1/1/2018 12:22 AM"));
        // final String[] s = {"hatem", "dasfaf", "dfsdf", "dsgg", "DFGdfg", "Dfgdfg", "ewrwe", "werwer"};

        Toolbar toolbar = findViewById(R.id.toole);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recAdapter = new RecAdapter(this, arrayList);//Adapter
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recAdapter);
        floatingActionButton = findViewById(R.id.fab);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
                builder.setMessage("sdfgsg");
                builder.setTitle("title");
                builder.setCancelable(false);
                View view = getLayoutInflater().inflate(R.layout.alert_dialog, null, true);
                builder.setView(view);
                final AlertDialog alertDialog = builder.show();
                Button button = view.findViewById(R.id.btn1);
                Button button2 = view.findViewById(R.id.btn2);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();

                    }
                });
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Main2Activity.this, "done", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.search23);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setQueryHint("search by name ");
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
        for (Mitem mitem : arrayList)
        {
            String name=mitem.getName().toLowerCase();
            if (name.startsWith(newText))
            {
                newList.add(mitem);

            }



        }
        recAdapter.setFilter(newList);
        return true;
    }


}
