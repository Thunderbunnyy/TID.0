package com.example.tid0;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText pw;
    Button btnLogin;
    Button btnSignin;
    TextView errorTxt;
    final Context context = this;
    public static final String USER = "UserPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.div_id);
        pw = findViewById(R.id.pw);

        btnLogin = findViewById(R.id.btn_login);
        btnSignin = findViewById(R.id.btn_signin);

        errorTxt = findViewById(R.id.error_txt);

        btnSignin.setOnClickListener((View.OnClickListener) v -> {

            LayoutInflater li = LayoutInflater.from(context);
            View promptsView = li.inflate(R.layout.prompt, null);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);

            // set prompts.xml to alertdialog builder
            alertDialogBuilder.setView(promptsView);

            final EditText userInput = (EditText) promptsView
                    .findViewById(R.id.id_admin);

            final EditText pwInput = (EditText) promptsView
                    .findViewById(R.id.pw_admin);

            final TextView errTxt = (TextView) promptsView
                    .findViewById(R.id.error_txt);

            // set dialog message
            alertDialogBuilder
                    .setCancelable(false)
                    .setNeutralButton("se connecter",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {

                                    if(userInput.getText().toString().equals("admin") && pwInput.getText().toString().equals("1234")){

                                       // Toast.makeText(MainActivity.this, "s7i7", Toast.LENGTH_SHORT).show();
                                        Intent signIn = new Intent(MainActivity.this, SignIn.class);
                                        startActivity(signIn);
                                    }else{
                                      //  Toast.makeText(MainActivity.this, "lé", Toast.LENGTH_SHORT).show();
                                        errorTxt.setVisibility(View.VISIBLE);
                                    }
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();
            // show it
            alertDialog.show();
        });

        btnLogin.setOnClickListener(v -> {

            SharedPreferences prefs = getSharedPreferences(USER, MODE_PRIVATE);
            String usr = prefs.getString("username", "No name defined");//"No name defined" is the default value.
            String pass = prefs.getString("password", "No name defined");//"No name defined" is the default value.

            if (!username.getText().toString().equals(usr) && !pw.getText().toString().equals(pass)) {

                errorTxt.setVisibility(View.VISIBLE);

            } else if(username.getText().toString().equals(usr) && pw.getText().toString().equals(pass)) {

                Intent i = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(i);

            }

        });


    }



}