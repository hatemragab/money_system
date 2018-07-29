package com.example.temo.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    TextView textView5, textView6, textView7, textView8, textView9;
    Intent intent;
    Mitem mitem;
    DB_manager db_manager;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        textView5 = findViewById(R.id.text5);
        textView6 = findViewById(R.id.text6);
        textView7 = findViewById(R.id.text7);
        textView8 = findViewById(R.id.text8);
        textView9 = findViewById(R.id.text9);

    }

    @Override
    protected void onResume() {
        super.onResume();
        intent = getIntent();
        mitem = (Mitem) intent.getSerializableExtra("items");
        db_manager = new DB_manager(this);
        id = mitem.getId();
        textView5.setText(mitem.getName() + "");
        textView6.setText(mitem.getPhone() + "");
        textView7.setText(mitem.getCost() + "");
        textView8.setText(mitem.getMethod() + "");
        textView9.setText(mitem.getDate() + "");
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
                intent1.putExtra("id", id + "");
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
                        finish();
                    }
                });
                builder.show();

            }

        }
        return true;
    }
}
