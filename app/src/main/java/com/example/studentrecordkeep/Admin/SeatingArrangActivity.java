package com.example.studentrecordkeep.Admin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
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

public class SeatingArrangActivity extends AppCompatActivity {

    public static String selectedBlock;
    public static String selectedRoomNumber;
    private Boolean checkRecord = false;
    private Spinner examBlockNameSpinner;
    private List<AddingHall> examHallDetails;
    private DatabaseReference mDatabase;
    private List<String> blockNames;
    private Spinner examRoomNumberSpinner;

    private List<String> roomNumber = new ArrayList<>();
    private List<String> key = new ArrayList<>();
    private TextView numberOfStudents;
    private EditText studentRegNumber;
    private AddingHall addingHall;
    private String regNumber;
    private ProgressBar progressBar;
    private RelativeLayout seatingArrangementLayout;
    private DatabaseReference mDatabaseRefStu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seating_arrang);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Seating Arrangement");
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
        numberOfStudents = findViewById(R.id.number_of_students);
        studentRegNumber = findViewById(R.id.regNoText);
        progressBar = findViewById(R.id.spinner);
        seatingArrangementLayout = findViewById(R.id.seatingArrangementLayout);

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

                mDatabase = FirebaseDatabase.getInstance().getReference().child("add hall details")
                        .child(selectedBlock + "_" + selectedRoomNumber);
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        addingHall = dataSnapshot.getValue(AddingHall.class);
                        numberOfStudents.setText(addingHall.getStudentNumber());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void ShowExamHAllDetails() {

        progressBar.setVisibility(View.VISIBLE);
        seatingArrangementLayout.setClickable(false);

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
                seatingArrangementLayout.setClickable(true);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void submitDetails(View view) {

        regNumber = studentRegNumber.getText().toString().trim();

        if (TextUtils.isEmpty(regNumber)) {
            studentRegNumber.setError("Please Enter Registration Number");
            return;
        }

        mDatabaseRefStu = FirebaseDatabase.getInstance().getReference().child("add student")
                .child(regNumber);
        //Updating NUmber of students...

        mDatabase = FirebaseDatabase.getInstance().getReference().child("add hall details")
                .child(selectedBlock + "_" + selectedRoomNumber);
        AddingHall hall = new AddingHall();
        String stNumber = addingHall.getStudentNumber();
        int number = Integer.parseInt(stNumber);
        if (number > 0) {

            if (UpdatingStudentExamHall()) {

                number -= 1;
                hall.setStudentNumber(String.valueOf(number));
                hall.setRoomNumber(addingHall.getRoomNumber());
                hall.setBlockNumber(addingHall.getBlockNumber());
                hall.setHallName(addingHall.getHallName());
                if (hall.getStaffName() != null) {
                    hall.setStaffName(addingHall.getStaffName());
                } else {
                    hall.setStaffName(null);
                }
                mDatabase.setValue(hall);

                Toast.makeText(this, "Submitted!!", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(SeatingArrangActivity.this, "No Record found to this registration Number!"
                        , Toast.LENGTH_SHORT).show();
            }

        } else {
            Button submit = findViewById(R.id.submitStudent);
            submit.setClickable(false);
            submit.setBackgroundColor(getResources().getColor(R.color.colorAccent));

            Toast.makeText(this, "Room is Full!!", Toast.LENGTH_SHORT).show();
        }
        studentRegNumber.setText("");
        // Adding student to Exam HAll..

    }

    public boolean UpdatingStudentExamHall() {


        final DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("add student");
        final AddingStudent addingStudent = new AddingStudent();
        mDatabaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(regNumber)) {

                    mDatabaseRefStu.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            AddingStudent student = dataSnapshot.getValue(AddingStudent.class);
                            addingStudent.setName(student.getName());
                            addingStudent.setRegNo(student.getRegNo());
                            addingStudent.setDepName(student.getDepName());
                            addingStudent.setYear(student.getYear());
                            addingStudent.setSemester(student.getSemester());
                            addingStudent.setEmail(student.getEmail());
                            addingStudent.setRoomNumber(selectedRoomNumber);
                            addingStudent.setBlockName(selectedBlock);
                            mDatabaseRefStu.setValue(addingStudent);
                            checkRecord = true;
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                } else {
                    Toast.makeText(SeatingArrangActivity.this, "No Record found to this registration NUmber!"
                            , Toast.LENGTH_SHORT).show();
                    checkRecord = false;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Log.i("_che", checkRecord + "");
        return checkRecord;
    }

}
