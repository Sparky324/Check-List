package com.example.check_list;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.example.check_list.MyRecyclerViewAdapter;
import com.example.check_list.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener{
    private List<String> items_s = new ArrayList<>();
    public Context context;
    MyRecyclerViewAdapter adapter;
    //private final RecyclerView.Adapter adapter = new ItemAdapter(this.items);
    private static final int REQUEST_TAKE_PHOTO = 1;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        adapter = new MyRecyclerViewAdapter(this, items_s);
        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        sharedPreferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
        Set<String> set = sharedPreferences.getStringSet("items_s", new HashSet<>());
        this.items_s = new ArrayList<>(set);
    }

    @Override
    protected void onStart() {
        super.onStart();

        adapter = new MyRecyclerViewAdapter(this, items_s);
        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        sharedPreferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
        Set<String> set = sharedPreferences.getStringSet("items_s", new HashSet<>());
        this.items_s = new ArrayList<>(set);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        adapter = new MyRecyclerViewAdapter(this, items_s);
        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        sharedPreferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
        Set<String> set = sharedPreferences.getStringSet("items_s", new HashSet<>());
        this.items_s = new ArrayList<>(set);
    }

   public void onAddClick(View view) {
        Intent intent = new Intent(MainActivity.this, AddingActivity.class);
        startActivity(intent);
    }

    public void onCameraClick(View view) {
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try{
            startActivityForResult(takePhotoIntent, REQUEST_TAKE_PHOTO);
        }catch (ActivityNotFoundException e){
            e.printStackTrace();
        }
    }

    public void onEditClick(View view) {
        Intent editClickIntent = new Intent(MainActivity.this, EditActivity.class);
        editClickIntent.putExtra("items", (Serializable) items_s);
        startActivity(editClickIntent);
    }

    public void onShareClick(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");

        sharedPreferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
        Set<String> set = sharedPreferences.getStringSet("items_s", new HashSet<>());
        this.items_s = new ArrayList<>(set);
        String s_items_s = String.join("\n", items_s);

        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Поделиться");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, s_items_s);

        startActivity(Intent.createChooser(intent, "Share"));
    }

    @Override
    public void onItemClick(View view, int position) {}

    public void onProdClick(View view) {}
}