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

public class AddStaffActivity extends AppCompatActivity {

    private EditText nameButton;
    // private EditText depNameButton;
    private EditText emailButton;
    private EditText staffIDText;
    private String staffDep;
    private Spinner spinner;

    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Add Staff detail");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        nameButton = findViewById(R.id.staff_nameText);
        // depNameButton = findViewById(R.id.staff_deptText);
        emailButton = findViewById(R.id.staff_emailnameText);
        staffIDText = findViewById(R.id.staff_id_Text);


        spinner = findViewById(R.id.depts_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {

                String staffDep1 = spinner.getItemAtPosition(i).toString();
                staffDep = staffDep1.replaceAll("\\s", "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void addDetails(View view) {

        String staffName = nameButton.getText().toString().trim();
        //String staffDep = depNameButton.getText().toString().trim();
        String staffEmail = emailButton.getText().toString().trim();
        String staffId = staffIDText.getText().toString().toUpperCase().trim();

        if (TextUtils.isEmpty(staffName)) {
            nameButton.setError("Please Enter name");
            return;
        }
        if (TextUtils.isEmpty(staffId)) {
            staffIDText.setError("Please Enter Department");
            return;
        }
        if (TextUtils.isEmpty(staffEmail)) {
            emailButton.setError("Please Enter Email");
            return;
        }

        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("add staff");

        AddingStaff staff = new AddingStaff();
        staff.setName(staffName);
        staff.setDepName(staffDep);
        staff.setEmail(staffEmail);
        staff.setStaffId(staffId);

        mDatabaseRef.child(staffId).setValue(staff);

        Toast.makeText(this, "Adding details...", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(AddStaffActivity.this, AdminMainActivity.class);
        startActivity(intent);
        finish();
    }
}
