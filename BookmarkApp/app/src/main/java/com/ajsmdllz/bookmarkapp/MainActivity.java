package com.ajsmdllz.bookmarkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> urls;
    ArrayAdapter<String> urlAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(getApplicationContext(), "Signed in Successfully!", Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        urls = new ArrayList<>();
        User user = (User) getIntent().getExtras().getSerializable("USER");



        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.data),
                    StandardCharsets.UTF_8));
            // Read one line at a time.
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Break each line into tokens (note that in CSV our values are comma separated)
                String[] tokens = line.split("\\[");

                if (Integer.parseInt(tokens[0]) == user.id &&
                        tokens[1].equals(user.Username) &&
                        tokens[2].equals(user.Password)) {
                    String[] urlStrings = tokens[3].split("\\|");
                    urls.addAll(Arrays.asList(urlStrings));
                    break;
                }

            }
            bufferedReader.close(); // Close the reader to prevent problems.
        } catch (IOException ignored) {}


        ListView listView = findViewById(R.id.URLList);
        urlAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, urls);
        listView.setAdapter(urlAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (urls.get(i) == null || urls.get(i).length() == 0) {
                    Toast.makeText(getApplicationContext(), "URL is invalid", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(MainActivity.this, ActivityWeb.class);
                intent.putExtra("URL", urls.get(i).toString());
                startActivity(intent);
            }
        });
    }


    public void addURLButton(View v) {
        EditText input = findViewById(R.id.inputText);
        String inputText = input.getText().toString();
        urls.add(inputText);
        urlAdapter.notifyDataSetChanged();
        input.setText("");

    }
}