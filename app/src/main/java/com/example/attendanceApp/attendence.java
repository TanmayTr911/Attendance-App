package com.example.attendanceApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class attendence extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence);


    }

    public void datechanger(View view){
        DatePickerDialog datePickerDialog =new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        );
        datePickerDialog.show();
    }
    String d;
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

        d=day + "-" + getMonthFormat(month) + "-" + year;
        Toast.makeText(this, d, Toast.LENGTH_SHORT).show();
    }
    public void getdatedata(View view){

        ListView list = findViewById(R.id.list);
        ArrayList <String> a=new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.items,a);
        list.setAdapter(adapter);

        FirebaseDatabase.getInstance().getReference().child("Dates").child(d).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    a.clear();
                    a.add(d);
                    for(DataSnapshot snapshot1 : snapshot.getChildren()){


                        a.add(snapshot1.getValue(String.class));

                    }
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void emptylist(View view){

        ListView list = findViewById(R.id.list);
        ArrayList <String> a=new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.items,a);
        list.setAdapter(adapter);

        a.clear();

    }

    public void getrnodata(View view){

        ListView list = findViewById(R.id.list);
        ArrayList <String> a=new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.items,a);
        list.setAdapter(adapter);

        EditText rno=findViewById(R.id.rno);
        String roll=rno.getText().toString();


        FirebaseDatabase.getInstance().getReference().child("Student").child(roll).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    String s="Dates Present";
                    a.add(s);
                    a.clear();

                    for(DataSnapshot snapshot1 : snapshot.getChildren()){

                        Collections.sort(a);
                        a.add(snapshot1.getValue(String.class));

                    }

                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private String getMonthFormat(int mn)
    {
        if(mn == 0)
            return "Jan";
        if(mn == 1)
            return "Feb";
        if(mn == 2)
            return "Mar";
        if(mn == 3)
            return "Apr";
        if(mn == 4)
            return "May";
        if(mn == 5)
            return "Jun";
        if(mn == 6)
            return "Jul";
        if(mn == 7)
            return "Aug";
        if(mn == 8)
            return "Sep";
        if(mn == 9)
            return "Oct" ;
        if(mn == 10)
            return "Nov";
        if(mn== 11)
            return "Dec";

        //default should never happen
        return "JAN";
    }


}