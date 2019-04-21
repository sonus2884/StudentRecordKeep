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

import com.example.studentrecordkeep.R;

public class SearchStudentActivity extends AppCompatActivity {

    public static String searchStuName;
    public static String searchStuDeptName;
    public static String searchStudentYearName;
    private EditText searchName;
    private Spinner spinner;
    private EditText searchYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_student);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Search Student");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        searchName = findViewById(R.id.student_name_Text);

        searchYear = findViewById(R.id.student_year_Text);
        spinner = findViewById(R.id.stu_dept_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {

                String s1 = spinner.getItemAtPosition(i).toString();
                searchStuDeptName = s1.replaceAll("\\s", "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void searchStudent(View view) {

        searchStuName = searchName.getText().toString().trim();

        if (TextUtils.isEmpty(searchStuName)) {
            searchName.setError("Please Enter Registration Number");
            return;
        }
        Bundle bundle = new Bundle();

        Intent intent = new Intent(SearchStudentActivity.this, ViewStudentsActivity.class);
        bundle.putString("Search", "1");
        intent.putExtras(bundle);

        startActivity(intent);

    }

    public void searchStudentDept(View view) {

        searchStudentYearName = searchYear.getText().toString().toUpperCase().trim();

        if (TextUtils.isEmpty(searchStudentYearName)) {
            searchYear.setError("Please Enter year");
            return;
        }
        Bundle bundle = new Bundle();
        Intent intent = new Intent(SearchStudentActivity.this, ViewStudentsActivity.class);
        bundle.putString("Search", "2");
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
