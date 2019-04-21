package com.example.studentrecordkeep.Admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentrecordkeep.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ClearRecordsActivity extends AppCompatActivity {

    private EditText clearDataText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear_records);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Clear Records");
        toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        clearDataText = findViewById(R.id.header_name);
    }

    public void deleteRecords(View view) {

        String headerName = clearDataText.getText().toString().trim();
        if (TextUtils.isEmpty(headerName)) {
            clearDataText.setError("Please Enter the name!");
            return;
        }

        if (headerName.equals("studentrecordkeep")) {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {
                        appleSnapshot.getRef().removeValue();
                    }
                    Toast.makeText(ClearRecordsActivity.this, "All Records are removed!", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("AdminMain", "onCancelled", databaseError.toException());
                }
            });
        } else {
            Toast.makeText(ClearRecordsActivity.this, "Please Enter Correct Name!", Toast.LENGTH_LONG).show();
        }

        clearDataText.setText("");
    }
}
