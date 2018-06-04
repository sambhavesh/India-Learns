package com.example.tusharjape.indialearnsapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TeacherSignUpActivity extends AppCompatActivity {

    EditText startTime, contactText, nameTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_sign_up);

        nameTxt = findViewById(R.id.name);
        startTime = findViewById(R.id.startTime);
        contactText = findViewById(R.id.contactNo);

        Button proceedBtn = findViewById(R.id.proceedBtn);
        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            Intent intent = new Intent(TeacherSignUpActivity.this, Maps2Activity.class);
            intent.putExtra("Name", nameTxt.getText().toString());
            intent.putExtra("Contact", contactText.getText().toString());
            intent.putExtra("Time", startTime.getText().toString());

            startActivity(intent);
            }
        });
    }
}
