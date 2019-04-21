package com.example.studentrecordkeep.Admin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.studentrecordkeep.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class EditStaffIDActivity extends AppCompatActivity {

    public static String selectedBlock;
    public static String selectedRoomNumber;
    private DatabaseReference mDatabase;
    private Spinner examBlockNameSpinner;
    private Spinner examRoomNumberSpinner;
    private ProgressBar progressBar;
    private List<String> blockNames;
    private List<AddingHall> examHallDetails;
    private List<String> key = new ArrayList<>();
    private List<String> roomNumber = new ArrayList<>();
    private EditText staffIdText;
    private boolean checkId;
    private String staffID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_staff_id);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Staff");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        examHallDetails = new ArrayList<>();
        blockNames = new ArrayList<>();

        examBlockNameSpinner = findViewById(R.id.block_name_spinner);
        examRoomNumberSpinner = findViewById(R.id.room_number_spinner);
        progressBar = findViewById(R.id.spinner);
        staffIdText = findViewById(R.id.staffIdText);

        ShowExamHAllDetails();

        examBlockNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedBlock = examBlockNameSpinner.getItemAtPosition(i).toString();
                examHallDetails.clear();
                roomNumber.clear();
                for (String string : key) {
                    String[] parts = string.split("_");
                    String part1 = parts[0];
                    String part2 = parts[1];
                    if (selectedBlock.equals(part1)) {
                        roomNumber.add(part2);

                    }
                }

                ArrayAdapter<String> adapter =
                        new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,
                                roomNumber);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                examRoomNumberSpinner.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        examRoomNumberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                selectedRoomNumber = examRoomNumberSpinner.getItemAtPosition(i).toString().trim();

//                mDatabase = FirebaseDatabase.getInstance().getReference().child("add hall details")
//                        .child(selectedBlock + "_" + selectedRoomNumber);
//                mDatabase.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                        // addingHall = dataSnapshot.getValue(AddingHall.class);
//                        // numberOfStudents.setText(addingHall.getStudentNumber());
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void ShowExamHAllDetails() {

        progressBar.setVisibility(View.VISIBLE);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("add hall details");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                examHallDetails.clear();
                blockNames.clear();
                key.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    AddingHall hall = ds.getValue(AddingHall.class);
                    String string = ds.getKey();
                    key.add(string);
                    String[] parts = string.split("_");
                    String part1 = parts[0];
                    blockNames.add(part1);
                    examHallDetails.add(hall);

                }
                Set<String> s = new LinkedHashSet<String>(blockNames);
                List<String> aList = new ArrayList<String>(s);

                ArrayAdapter<String> adapter =
                        new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,
                                aList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                examBlockNameSpinner.setAdapter(adapter);
                progressBar.setVisibility(View.INVISIBLE);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void submitDetails(View view) {

        staffID = staffIdText.getText().toString().trim().toUpperCase();

        if (TextUtils.isEmpty(staffID)) {

            staffIdText.setError("Please Enter Staff Id");
            return;
        }

      CheckingStaffIDs();


    }

    private void CheckingStaffIDs() {
        DatabaseReference mReference = FirebaseDatabase.getInstance().getReference().child("add staff");

        final AddingHall addingHall = new AddingHall();
        final String[] staff_Id = new String[1];

        mReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChild(staffID)) {
                    checkId = true;
                    DatabaseReference mData = FirebaseDatabase.getInstance().getReference().child("add staff").child(staffID);
                    mData.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            AddingStaff staff = dataSnapshot.getValue(AddingStaff.class);
                            staff_Id[0] = staff.getName();

                            // Updating Hall Details...
                            final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                                    .child("add hall details")
                                    .child(selectedBlock + "_" + selectedRoomNumber);
                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot ds) {

                                    AddingHall hall = ds.getValue(AddingHall.class);
                                    addingHall.setHallName(hall.getHallName());
                                    addingHall.setBlockNumber(hall.getBlockNumber());
                                    addingHall.setRoomNumber(hall.getRoomNumber());
                                    addingHall.setStudentNumber(hall.getStudentNumber());
                                    addingHall.setStaffName(staff_Id[0]);
                                    databaseReference.setValue(addingHall);

                                    Log.i("_so", hall.getBlockNumber() + hall.getRoomNumber() + staff_Id);
                                    Toast.makeText(EditStaffIDActivity.this, "Added", Toast.LENGTH_SHORT).show();
                                    staffIdText.setText("");

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                } else {

                    Toast.makeText(EditStaffIDActivity.this, "No Staff ID Found", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
