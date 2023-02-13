package com.example.check_list;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class EditActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        sharedPreferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
        TextView edit = findViewById(R.id.chosenTextView);
        Set<String> set = sharedPreferences.getStringSet("items_s", new HashSet<>());
        ArrayList<String> items_s = new ArrayList<>(set);
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, items_s);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                edit.setText(spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });
    }

    public void onSaveClick(View view) {
        Spinner spinner = findViewById(R.id.spinner);
        sharedPreferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
        EditText editText = findViewById(R.id.chosenTextView);
        Set<String> set = sharedPreferences.getStringSet("items_s", new HashSet<>());
        ArrayList<String> items_s = new ArrayList<>(set);

        String el = spinner.getSelectedItem().toString();
        int idx = items_s.indexOf(el);
        items_s.set(idx, editText.getText().toString());

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> set1 = new HashSet<>(items_s);
        editor.putStringSet("items_s", set1);
        editor.apply();

        finish();
    }

    public void onDeleteClick(View view) {
        Spinner spinner = findViewById(R.id.spinner);
        sharedPreferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
        Set<String> set = sharedPreferences.getStringSet("items_s", new HashSet<>());
        ArrayList<String> items_s = new ArrayList<>(set);

        String el = spinner.getSelectedItem().toString();
        items_s.remove(el);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> set1 = new HashSet<>(items_s);
        editor.putStringSet("items_s", set1);
        editor.apply();

        Toast.makeText(EditActivity.this, "Удалено!", Toast.LENGTH_LONG).show();

        finish();
    }

}