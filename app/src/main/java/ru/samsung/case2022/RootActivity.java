package ru.samsung.case2022;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;


import com.example.check_list.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ru.samsung.case2022.main.AddingActivity;
import ru.samsung.case2022.main.EditActivity;
import ru.samsung.case2022.main.MyRecyclerViewAdapter;
import ru.samsung.case2022.main.PreviewActivity;

public class RootActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener{
    private List<String> items_s = new ArrayList<>();
    MyRecyclerViewAdapter adapter;
    private static final int REQUEST_TAKE_PHOTO = 1;
    SharedPreferences sharedPreferences;
    Bitmap thumbnailBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        adapter = new MyRecyclerViewAdapter(this, items_s);
        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        adapter.setClickListener(this);

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
        Intent intent = new Intent(RootActivity.this, AddingActivity.class);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            thumbnailBitmap = (Bitmap) extras.get("data");
            Intent intent = new Intent(RootActivity.this, PreviewActivity.class);
            intent.putExtra("photo", thumbnailBitmap);
            startActivity(intent);
        }
    }

    public void onEditClick(View view) {
        Intent editClickIntent = new Intent(RootActivity.this, EditActivity.class);
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
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "Скоро будет", Toast.LENGTH_SHORT).show();
    }
}