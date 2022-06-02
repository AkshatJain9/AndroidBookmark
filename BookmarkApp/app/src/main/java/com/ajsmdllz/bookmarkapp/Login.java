package com.ajsmdllz.bookmarkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void LoginButton(View v) {
        EditText userName = findViewById(R.id.Username);
        EditText password = findViewById(R.id.Password);

        // Declare a buffered reader
        BufferedReader bufferedReader;
        // Reading/writing is exception prone and will need to be encapsulated in a try/catch block.
        try {
            // Create a new instance of a BufferedReader and provide an InputStreamReader with the file to read (and optionally, the
            //            encoding).
            bufferedReader = new BufferedReader(new InputStreamReader(getAssets().open("loginDetails.csv"), StandardCharsets.UTF_8));
            // Read one line at a time.
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Break each line into tokens (note that in CSV our values are comma separated)
                String[] tokens = line.split("\\|");
                boolean username = false;
                boolean pw = false;
                for (String token : tokens) {
                    String[] temp = token.split(":");
                    if (temp[0].equals("username")) {
                        username = temp[1].equals(userName.getText().toString());
                    }
                    if (temp[0].equals("password")) {
                        pw = temp[1].equals(password.getText().toString());
                    }
                }

                if (username && pw) {
                    User u = new User(
                            Integer.parseInt(tokens[0].substring(3)),
                            tokens[1].substring(9),
                            tokens[2].substring(9));
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("USER", u);
                    startActivity(intent);
                    return;
                }
            }
            bufferedReader.close(); // Close the reader to prevent problems.
            Toast.makeText(getApplicationContext(), "Not Valid", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();

        }
    }


}