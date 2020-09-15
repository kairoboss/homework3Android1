package com.example.homework3android1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import static com.example.homework3android1.MainActivity.KEY;

public class SecondActivity extends AppCompatActivity {
    private Button sendResult;
    private ImageView openGallery;
    private EditText setText;
    public static int REQUEST_CODE = 99;
    private Uri selectedImage;
    public static  String imageKEY = "img";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        String textFromMain = intent.getStringExtra(KEY);
        Toast.makeText(this,textFromMain,Toast.LENGTH_SHORT).show();
        init();
    }
    private void init(){
        sendResult = findViewById(R.id.send_result);
        openGallery = findViewById(R.id.gallery);
        setText = findViewById(R.id.edtext);
        openGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select picture"), REQUEST_CODE);


            }
        });
        sendResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String resultText = setText.getText().toString();
                Intent i = new Intent();
                i.putExtra(KEY,resultText);
                i.putExtra(imageKEY,selectedImage.toString());
                setResult(RESULT_OK, i);
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            selectedImage = data.getData();
            openGallery.setImageURI(selectedImage);
        }
    }
}