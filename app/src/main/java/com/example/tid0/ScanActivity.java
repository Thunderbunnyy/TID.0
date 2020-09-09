package com.example.tid0;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ScanActivity extends AppCompatActivity {

    Button btn_scan;
    Button btn_valider;
    EditText info;
    TextView of;
    TextView codeArticle;
    TextView desArticle;
    EditText quantity;
    TextView date;
    TransfertDatabase db;
    List<Transfert> transfertList = new ArrayList<>();
    Transfert transfert;
    String numOf;
    String palette;
    ResultSet rsf;
    Statement stmtf;
    ListView lv;
    ListAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        btn_scan = findViewById(R.id.btn_scan);
        info = findViewById(R.id.numPalette);

        of = findViewById(R.id.of);
        codeArticle = findViewById(R.id.code_article);
        desArticle = findViewById(R.id.des_article);
        quantity = findViewById(R.id.quantité);

        date = findViewById(R.id.date);


        LinearLayout infoLayout = findViewById(R.id.info_layout);
        infoLayout.setVisibility(View.INVISIBLE);

        btn_valider = findViewById(R.id.btn_valider);
        Button btn_cancel = findViewById(R.id.btn_cancel);

        Button btn_entrer = findViewById(R.id.btn_edittext);

        Button btn_cloturer = findViewById(R.id.btn_cloturer);
        btn_cloturer.setVisibility(View.INVISIBLE);

        btn_cloturer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(transfert.getSTATUS()==0){
                    transfert.setSTATUSENT(0);
                    // TODO: 08/09/2020 go to home screen
                }else {
                    transfert.setSTATUSENT(2);
                    // TODO: 08/09/2020 go to home screen
                }
            }
        });

        /*
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TransfertListAdapter(transfertList);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);*/

        lv = findViewById(R.id.listview);

        btn_scan.setOnClickListener(v -> scanCode());

        btn_entrer.setOnClickListener(v -> {

            if (info.getText().toString().equals("")) {
                info.setError("vide");
                info.requestFocus();

            } else {

                String currentString = info.getText().toString().trim();
                String[] separated = currentString.split("P");
                numOf = separated[0];
                palette = separated[1].trim();

                SyncData_cmd orderData = new SyncData_cmd();
                orderData.execute("");
            }

        });

        btn_valider.setOnClickListener((View v) -> {
            String q = quantity.getText().toString();

            if (transfert != null) {

                try {
                    if(q.equals(String.valueOf(rsf.getDouble("CDQTE")))){

                        transfert.setSTATUS(1);
                        db.transfertDAO().insert(transfert);
                        transfert = new Transfert();

                        LinearLayout infoLayout1 = findViewById(R.id.info_layout);
                        infoLayout1.setVisibility(View.INVISIBLE);

                        transfertList.add(transfert);

                        //recyclerView.setVisibility(View.VISIBLE);

                    }else{

                        transfert.setSTATUS(0);
                        db.transfertDAO().insert(transfert);
                        transfert = new Transfert();

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

                        alertDialogBuilder.setMessage("Veuillez vérifier la quantité");
                        alertDialogBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else {
                Toast.makeText(ScanActivity.this, "nope", Toast.LENGTH_SHORT).show();
            }

        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout infoLayout1 = findViewById(R.id.info_layout);
                infoLayout1.setVisibility(View.INVISIBLE);
                //recyclerView.setVisibility(View.VISIBLE);
            }
        });

        Dexter.withContext(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Toast.makeText(ScanActivity.this, "Il faut accepter la permission pour continuer", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                    }
                });

        db = TransfertDatabase.getInstance(ScanActivity.this);

        transfertList=db.transfertDAO().SelectListeTransfer();
       // Log.e("transfertList",transfertList.size()+"");

        /*GridAdapter adapter = new GridAdapter(this,transfertList);
        listview_scanned.setAdapter(adapter);*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();

                //AlertDialog.Builder builder = new AlertDialog.Builder(this);

                String currentString = result.getContents();
                String[] separated = currentString.split("P");
                numOf = separated[0];
                palette = separated[1].trim();

                /*AlertDialog dialog = builder.create();
                dialog.show();*/

                info.setText(result.getContents());

                if (info.getText().toString().equals("")) {
                    info.setError("vide");
                    info.requestFocus();

                } else {

                    SyncData_cmd orderData = new SyncData_cmd();
                    orderData.execute("");

                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    private void scanCode() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setBarcodeImageEnabled(true);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.initiateScan();

    }

    private class SyncData_cmd extends AsyncTask<String, String, String> {
        String msg = "Internet/DB_Credentials/Windows_FireWall_TurnOn Error, See Android Monitor in the bottom For details!";

        ProgressDialog progress;


        @Override
        protected void onPreExecute() //Starts the progress dailog
        {
            progress = ProgressDialog.show(ScanActivity.this, "",
                    " Chargement...", true);
        }


        @Override
        protected String doInBackground(String... strings)  // Connect to the database, write query and add items to array list
        {

            progress.show();
            Connection conn;

            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                conn = new ConnectionClass().CNX();
                if (conn == null) {
                    Log.e("query3", "******************");

                } else {

                    String query3;
                    query3 = " select * from ZVW_TRANSFERT where UNUMOF=" + numOf + " and UNUMP=" + palette;
                    Log.e("query3", "" + query3);

                    stmtf = conn.createStatement();
                    rsf = stmtf.executeQuery(query3);

                            while (rsf.next()) {

                            String stat5 = rsf.getString("DES");
                            Log.e("stat5", rsf.getInt("CDNO") + "");
                            transfert = new Transfert();
                            transfert.setCDNO(rsf.getInt("CDNO"));
                            transfert.setDES(rsf.getString("DES"));
                            transfert.setCDDT(rsf.getString("CDDT"));
                            transfert.setREF(rsf.getString("REF"));
                            transfert.setDEPO(rsf.getString("DEPO"));
                            transfert.setSREF1(rsf.getString("SREF1"));
                            //transfert.setCDQTE(rsf.getDouble("CDQTE"));
                            transfert.setREFUN(rsf.getString("REFUN"));
                            transfert.setSENS(rsf.getInt("SENS"));
                            transfert.setN_OF(rsf.getString("N°OF"));
                            transfert.setUNUMOF(rsf.getInt("UNUMOF"));
                            transfert.setUNUMP(rsf.getInt("UNUMP"));

                            of.setText(rsf.getString("UNUMOF"));
                            codeArticle.setText(rsf.getString("REF"));
                            quantity.setText(String.valueOf(rsf.getDouble("CDQTE")));

                            transfert.setCDQTE(Double.parseDouble(quantity.getText().toString().trim()));

                            date.setText(rsf.getString("CDDT"));
                            desArticle.setText(rsf.getString("DES"));

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
        protected void onPostExecute(String msg) // dismissing progress dialoge, showing error and setting up my ListView
        {
            progress.dismiss();
            LinearLayout infoLayout = findViewById(R.id.info_layout);
            infoLayout.setVisibility(View.VISIBLE);

            customAdapter = new ListAdapter(ScanActivity.this, R.layout.row_item, transfertList);
            lv.setAdapter(customAdapter);
            customAdapter.notifyDataSetChanged();
            lv.setVisibility(View.VISIBLE);

            /*RecyclerView recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setVisibility(View.INVISIBLE);*/


            if (transfert == null) {
                Toast.makeText(ScanActivity.this, "Nothing there", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(ScanActivity.this, "Showing ", Toast.LENGTH_SHORT).show();
            }
        }
    }
}