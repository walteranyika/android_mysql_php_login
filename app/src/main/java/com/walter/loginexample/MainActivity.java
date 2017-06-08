package com.walter.loginexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
   EditText inputUsername, inputPassword;
    ProgressBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputPassword= (EditText) findViewById(R.id.inputPassword);
        inputUsername= (EditText) findViewById(R.id.inputUsername);
        bar= (ProgressBar) findViewById(R.id.progressBar);
    }

    public void login(View view) {
        String username= inputUsername.getText().toString().trim();
        String password= inputPassword.getText().toString().trim();
        String url ="http://192.168.0.44/andoidmysql/andoid.php";
        RequestParams params=new RequestParams();
        params.put("username",username);
        params.put("password",password);

        AsyncHttpClient client=new AsyncHttpClient();
        bar.setVisibility(View.VISIBLE);

        client.post(url, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Failed To Get Data", Toast.LENGTH_SHORT).show();
                bar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                bar.setVisibility(View.INVISIBLE);
               if (responseString.toLowerCase().contains("success"))
               {
                   Toast.makeText(MainActivity.this, "Successfully logged in", Toast.LENGTH_SHORT).show();

               }else {
                   Toast.makeText(MainActivity.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
               }
            }
        });


    }
}
