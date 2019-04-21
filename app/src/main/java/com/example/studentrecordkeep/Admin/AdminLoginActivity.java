package com.example.studentrecordkeep.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.studentrecordkeep.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText passwordText;
    private EditText emailText;
    private ImageView visibilityOn;
    private ImageView visibilityOff;
    private ProgressBar progressBar;
    private RelativeLayout backgroundRelativeLayout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        // FirebaseApp.initializeApp(this);

        TextInputLayout emailTextWrapper = findViewById(R.id.emailTextWrapper);
        TextInputLayout passwordTextWrapper = findViewById(R.id.passwordTextWrapper);
        passwordText = findViewById(R.id.passwordText);
        emailText = findViewById(R.id.emailText);
        progressBar = findViewById(R.id.spinner);
        visibilityOn = findViewById(R.id.visibilityOn);
        visibilityOff = findViewById(R.id.visibilityOff);
        backgroundRelativeLayout = findViewById(R.id.backgroundRelativeLayout);
        backgroundRelativeLayout.setOnClickListener(this);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Log in");

        emailTextWrapper.setHint("Username");
        passwordTextWrapper.setHint("Password");
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

    }

    public void signIn(View view) {

        String email = emailText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {

            // Toast.makeText(LoginActivity.this, "Please enter a email!", Toast.LENGTH_SHORT).show();
            emailText.setError("Please enter a email!");
            return;
        }
        if (TextUtils.isEmpty(password)) {

            //Toast.makeText(LoginActivity.this, "Please enter  password!", Toast.LENGTH_SHORT).show();
            passwordText.setError("Please enter  password!");
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        Log.i("_Email", email);
        Log.i("_Password", password);


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

//                            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
//                            String RegisteredUserID = currentUser.getUid();
//                            Toast.makeText(AdminLoginActivity.this, RegisteredUserID, Toast.LENGTH_LONG).show();
//                            if (RegisteredUserID.equals("Zl5LXNbVzmYk4AgH8tF1TlLivvv2")) {
                            progressBar.setVisibility(View.INVISIBLE);
                            Intent intent = new Intent(AdminLoginActivity.this, AdminMainActivity.class);
                            startActivity(intent);
                            finish();
                            // }
                        } else {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(AdminLoginActivity.this, "You have not access of Admin",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    public void viewPassword(View view) {

        if (view.getId() == R.id.visibilityOn) {

            passwordText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            visibilityOn.setVisibility(View.INVISIBLE);
            visibilityOff.setVisibility(View.VISIBLE);
            passwordText.setSelection(passwordText.length());

        } else {

            passwordText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            visibilityOn.setVisibility(View.VISIBLE);
            visibilityOff.setVisibility(View.INVISIBLE);
            passwordText.setSelection(passwordText.length());
        }
    }


}
