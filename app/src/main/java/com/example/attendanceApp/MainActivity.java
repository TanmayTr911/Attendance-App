package com.example.attendanceApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String ID="com.tanmay.qr.id";
    public static final String Pass="com.tanmay.qr.pass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Login(View view){
        Intent intent =new Intent(this, QRpage.class);
        Intent init =new Intent(this,Teacherside.class);
        EditText rollno = findViewById(R.id.roll);
        EditText pas = findViewById(R.id.pass);

        String id=rollno.getText().toString();
        String pa=pas.getText().toString();

        if(id.isEmpty() || pa.isEmpty()){
            Toast.makeText(this, "Please enter id password..", Toast.LENGTH_SHORT).show();
        }

        String ti="Shyamsir";
        String tp="hello";

        if(id.equals(ti) && pa.equals(tp)){

            startActivity(init);
        }

        else{

            intent.putExtra(ID,id);
            intent.putExtra(Pass,pa);
            startActivity(intent);

        }


    }

}