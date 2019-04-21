package com.example.studentrecordkeep.Student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.studentrecordkeep.Admin.SearchStudentActivity;
import com.example.studentrecordkeep.Admin.ViewStudentsActivity;
import com.example.studentrecordkeep.R;

public class StudentSearchActivity extends AppCompatActivity {
    private EditText searchName;
    public static String searchStudName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_search);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("student detail");
        toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        searchName = findViewById(R.id.studRegNoText);
    }

    public void viewDetails(View view) {
        searchStudName = searchName.getText().toString().trim();

        if (TextUtils.isEmpty(searchStudName)){
            searchName.setError("Please Enter Registration Number");
            return;
        }
        Intent intent = new Intent(StudentSearchActivity.this, ShowStudentActivity.class);
        startActivity(intent);

    }
}
