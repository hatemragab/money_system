package com.example.temo.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText1, editText2;
    CheckBox checkBox;
    public final String NAME = "root";
    public final String PASS = "admin";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1 = findViewById(R.id.edit1);
        editText2 = findViewById(R.id.edit2);
        checkBox = findViewById(R.id.check);
        sharedPreferences = getSharedPreferences("hatem", Context.MODE_PRIVATE);

        if (sharedPreferences.getString("name", "fd").equals(NAME) && sharedPreferences.getString("pass", "").equals(PASS)) {
            finish();
            startActivity(new Intent(MainActivity.this, Main2Activity.class));

        }

    }

    public void lognIn(View view) {

        String name = editText1.getText().toString().trim();
        String pass = editText2.getText().toString().trim();
        if (checkBox.isChecked()) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", editText1.getText().toString().trim());
            editor.putString("pass", editText2.getText().toString().trim());
            editor.apply();
            checkBox.setChecked(false);
        }
        if (name.trim().equals(NAME) && pass.trim().equals(PASS)) {
            startActivity(new Intent(MainActivity.this, Main2Activity.class));
            finish();
        } else Toast.makeText(this, "pass and user name ", Toast.LENGTH_SHORT).show();

    }

}
