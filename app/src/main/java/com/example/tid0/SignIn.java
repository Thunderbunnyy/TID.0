package com.example.tid0;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SignIn extends AppCompatActivity {

    EditText username;
    EditText pw;
    Button btnSignin;
    ResultSet rsf;
    Statement stmtf;
    public static final String USER = "UserPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        username = findViewById(R.id.user_ed);
        pw = findViewById(R.id.pw);

        btnSignin = findViewById(R.id.btn_signin);

        btnSignin.setOnClickListener(v -> {
            SharedPreferences prefs = getSharedPreferences(USER, MODE_PRIVATE);
            String usr = prefs.getString("username", "No name defined");//"No name defined" is the default value.

            if (username.getText().toString().equals(usr)) {
                username.setError("l'utilisateur existe déjà");
                username.requestFocus();

            } else if (username.getText().toString().equals("") || pw.getText().toString().equals("")) {
                username.setError("vide");
                username.requestFocus();

                pw.setError("vide");
                pw.requestFocus();
            } else {
                SyncData_cmd orderData = new SyncData_cmd();
                orderData.execute("");

            }

        });

    }

    private class SyncData_cmd extends AsyncTask<String, String, String> {
        String msg = "Internet/DB_Credentials/Windows_FireWall_TurnOn Error, See Android Monitor in the bottom For details!";

        ProgressDialog progress;


        @Override
        protected void onPreExecute() //Starts the progress dailog
        {
            progress = ProgressDialog.show(SignIn.this, "",
                    " Chargement...", true);
        }


        @Override
        protected String doInBackground(String... strings)  // Connect to the database, write query and add items to array list
        {

            String user = username.getText().toString();
            String pass = pw.getText().toString();

            progress.show();
            Connection conn;

            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                conn = new ConnectionClass().CNX();
                if (conn == null) {
                    Log.e("query3", "******************");

                } else {

                    PreparedStatement ps;

                    ps = conn.prepareStatement("insert into SCSI_TRF_USER(username, password) values(?,?)");
                    ps.setString(1, user);
                    ps.setString(2, pass);
                    int x;
                    x = ps.executeUpdate();

                    Log.e("username", "" + user);
                    Log.e("pw", "" + pass);
                    Log.e("PreparedStmt", "Success");

                    String query3;
                    query3 = " select * from SCSI_TRF_USER ";

                    stmtf = conn.createStatement();
                    rsf = stmtf.executeQuery(query3);

                    while (rsf.next()) {

                        String stat5 = rsf.getString("username");
                        Log.e("stat5", "" + stat5);

                        SharedPreferences.Editor editor = getSharedPreferences(USER, MODE_PRIVATE).edit();
                        editor.putString("username", user);
                        editor.putString("password", pass);
                        editor.putInt("uid", rsf.getInt("UID"));
                        editor.apply();
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
                Writer writer = new StringWriter();
                e.printStackTrace(new PrintWriter(writer));
                msg = writer.toString();

            }
            return msg;
        }

        @Override
        protected void onPostExecute(String msg) {
            progress.dismiss();

            Toast.makeText(SignIn.this, "Success", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(SignIn.this, MainActivity.class);
            startActivity(i);


        }
    }
}