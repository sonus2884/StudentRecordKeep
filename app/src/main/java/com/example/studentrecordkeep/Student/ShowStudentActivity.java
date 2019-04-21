package com.example.studentrecordkeep.Student;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.studentrecordkeep.Admin.AddingStudent;
import com.example.studentrecordkeep.Admin.SearchStudentActivity;
import com.example.studentrecordkeep.Admin.ViewStudentAdapter;
import com.example.studentrecordkeep.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowStudentActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<AddingStudent> studentList;
    private StudentDetailsAdapter mAdapter;
    private DatabaseReference mDatabase;
    private ProgressBar progressBar;
    private TextView errorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_student);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Student List");
        toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        studentList = new ArrayList<>();

        progressBar = findViewById(R.id.spinner);

        recyclerView = findViewById(R.id.show_student_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Init();
    }

    private void Init() {
        progressBar.setVisibility(View.VISIBLE);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("add student");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                studentList.clear();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    AddingStudent st = ds.getValue(AddingStudent.class);


                    if (ds.getKey().equals(StudentSearchActivity.searchStudName)) {
                        studentList.add(st);
                    }

                }
                progressBar.setVisibility(View.INVISIBLE);
                mAdapter = new StudentDetailsAdapter(ShowStudentActivity.this, studentList);
                recyclerView.setAdapter(mAdapter);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
