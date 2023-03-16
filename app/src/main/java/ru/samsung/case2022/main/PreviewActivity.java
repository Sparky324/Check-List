package ru.samsung.case2022.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.check_list.R;

import ru.samsung.case2022.RootActivity;

public class PreviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        Bundle bundle = getIntent().getExtras();
        Bitmap bitmap = bundle.getParcelable("photo");
        ImageView imageView = findViewById(R.id.preview);
        imageView.setImageBitmap(bitmap);
    }

    public void onRecClick(View view) {
        Intent intent = new Intent(PreviewActivity.this, RootActivity.class);
        startActivity(intent);
        finish();
    }

    public void onCanClick(View view) {
        Intent intent = new Intent(PreviewActivity.this, RootActivity.class);
        startActivity(intent);
        finish();
    }
}