package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    private EditText edttitle;
    private EditText edtdetail;
    private Folder folder;
    private ImageView imgSave;
    private ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        folder = (Folder) getIntent().getSerializableExtra("data");
        edttitle = findViewById(R.id.edt_title);
        edtdetail = findViewById(R.id.edt_detail);
        edttitle.setText(folder.getTitle());
        edtdetail.setText(folder.getDetail());
        imgSave = findViewById(R.id.icon_save);
        imgBack = findViewById(R.id.icon_back);

        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edttitle.getText().toString().trim();
                String detail = edtdetail.getText().toString().trim();
                if (title.equals("") || detail.equals("")) {
                    Toast.makeText(EditActivity.this, "Empty", Toast.LENGTH_LONG).show();
                } else {
                    folder.setTitle(title);
                    folder.setDetail(detail);
                    Intent intent = new Intent(EditActivity.this, FragmentHome.class);
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