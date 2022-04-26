package com.example.attendanceApp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.Locale;

public class QRpage extends AppCompatActivity {


    public int a[]=new int[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrpage);

        ImageView image;
        String text2Qr;


        Intent intent=getIntent();
        String hr= intent.getStringExtra(MainActivity.ID);
        String pass= intent.getStringExtra(MainActivity.Pass);

//        TextView text=findViewById(R.id.op);





        String v = null;

        for(int i=1;i<10;i++){
            String s="20C410"+Integer.toString(i);
            String p="20BCS1@0"+Integer.toString(i);

            if(hr.equals(s) && pass.equals(p)){
                v="20bcs10"+Integer.toString(i);


            }


        }
        for(int i=10;i<90;i++){
            String s="20C41"+Integer.toString(i);
            String p="20BCS1@"+Integer.toString(i);

            if(hr.equals(s) && pass.equals(p)){
                v="20bcs1"+Integer.toString(i);


            }
        }

        image=findViewById(R.id.i2);

        text2Qr = v;
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try{
            BitMatrix bitMatrix = multiFormatWriter.encode(text2Qr, BarcodeFormat.QR_CODE,350,350);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            image.setImageBitmap(bitmap);
        }
        catch (WriterException e){
            e.printStackTrace();
        }

        TextView t=findViewById(R.id.studdata);

        String user=pass.toLowerCase(Locale.ROOT).substring(0,6) + pass.toLowerCase(Locale.ROOT).substring(7);

        FirebaseDatabase.getInstance().getReference().child("Student").child(user).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot){

                for(DataSnapshot snapshot1 : snapshot.getChildren()){


                    a[0]++;

                }
                t.setText("Your Attendence is  " + a[0] +"/");
//                a[0]=(int) snapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
//        Toast.makeText(this, user, Toast.LENGTH_SHORT).show();
        FirebaseDatabase.getInstance().getReference().child("Dates").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot){

                for(DataSnapshot snapshot1 : snapshot.getChildren()){


                    a[1]++;

                }
                t.setText(t.getText().toString()+Integer.toString(a[1]));
//                a[1]=(int) snapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        Toast.makeText(QRpage.this, Integer.toString(a[0]), Toast.LENGTH_SHORT).show();



    }
}