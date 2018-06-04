package com.example.tusharjape.indialearnsapplication;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.Button;
public class MainActivity extends AppCompatActivity {

    Button teacher, center;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teacher = findViewById(R.id.teacher);
        center = findViewById(R.id.center);

        center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                    startActivity(new Intent(MainActivity.this, CenterUploadActivity.class), compat.toBundle());
                else*/
                startActivity(new Intent(MainActivity.this, CenterUploadActivity.class));
            }
        });

        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    startActivity(new Intent(MainActivity.this, TeacherSignUpActivity.class), compat.toBundle());
                }
                else*/
                startActivity(new Intent(MainActivity.this, TeacherSignUpActivity.class));
            }
        });

    }
}
