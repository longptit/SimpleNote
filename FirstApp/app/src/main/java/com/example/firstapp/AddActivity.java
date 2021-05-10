package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    private EditText edttitle;
    private EditText edtdetail;
    private ImageView imgSave;
    private  ImageView imgBack;
    private Folder folder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        edttitle = findViewById(R.id.edt_title);
        edtdetail = findViewById(R.id.edt_detail);
        imgBack = findViewById(R.id.icon_back);
        imgSave = findViewById(R.id.icon_save);
        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edttitle.getText().toString().trim();
                String detail = edtdetail.getText().toString().trim();
                if (title.equals("") || detail.equals("")) {
                    Toast.makeText(AddActivity.this, "Empty", Toast.LENGTH_LONG).show();
                } else {
                    folder.setTitle(title);
                    folder.setDetail(detail);
                    Intent intent = new Intent(AddActivity.this, FragmentHome.class);
                    intent.putExtra("return_data", folder);
                    setResult(RESULT_OK, intent);
                    finish();
                }

            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}