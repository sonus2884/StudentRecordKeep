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

public class SearchStaffActivity extends AppCompatActivity {

    public static String searchStaffName;
    public static String searchStaffID;
    private Spinner spinner;
    private EditText staffIdText;
    // private EditText searchName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_staff);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Search Staff");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //searchName = findViewById(R.id.staff_name_Text);

        staffIdText = findViewById(R.id.staff_ID_Text);
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

                String s1 = spinner.getItemAtPosition(i).toString();
                searchStaffName = s1.replaceAll("\\s", "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void searchStaff(View view) {

        Bundle bundle = new Bundle();
        Intent intent = new Intent(SearchStaffActivity.this, ViewStaffActivity.class);
        bundle.putString("Search", "1");
        intent.putExtras(bundle);
        startActivity(intent);

    }

    public void searchStaffID(View view) {

        searchStaffID = staffIdText.getText().toString().toUpperCase().trim();

        if (TextUtils.isEmpty(searchStaffID)) {
            staffIdText.setError("Please Enter Staff ID");
            return;
        }

        Bundle bundle = new Bundle();
        Intent intent = new Intent(SearchStaffActivity.this, ViewStaffActivity.class);
        bundle.putString("Search", "2");
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

