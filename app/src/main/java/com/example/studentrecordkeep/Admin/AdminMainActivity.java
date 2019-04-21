package com.example.studentrecordkeep.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.studentrecordkeep.MainActivity;
import com.example.studentrecordkeep.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AdminMainActivity extends AppCompatActivity {

    private Button addStudentButton;
    private Button viewStudentButton;
    private Button addStaffButton;
    private Button viewStaffButton;
    private Button addExamHallButton;
    private Button viewSeatingButton;
    private Button seatAllotmentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Seat Allot");

        addStaffButton = findViewById(R.id.addStudentButton);
        viewStudentButton = findViewById(R.id.viewStudentButton);
        addStaffButton = findViewById(R.id.addStaffButton);
        viewStaffButton = findViewById(R.id.viewStaffButton);
        addExamHallButton = findViewById(R.id.addExamHallButton);
        viewSeatingButton = findViewById(R.id.viewSettingButton);
        seatAllotmentButton = findViewById(R.id.seatAllotmentButton);
    }

    public void logOut(View view) {

        FirebaseAuth.getInstance().signOut();

        Intent intent = new Intent(AdminMainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void addStudent(View view) {

        Intent intent = new Intent(AdminMainActivity.this, AddStudentActivity.class);
        startActivity(intent);
    }


    public void viewStudent(View view) {

        Intent intent = new Intent(AdminMainActivity.this, SearchStudentActivity.class);
        startActivity(intent);
    }

    public void addStaff(View view) {

        Intent intent = new Intent(AdminMainActivity.this, AddStaffActivity.class);
        startActivity(intent);
    }

    public void viewStaff(View view) {

        Intent intent = new Intent(AdminMainActivity.this, SearchStaffActivity.class);
        startActivity(intent);
    }

    public void addExamHall(View view) {

        Intent intent = new Intent(AdminMainActivity.this, AddExamHallActivity.class);
        startActivity(intent);
    }

    public void viewSeating(View view) {

        Intent intent = new Intent(AdminMainActivity.this, SearchExamHallActivity.class);
        startActivity(intent);
    }

    public void seatAllotment(View view) {

        Intent intent = new Intent(AdminMainActivity.this, SeatingArrangActivity.class);
        startActivity(intent);
    }

    public void clearData(View view) {

        Intent intent = new Intent(AdminMainActivity.this, ClearRecordsActivity.class);
        startActivity(intent);
    }

    public void staffSeatAllotment(View view) {
        Intent intent = new Intent(AdminMainActivity.this, EditStaffIDActivity.class);
        startActivity(intent);
    }
}
