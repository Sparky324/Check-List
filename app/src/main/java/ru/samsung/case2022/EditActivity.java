package ru.samsung.case2022;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.check_list.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class EditActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    int elem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Bundle bundle = getIntent().getExtras();
        elem = bundle.getInt("pos");

        sharedPreferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
        TextView edit = findViewById(R.id.editProductName);
        Set<String> set = sharedPreferences.getStringSet("items_s", new HashSet<>());
        ArrayList<String> items_s = new ArrayList<>(set);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, items_s);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        edit.setText(items_s.get(elem));
    }

    public void onSaveClick(View view) {
        sharedPreferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
        EditText editText = findViewById(R.id.editProductName);
        Set<String> set = sharedPreferences.getStringSet("items_s", new HashSet<>());
        ArrayList<String> items_s = new ArrayList<>(set);

        items_s.set(elem, editText.getText().toString());

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> set1 = new HashSet<>(items_s);
        editor.putStringSet("items_s", set1);
        editor.apply();

        finish();
    }

    public void onDeleteClick(View view) {
        sharedPreferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
        Set<String> set = sharedPreferences.getStringSet("items_s", new HashSet<>());
        ArrayList<String> items_s = new ArrayList<>(set);

        items_s.remove(elem);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> set1 = new HashSet<>(items_s);
        editor.putStringSet("items_s", set1);
        editor.apply();

        Toast.makeText(EditActivity.this, "Удалено!", Toast.LENGTH_LONG).show();

        finish();
    }
}