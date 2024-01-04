package com.example.expensemanager.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.expensemanager.R;

public class ChangePassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        ImageButton returnButton = findViewById(R.id.imageButton3ReturnChangePassword);

        returnButton.setOnClickListener(v -> {
                    Intent intent1 = new Intent(ChangePassword.this, LogInForgotPassword.class);
                    startActivity(intent1);
                }
        );

        ImageButton EnterButton = findViewById(R.id.imageButton2ChangePassword);

        EnterButton.setOnClickListener(v -> {

                    EditText editTextPassword1 = findViewById(R.id.editTextTextPasswordNewPasswordChangePassword);
                    EditText editTextPassword2 = findViewById(R.id.editTextTextPassword2RepeatNewPasswordChangePassword);
                    String password = editTextPassword1.getText().toString();
                    String checkpassword = editTextPassword2.getText().toString();
                    if(password.equals(checkpassword)){
                        Intent intent = getIntent();
                        //if (intent != null && intent.hasExtra("EMAIL_EXTRA")) {
                            String receivedEmail = intent.getStringExtra("email");
                            // Hier können Sie die erhaltene E-Mail-Adresse verwenden
                        assert receivedEmail != null;
                        SignUpActivity.changePassword(this,receivedEmail,password);
                        //}

                        Toast.makeText(ChangePassword.this, "Passwort erfolgreich geändert!.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ChangePassword.this, LoginActivity.class));
                    }
                    else{
                        Toast.makeText(ChangePassword.this, "Passwörter Matchen sich nicht.", Toast.LENGTH_SHORT).show();
                    }

                }
        );

    }
}