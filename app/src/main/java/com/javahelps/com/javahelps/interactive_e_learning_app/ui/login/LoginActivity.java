package com.javahelps.com.javahelps.interactive_e_learning_app.ui.login;

import android.annotation.SuppressLint;
import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.javahelps.com.javahelps.interactive_e_learning_app.Courses.CourseSelection;
import com.javahelps.com.javahelps.interactive_e_learning_app.Professor.HandledCourses;
import com.javahelps.com.javahelps.interactive_e_learning_app.R;
import com.javahelps.com.javahelps.interactive_e_learning_app.databinding.ActivityLoginBinding;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;
    TextView forgot;
    EditText id;
    EditText pass;
    public Button login;

    DatabaseReference fb;
    FirebaseDatabase rootNode;

    public static String user_id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            binding = ActivityLoginBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

        rootNode = FirebaseDatabase.getInstance();

        fb = rootNode.getReference().child("users");


        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        forgot = findViewById(R.id.textView3);
        login = findViewById(R.id.login);

        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.login;
        final ProgressBar loadingProgressBar = binding.loading;

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(loginFormState.getUsernameError());
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });


        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);


            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(@NonNull View v) {
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());

                id = findViewById(R.id.username);
                pass = findViewById(R.id.password);

                    fb.child(id.getText().toString()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                            if (snapshot.exists()) {

                                User user = snapshot.getValue(User.class);

                                if (pass.getText().toString().equals(user.getPassword()) && user.getUser_type().equals("student")) {

                                    user_id = user.getUser_id();

                                    Toast.makeText(getApplicationContext(), "Successfully Login", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(LoginActivity.this, CourseSelection.class);
                                    startActivity(intent);
                                    //Complete and destroy login activity once successful
                                    finish();

                                }else if(pass.getText().toString().equals(user.getPassword()) && user.getUser_type().equals("professor")){

                                    user_id = user.getUser_id();

                                    Toast.makeText(getApplicationContext(), "Successfully Login", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(LoginActivity.this, HandledCourses.class);
                                    startActivity(intent);
                                    //Complete and destroy login activity once successful
                                    finish();

                                } else {

                                    forgot.setText("User Id or Password is Invalid!");
                                    id.setText(null);
                                    pass.setText(null);
                                }
                            }
                            else{

                                Toast.makeText(getApplicationContext(), "This User does not Exist!", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                            Toast.makeText(getApplicationContext(), "Database Error", Toast.LENGTH_LONG).show();
                        }
                    });



            }
        });


    }

            @Override
            public void onBackPressed() {
                super.onBackPressed();
            }

            private void updateUiWithUser(LoggedInUserView model) {
                String welcome = getString(R.string.welcome) + model.getDisplayName();
                // TODO : initiate successful logged in experience
                Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG);
            }

            private void showLoginFailed(@StringRes Integer errorString) {
                Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
            }

            @SuppressLint("SetTextI18n")
            public void forgotPass(View view) {

                forgot.setText("Please Contact to the Institute to Reset Password.");

            }

}
