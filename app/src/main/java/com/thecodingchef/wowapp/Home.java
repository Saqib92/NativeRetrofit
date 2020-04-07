package com.thecodingchef.wowapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

public class Home extends AppCompatActivity {
    TextView names, emails, contacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

         names = (TextView) findViewById(R.id.name);
         emails = (TextView) findViewById(R.id.email);
         contacts = (TextView) findViewById(R.id.contact);

        JSONObject userData = null;
        String name, email, contact = "";

        try {
            userData = new JSONObject(getIntent().getStringExtra("user"));
            name = userData.getJSONObject("data").getString("first_name") + " " + userData.getJSONObject("data").getString("last_name");
            email = userData.getJSONObject("data").getString("email");
            contact = userData.getJSONObject("data").getString("contact_no");

            names.setText("Welcome: " + name);
            emails.setText("Email: "+ email);
            contacts.setText("Email: "+ contact);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
