package com.example.attendanceApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;

import javax.security.auth.Subject;

public class QRScanner extends AppCompatActivity {
    private Button scan_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);
        scan_btn=findViewById(R.id.button4);
        final Activity activity = this;
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(activity);


                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.setOrientationLocked(true);
                integrator.initiateScan();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result !=null){
            if(result.getContents()==null){
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            }
            else {

                boolean crt=false;
                for(int i=0;i<10;i++){
                    String v="20bcs10"+Integer.toString(i);
                    if(result.getContents().toString().equals(v)){
                        crt=true;
                        Calendar calendar =Calendar.getInstance();
                        String currentDate= DateFormat.getDateInstance().format(calendar.getTime());


                        FirebaseDatabase.getInstance().getReference().child("Dates").child(currentDate).push().setValue(result.getContents().toString());
                        Toast.makeText(this, result.getContents()+"is present",Toast.LENGTH_LONG).show();
                        FirebaseDatabase.getInstance().getReference().child("Student").child(result.getContents().toString()).push().setValue(currentDate);
                    }
                }
                for(int i=10;i<90;i++){
                    String v="20bcs1"+Integer.toString(i);
                    if(result.getContents().toString().equals(v)){
                        crt=true;
                        Calendar calendar =Calendar.getInstance();
                        String currentDate= DateFormat.getDateInstance().format(calendar.getTime());


                        FirebaseDatabase.getInstance().getReference().child("Dates").child(currentDate).push().setValue(result.getContents().toString());
                        Toast.makeText(this, result.getContents()+"is present",Toast.LENGTH_LONG).show();

                        FirebaseDatabase.getInstance().getReference().child("Student").child(result.getContents().toString()).push().setValue(currentDate);
                        //Toast.makeText(this, result.getContents()+"is present",Toast.LENGTH_LONG).show();

                    }
                }

                if(!crt){
                    Toast.makeText(this, "Use only valid Qr", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }


    }
}