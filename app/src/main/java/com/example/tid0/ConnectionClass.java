package com.example.tid0;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {

    String ip = "192.168.1.230"; //"192.168.43.17"; "192.168.1.7";
    String classs = "net.sourceforge.jtds.jdbc.Driver";
    String db = "ERPOP";
    String un = "sa";
    String password = "saDbFinal";

    @SuppressLint("NewApi")
    public Connection CNX() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection cnx = null;
        String cnxURL;
        try {
            Class.forName(classs);
            //jdbc:jtds:sqlserver://<yourDBServerIPAddress>\SQLEXPRESS:1433;databaseName=AdventureWorks;user=sa;password=*****;
            cnxURL = "jdbc:jtds:sqlserver://" + ip + ";"+"databaseName=" + db + ";user=" + un + ";password="+ password + ";";

            Log.e("cnxURL",cnxURL+"");
            cnx = DriverManager.getConnection(cnxURL);
        }
        catch (SQLException se)
        {
            Log.e("Error 1: ", se.getMessage());
        }
        catch (ClassNotFoundException e) {
        }
        catch (Exception e) {
            Log.e("Error 2:", e.getMessage());
        }
        return cnx;
    }


}
