package com.example.tusharjape.indialearnsapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class CenterUploadActivity extends AppCompatActivity {

    int starttime, endtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_upload);

        final EditText children = findViewById(R.id.studentCapacity);
        final EditText name = findViewById(R.id.name);
        final EditText contact = findViewById(R.id.contactNo);

        final NumberPicker beginHourPicker = findViewById(R.id.beginHourPicker);
        beginHourPicker.setMinValue(1);
        beginHourPicker.setMaxValue(24);
        beginHourPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                starttime = newVal;
            }
        });

        final NumberPicker endHourPicker = findViewById(R.id.endHourPicker);
        endHourPicker.setMinValue(1);
        endHourPicker.setMaxValue(24);
        endHourPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                endtime = newVal;
            }
        });

        Button selectLocationBtn = findViewById(R.id.selectLocationBtn);
        selectLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(CenterUploadActivity.this, MapActivity.class);
            while(children.getText().toString().equals("")){
                Toast.makeText(CenterUploadActivity.this, "Enter no of children!", Toast.LENGTH_SHORT).show();
            }
            while (name.getText().toString().equals("")){
                Toast.makeText(CenterUploadActivity.this, "Enter your name!", Toast.LENGTH_SHORT).show();
            }

            intent.putExtra("Children", children.getText().toString());
            intent.putExtra("Start time", Integer.toString(starttime));
            intent.putExtra("End time", Integer.toString(endtime));
            intent.putExtra("Name", name.getText().toString());
            intent.putExtra("Contact", contact.getText().toString());
            startActivity(intent);
            }
        });
    }
}
