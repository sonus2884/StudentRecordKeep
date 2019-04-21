package com.example.studentrecordkeep.Admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.studentrecordkeep.R;

public class SearchExamHallActivity extends AppCompatActivity {

    private EditText examHallText;
    public static String examBlockName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_exam_hall);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Search Exam Hall");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        examHallText = findViewById(R.id.hall_name_Text);
    }

    public void searchExamHall(View view) {

        examBlockName = examHallText.getText().toString().trim();

        if (TextUtils.isEmpty(examBlockName)){
            examHallText.setError("Please Enter Block Name");
            return;
        }
        Intent intent = new Intent(SearchExamHallActivity.this, ViewExamHallActivity.class);
        startActivity(intent);
    }
}
