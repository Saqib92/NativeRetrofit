package com.thecodingchef.wowapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.thecodingchef.wowapp.utilities.General;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText email, password;
    Button loginBtn;
    ProgressBar loader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide(); // to hide Header/Actionbar
        loader = (ProgressBar) findViewById(R.id.loader);

        loader.setVisibility(View.GONE);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = email.getText().toString();
                String userPass = password.getText().toString();
                if(!General.validateEmail(userEmail)){
                    General.showToast(getApplicationContext(), "Please Enter Correct Email");
                    return;
                }
                if(General.isEmpty(password)){
                    General.showToast(getApplicationContext(), "Please Enter Password");
                    return;
                }
                loader.setVisibility(View.VISIBLE);
                loginBtn.setEnabled(false);
                General.hideKeyboard(MainActivity.this);
                callLoginApi(email.getText().toString(), password.getText().toString());
            }
        });

    }

    private void callLoginApi(String email, String password) {

        //making JSON with HASHMAP
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("password", password);
        map.put("grant_type", "password");
        map.put("client_id", "2");
        map.put("client_secret", "sNLIvwU2YHLfBHhBUhf4iryLY5e0hwhRH1uI6QkL");
        map.put("device_id", "");
        map.put("role", "User");

        Call<JsonElement> call = General.API_SERVICE.LoginApi(map);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    loader.setVisibility(View.GONE);
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(String.valueOf(response.body()));
                        String status = jsonObject.getString("status");
                        String msg = jsonObject.getString("message");
                        if(status == "true"){
                            General.showToast(getApplicationContext(), msg);
                             Intent it = new Intent(getApplicationContext(), Home.class);
                             it.putExtra("user", jsonObject.getString("data").toString());
                             startActivity(it);
                        }else{
                            loginBtn.setEnabled(true);
                            General.showToast(getApplicationContext(), msg);
                        }
                    } catch (JSONException ex) {
                        loginBtn.setEnabled(true);
                        System.out.println(ex);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                loader.setVisibility(View.GONE);
                loginBtn.setEnabled(true);
                try {
                    System.out.println("Faileddddd");
                } catch (Exception ex) {
                    System.out.println("here u go" + ex);
                }

            }
        });
    }
}
