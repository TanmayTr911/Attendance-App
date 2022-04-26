package com.example.attendanceApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Teacherside extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherside);


    }

    public void Scan(View view){
        Intent k =new Intent(this,QRScanner.class);
        startActivity(k);
    }
    public void Atten(View view){
        Intent k =new Intent(this,attendence.class);
        startActivity(k);
    }
}