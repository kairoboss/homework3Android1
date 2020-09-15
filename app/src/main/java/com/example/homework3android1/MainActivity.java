package com.example.homework3android1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private Button openActivity, gmail;
    private ImageView resultImage;
    private TextView resultText;
    public static String KEY = "key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        openActivity = findViewById(R.id.btn_openActivity);
        gmail = findViewById(R.id.btn_gmail);
        resultImage = findViewById(R.id.mainImage);
        resultText = findViewById(R.id.textMain);
        openActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(KEY, "Welcome to Second Activity");
                startActivityForResult(intent, SecondActivity.REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == SecondActivity.REQUEST_CODE) {
            String text = data.getStringExtra(KEY);
            resultText.setText(text);
            Uri selectedImage = Uri.parse(data.getStringExtra(SecondActivity.imageKEY));
            resultImage.setImageURI(selectedImage);
        }
    }
    public void sendMessage(View view){
        Intent i = new Intent();
        i.setAction(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_TEXT, resultText.getText().toString());
        i.setType("text/plain");
        if(i.resolveActivity(getPackageManager()) !=null){
            startActivity(i);
        }
    }
}

