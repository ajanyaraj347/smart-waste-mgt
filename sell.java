package com.example.wastemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class sell extends AppCompatActivity {
Button upload;
EditText sname;
EditText saddress;
EditText pname;
EditText pcost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        sname=findViewById(R.id.sname);
        saddress=findViewById(R.id.saddress);
        pname=findViewById(R.id.pname);
        pcost=findViewById(R.id.pcost);

        upload=findViewById(R.id.upload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View v) {
                Intent select=new Intent(Intent.ACTION_CAMERA_BUTTON);
                //Intent intent = select.setData(uri);
                startActivity(Intent.createChooser(select,"select an image from camera"));
            }
        });
    }
}
