package com.example.studentrecordkeep.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.studentrecordkeep.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddStudentActivity extends AppCompatActivity {

    // private EditText depNameButton;
    private Spinner spinner;
    private EditText nameButton;
    private EditText regNoButton;
    private EditText semesterButton;
    private EditText yearButton;
    private EditText EmailButton;
    private String depNoText;

    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Add Student detail");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        nameButton = findViewById(R.id.nameText);
        regNoButton = findViewById(R.id.RegNoText);
        //  depNameButton = findViewById(R.id.deptText);
        semesterButton = findViewById(R.id.semnameText);
        yearButton = findViewById(R.id.yearNameText);
        EmailButton = findViewById(R.id.emailnameText);

        spinner = (Spinner) findViewById(R.id.planets_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {

                depNoText = spinner.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void addDetails(View view) {

        String nameText = nameButton.getText().toString().trim();
        String regNoText = regNoButton.getText().toString().trim();
        // String depNoText = depNameButton.getText().toString().trim();
        String semText = semesterButton.getText().toString().trim();
        String yearText = yearButton.getText().toString().trim();
        String emailText = EmailButton.getText().toString().trim();

        if (TextUtils.isEmpty(nameText)) {
            nameButton.setError("Please Enter name");
            return;
        }
        if (TextUtils.isEmpty(regNoText)) {
            regNoButton.setError("Please Enter Registration");
            return;
        }
        if (TextUtils.isEmpty(semText)) {
            nameButton.setError("Please Enter Semester");
            return;
        }
        if (TextUtils.isEmpty(yearText)) {
            nameButton.setError("Please Enter Year");
            return;
        }
        if (TextUtils.isEmpty(emailText)) {
            nameButton.setError("Please Enter email");
            return;
        }

        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("add student");
        AddingStudent student = new AddingStudent();
        student.setName(nameText);
        student.setRegNo(regNoText);
        student.setDepName(depNoText);
        student.setSemester(semText);
        student.setEmail(emailText);
        student.setYear(yearText);
        student.setBlockName(null);
        student.setRoomNumber(null);

        mDatabaseRef.child(regNoText).setValue(student);

        Toast.makeText(this, "Adding details...", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(AddStudentActivity.this, AdminMainActivity.class);
        startActivity(intent);
        finish();

    }
}
