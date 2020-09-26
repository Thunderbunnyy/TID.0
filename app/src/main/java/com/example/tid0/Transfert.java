package com.example.tid0;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "transfert")
public class Transfert {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private int _id;

    @ColumnInfo(name = "user")
    private String user;

    @ColumnInfo(name = "destination")
    private String destination;

    @ColumnInfo(name = "STATUS")
    private int STATUS;

    @ColumnInfo(name = "STATUSENT")
    private int STATUSENT;

    @ColumnInfo(name = "CDDT")
    private String CDDT;

    @ColumnInfo(name = "UNUMOF")
    private int UNUMOF;

    @ColumnInfo(name = "UNUMP")
    private int UNUMP;

    @ColumnInfo(name = "CDNO")
    private int CDNO;

    @ColumnInfo(name = "REF")
    private String REF;

    @ColumnInfo(name = "DES")
    private String DES;

    @ColumnInfo(name = "SREF1")
    private String SREF1;

    @ColumnInfo(name = "DEPO")
    private String DEPO;

    @ColumnInfo(name = "CDQTE")
    private double CDQTE;

    @ColumnInfo(name = "REFUN")
    private String REFUN;

    @ColumnInfo(name = "SENS")
    private int SENS;

    @ColumnInfo(name = "N_OF")
    private String N_OF;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(int STATUS) {
        this.STATUS = STATUS;
    }

    public int getSTATUSENT() {
        return STATUSENT;
    }

    public void setSTATUSENT(int STATUSENT) {
        this.STATUSENT = STATUSENT;
    }

    public String getCDDT() {
        return CDDT;
    }

    public void setCDDT(String CDDT) {
        this.CDDT = CDDT;
    }

    public int getUNUMOF() {
        return UNUMOF;
    }

    public void setUNUMOF(int UNUMOF) {
        this.UNUMOF = UNUMOF;
    }

    public int getUNUMP() {
        return UNUMP;
    }

    public void setUNUMP(int UNUMP) {
        this.UNUMP = UNUMP;
    }

    public int getCDNO() {
        return CDNO;
    }

    public void setCDNO(int CDNO) {
        this.CDNO = CDNO;
    }

    public String getREF() {
        return REF;
    }

    public void setREF(String REF) {
        this.REF = REF;
    }

    public String getDES() {
        return DES;
    }

    public void setDES(String DES) {
        this.DES = DES;
    }

    public String getSREF1() {
        return SREF1;
    }

    public void setSREF1(String SREF1) {
        this.SREF1 = SREF1;
    }

    public String getDEPO() {
        return DEPO;
    }

    public void setDEPO(String DEPO) {
        this.DEPO = DEPO;
    }

    public double getCDQTE() {
        return CDQTE;
    }

    public void setCDQTE(double CDQTE) {
        this.CDQTE = CDQTE;
    }

    public String getREFUN() {
        return REFUN;
    }

    public void setREFUN(String REFUN) {
        this.REFUN = REFUN;
    }

    public int getSENS() {
        return SENS;
    }

    public void setSENS(int SENS) {
        this.SENS = SENS;
    }

    public String getN_OF() {
        return N_OF;
    }

    public void setN_OF(String n_OF) {
        N_OF = n_OF;
    }
}
