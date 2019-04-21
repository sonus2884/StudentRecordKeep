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

public class ViewStaffActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<AddingStaff> staffList;
    private ViewStaffAdapter mAdapter;
    private DatabaseReference mDatabase;
    private ProgressBar progressBar;
    private TextView errorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_staff);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Staff List");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        staffList = new ArrayList<>();

        progressBar = findViewById(R.id.spinner);
        errorText = findViewById(R.id.errorDataInput);

        recyclerView = findViewById(R.id.view_staff_list);
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

    private void Init() {

        progressBar.setVisibility(View.VISIBLE);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("add staff");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                staffList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    AddingStaff st = ds.getValue(AddingStaff.class);
                    String dept = st.getDepName();
                    String s1 = dept.replaceAll("\\s", "");

                    if (s1.toUpperCase().equals(SearchStaffActivity.searchStaffName.toUpperCase())) {
                        staffList.add(st);
                    }
                }

                progressBar.setVisibility(View.INVISIBLE);
                mAdapter = new ViewStaffAdapter(ViewStaffActivity.this, staffList);
                recyclerView.setAdapter(mAdapter);
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

    private void Init1() {

        Log.i("_ID", SearchStaffActivity.searchStaffID);
        progressBar.setVisibility(View.VISIBLE);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("add staff");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                staffList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    AddingStaff st = ds.getValue(AddingStaff.class);

                    if (ds.getKey().toUpperCase().equals(SearchStaffActivity.searchStaffID.toUpperCase())) {
                        staffList.add(st);
                    }
                }

                progressBar.setVisibility(View.INVISIBLE);
                mAdapter = new ViewStaffAdapter(ViewStaffActivity.this, staffList);
                recyclerView.setAdapter(mAdapter);
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
