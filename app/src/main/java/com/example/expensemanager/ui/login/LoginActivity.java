package com.example.expensemanager.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
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
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expensemanager.MainActivity;
import com.example.expensemanager.R;
import com.example.expensemanager.ui.login.LoginViewModel;
import com.example.expensemanager.ui.login.LoginViewModelFactory;
import com.example.expensemanager.databinding.ActivityLoginBinding;
import com.example.expensemanager.ui.reminder.ReminderFragment;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.login;
        //final ProgressBar loadingProgressBar = binding.loading;

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        Context context = this;

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                /*
                if (loginResult == null) {
                    return;
                }
                //  loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }

                setResult(Activity.RESULT_OK);


                // Get a Context object from the current activity
                Intent mainActivityIntent = new Intent(context, MainActivity.class);
                startActivity(mainActivityIntent);

                //Complete and destroy login activity once successful
                finish();

 */
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

        // Changes

        /*
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //      loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());

            }
        });

         */

        ImageButton imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(v -> {
            String enteredEmail = usernameEditText.getText().toString();
            String enteredPassword = passwordEditText.getText().toString();

            // Example: Check if the entered email and password are correct
            if (isValidAccount(enteredEmail, enteredPassword)) {
                // If the account is valid, start the MainActivity5
                // Get a Context object from the current activity
                Intent mainActivityIntent = new Intent(context, MainActivity.class);
                startActivity(mainActivityIntent);

                //Complete and destroy login activity once successful
                finish();
                Toast.makeText(LoginActivity.this, "Welcome :-)", Toast.LENGTH_SHORT).show();
            } else {
                // If the account is not valid, you may show an error message
                // or perform any other desired action.
                Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
            }
        });

        Button button = findViewById(R.id.buttonSignIn);

        button.setOnClickListener(v -> {
                    Intent intent1 = new Intent(LoginActivity.this, SignUpActivity.class);
                    startActivity(intent1);
                }
        );

        TextView textView = findViewById(R.id.textViewForgotPassword);

        textView.setOnClickListener(v -> {
                    Intent intent = new Intent(LoginActivity.this, LogInForgotPassword.class);
                    startActivity(intent);
                }

        );

    }


    private boolean isValidAccount(String enteredEmail, String enteredPassword) {
        // Beispiel: Hardcoded-Email und Passwort für Tests
        String correctEmail = "test@example.com";
        String correctPassword = "123456";

        // Überprüfen, ob die eingegebenen Daten mit den gespeicherten Daten übereinstimmen
        if (SignUpActivity.checkCredentials(this, enteredEmail, enteredPassword)) {
            // Die eingegebenen Daten stimmen mit den gespeicherten Daten überein
            return true;
        } else {
            // Die eingegebenen Daten stimmen nicht mit den gespeicherten Daten überein
            // Überprüfen Sie die eingegebenen Daten mit den festgelegten Werten für diese LoginActivity
            return enteredEmail.equals(correctEmail) && enteredPassword.equals(correctPassword);
        }
    }

    // End changes

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}