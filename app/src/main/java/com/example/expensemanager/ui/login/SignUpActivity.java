package com.example.expensemanager.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.expensemanager.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "UserPrefs";
    private static final String PREF_EMAIL = "email";
    private static final String PREF_PASSWORD = "password";
    public static Map<String, String> userCredentials = new HashMap<>();

    public static ArrayList<String> emailList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);




        // Return button

        ImageButton returnButton = findViewById(R.id.imageButton3SignUpReturn);

        returnButton.setOnClickListener(v -> {
                    Intent intent1 = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(intent1);
                }
        );


        //Moves to activity 2 Sign up
        ImageButton signUpButton = findViewById(R.id.imageButton2SignUp);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Eingabe aus den Feldern abrufen
                EditText editTextEmail = findViewById(R.id.editTextTextEmailAddressSignUp);
                EditText editTextPassword = findViewById(R.id.editTextTextPasswordSignUp);
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                // Überprüfen, ob die E-Mail-Adresse gültig ist
                if (isValidEmail(email)) {
                    if(isValidPassword(password)){
                        saveUserCredentials(email, password);
                        emailList.add(email);
                        Toast.makeText(SignUpActivity.this, "Registrierung erfolgreich", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                    }
                    // Benutzerdaten speichern

                    else{
                        // Fehlermeldung für ungültiges Passwort
                        Toast.makeText(SignUpActivity.this, "Passwort muss mindestens 4 Zeichen lang sein", Toast.LENGTH_SHORT).show();
                    }


                    // Hier können Sie zur nächsten Aktivität wechseln oder weitere Aktionen durchführen
                } else {
                    Toast.makeText(SignUpActivity.this, "Ungültige E-Mail-Adresse", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveUserCredentials(String email, String password) {
        // Benutzerdaten im SharedPreferences speichern
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString(PREF_EMAIL, email);
        editor.putString(PREF_PASSWORD, password);
        editor.apply();
    }

    public static boolean checkCredentials(Context context, String email, String password) {
        // Benutzerdaten aus SharedPreferences abrufen
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String storedEmail = prefs.getString(PREF_EMAIL, "");
        String storedPassword = prefs.getString(PREF_PASSWORD, "");

        // Überprüfen, ob die eingegebenen Daten mit den gespeicherten übereinstimmen
        return email.equals(storedEmail) && password.equals(storedPassword);
    }

    private boolean isValidEmail(CharSequence target) {
        // Überprüfen, ob die E-Mail-Adresse gültig ist
        return Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private boolean isValidPassword(String password) {
        // Überprüfen, ob das Passwort die Mindestlänge von 4 Zeichen hat
        return password.length() >= 4;
    }
    public static void changePassword(Context context, String email, String newPassword) {
        // Benutzerdaten aus SharedPreferences abrufen
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String storedEmail = prefs.getString(PREF_EMAIL, "");
        String storedPassword = prefs.getString(PREF_PASSWORD, "");

        // Überprüfen, ob die eingegebene E-Mail-Adresse mit der gespeicherten übereinstimmt
        if (email.equals(storedEmail)) {
            // Die E-Mail-Adresse ist in der SharedPreferences vorhanden
            // Das Passwort aktualisieren und speichern
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(PREF_PASSWORD, newPassword);
            editor.apply();

            // Optional: Benutzer über erfolgreiche Änderung informieren

        } else {
            // Die E-Mail-Adresse ist nicht in der SharedPreferences vorhanden
            // Optional: Benutzer über fehlerhafte E-Mail-Adresse informieren

        }
    }





}
