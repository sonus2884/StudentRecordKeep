package com.example.studentrecordkeep.Admin;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class ViewExamHallActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<AddingHall> examHallList;
    private ViewExamHallAdapter mAdapter;
    private DatabaseReference mDatabase;
    private ProgressBar progressBar;
    private TextView errorText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exam_hall);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Exam Hall");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        examHallList = new ArrayList<>();

        progressBar = findViewById(R.id.spinner);
        errorText = findViewById(R.id.errorDataInput);

        recyclerView = findViewById(R.id.view_exam_hall_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Init();
    }

    private void Init() {

        progressBar.setVisibility(View.VISIBLE);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("add hall details");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                examHallList.clear();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    AddingHall hall = ds.getValue(AddingHall.class);

//                   Log.i("_Name",SearchStudentActivity.searchStuName);
                   // Log.i("_NameStu",ds.getKey());
                    String string = ds.getKey();
                    String[] parts = string.split("_");
                    String part1 = parts[0]; // 004
                    //Log.i("_Name",part1+"=="+SearchExamHallActivity.examBlockName);
                   // String part2 = parts[1]; // 034556

                    if (part1.equals(SearchExamHallActivity.examBlockName.toUpperCase())) {
                        examHallList.add(hall);
                    }


                }

                progressBar.setVisibility(View.INVISIBLE);
                mAdapter = new ViewExamHallAdapter(ViewExamHallActivity.this, examHallList);
                recyclerView.setAdapter(mAdapter);
                if (mAdapter.getItemCount() <=0){
                    Log.i("_RIN","TRUE");
                    errorText.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
