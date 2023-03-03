package ru.samsung.case2022;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.check_list.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AddingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);
    }

    public void onAddClick(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
        Set<String> set = sharedPreferences.getStringSet("items_s", new HashSet<>());
        ArrayList<String> items_s = new ArrayList<>(set);

        EditText edit = this.findViewById(R.id.editProductName);
        items_s.add(edit.getText().toString());
        edit.setText("");

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> set1 = new HashSet<>(items_s);
        editor.putStringSet("items_s", set1);
        editor.apply();

        finish();
    }
}