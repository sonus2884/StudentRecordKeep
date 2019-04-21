package com.example.studentrecordkeep.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentrecordkeep.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddExamHallActivity extends AppCompatActivity {

    private EditText hallNameText;
    private EditText roomNumberText;
    private EditText blockNumberText;
    private EditText studentNumberText;
    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exam_hall);


        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Add exam hall detail");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        hallNameText = findViewById(R.id.hallnameText);
        roomNumberText = findViewById(R.id.roomNoText);
        blockNumberText = findViewById(R.id.blockNoText);
        studentNumberText = findViewById(R.id.stuNoText);

    }

    public void addDetails(View view) {

        String hallName = hallNameText.getText().toString().trim();
        String roomNumber = roomNumberText.getText().toString().trim();
        String blockNumber = blockNumberText.getText().toString().trim();
        String studentNumber = studentNumberText.getText().toString().trim();

        if (TextUtils.isEmpty(hallName)) {
            hallNameText.setError("Please Enter hall name");
            return;
        }
        if (TextUtils.isEmpty(roomNumber)) {
            roomNumberText.setError("Please Enter Room Number");
            return;
        }
        if (TextUtils.isEmpty(blockNumber)) {
            blockNumberText.setError("Please Enter Block Number");
            return;
        }
        if (TextUtils.isEmpty(studentNumber)) {
            studentNumberText.setError("Please Enter Student Number");
            return;
        }


        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("add hall details");
        AddingHall hall = new AddingHall();
        hall.setHallName(hallName);
        hall.setBlockNumber(blockNumber);
        hall.setRoomNumber(roomNumber);
        hall.setStudentNumber(studentNumber);
        hall.setStaffName(null);

        mDatabaseRef.child(blockNumber.toUpperCase() + "_" + roomNumber).setValue(hall);
        Toast.makeText(this, "Adding details...", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(AddExamHallActivity.this, AdminMainActivity.class);
        startActivity(intent);
        finish();

    }

}
