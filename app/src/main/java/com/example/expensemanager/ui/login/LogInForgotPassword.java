package com.example.expensemanager.ui.login;

import static com.example.expensemanager.ui.login.SignUpActivity.emailList;
import static com.example.expensemanager.ui.login.SignUpActivity.userCredentials;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.expensemanager.R;

public class LogInForgotPassword extends AppCompatActivity {
    public String emailToCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_forgot_password);

        ImageButton returnButton = findViewById(R.id.imageButton3ReturnLogInForgotPassword);

        returnButton.setOnClickListener(v -> {
                    Intent intent1 = new Intent(LogInForgotPassword.this, LoginActivity.class);
                    startActivity(intent1);
                }
        );


        ImageButton imageButton = findViewById(R.id.imageButton2ForgotPassword);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hier die E-Mail-Adresse überprüfen
                EditText editTextEmail = findViewById(R.id.editTextTextEmailAddressForgotPassword);
                emailToCheck = editTextEmail.getText().toString(); // Hier die tatsächliche E-Mail-Adresse abrufen

                if (emailList.contains(emailToCheck)) {
                    // Die eingegebenen Daten stimmen mit den gespeicherten Daten überein
                     showPopup();
                }
                else {
                    // Die E-Mail-Adresse ist nicht in der HashMap
                    Toast.makeText(LogInForgotPassword.this, "E-Mail-Adresse nicht gefunden", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showPopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("E-Mail gefunden");
        builder.setMessage("Die E-Mail-Adresse wurde gefunden. Klicken Sie auf den Link, um fortzufahren.");

        builder.setPositiveButton("Weiter", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Hier den Code für den Übergang zu einer neuen Aktivität einfügen
                 Intent intent = new Intent(LogInForgotPassword.this, ChangePassword.class);
                 intent.putExtra("email", emailToCheck);
                 startActivity(intent);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

        // Popup nach einer bestimmten Zeit automatisch schließen
        final Handler handler = new Handler();
        final int delayMillis = 5000; // 5000 Millisekunden (5 Sekunden)
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        }, delayMillis);
    }
}