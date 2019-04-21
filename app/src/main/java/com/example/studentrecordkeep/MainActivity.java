package com.example.studentrecordkeep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.studentrecordkeep.Admin.AdminLoginActivity;
import com.example.studentrecordkeep.Student.StudentSearchActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonAdmin;
    private Button buttonStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Seat Allot");
        buttonAdmin = findViewById(R.id.buttonAdmin);
        buttonStudent = findViewById(R.id.buttonStudent);
    }


    public void adminClick(View view) {

        Toast.makeText(this, "Admin", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, AdminLoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void studentClick(View view) {

        Toast.makeText(this, "Student", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(MainActivity.this, StudentSearchActivity.class);
//        startActivity(intent);

    }

}
