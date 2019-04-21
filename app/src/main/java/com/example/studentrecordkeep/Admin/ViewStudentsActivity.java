package com.example.studentrecordkeep.Admin;

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

import com.example.studentrecordkeep.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewStudentsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<AddingStudent> studentList;
    private ViewStudentAdapter mAdapter;
    private DatabaseReference mDatabase;
    private ProgressBar progressBar;
    private TextView errorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_students);

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
        errorText = findViewById(R.id.errorDataInput);

        recyclerView = findViewById(R.id.view_student_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Bundle bundle = getIntent().getExtras();

        String stuff = bundle.getString("Search");

        if (stuff.equals("1")) {
            Log.i("_Val", stuff);
            Init();
        } else {
            Log.i("_Val", stuff);
            Init1();
        }


    }

    private void Init1() {

        progressBar.setVisibility(View.VISIBLE);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("add student");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                studentList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    AddingStudent st = ds.getValue(AddingStudent.class);
                    String dept = st.getDepName();
                    String s1 = dept.replaceAll("\\s", "");

//                    Log.i("_V",st.getYear()+"=="+SearchStudentActivity.searchStudentYearName);
//                    Log.i("_v",st.getDepName()+"=="+SearchStudentActivity.searchStuDeptName);
                    if (st.getYear().equals(SearchStudentActivity.searchStudentYearName) &&
                            s1.toUpperCase().equals(SearchStudentActivity.searchStuDeptName.toUpperCase())) {

                        studentList.add(st);
                    }
                }

                progressBar.setVisibility(View.INVISIBLE);
                mAdapter = new ViewStudentAdapter(ViewStudentsActivity.this, studentList);
                recyclerView.setAdapter(mAdapter);

                Log.i("_RINSIZE", mAdapter.getItemCount() + "");
                if (mAdapter.getItemCount() <= 0) {
                    Log.i("_RIN", "TRUE");
                    errorText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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

//                   Log.i("_Name",SearchStudentActivity.searchStuName);
//                   Log.i("_NameStu",ds.getKey());

                    if (ds.getKey().equals(SearchStudentActivity.searchStuName)) {
                        studentList.add(st);
                    }

                }
                progressBar.setVisibility(View.INVISIBLE);
                mAdapter = new ViewStudentAdapter(ViewStudentsActivity.this, studentList);
                recyclerView.setAdapter(mAdapter);

                Log.i("_RINSIZE", mAdapter.getItemCount() + "");
                if (mAdapter.getItemCount() <= 0) {
                    Log.i("_RIN", "TRUE");
                    errorText.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
