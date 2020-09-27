package com.example.tid0;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;

public class ListAdapter extends ArrayAdapter<Transfert> {

    private int resourceLayout;
    private Context mContext;

    TextView designation;
    TextView of;
    TextView quantity;
    TextView statusTV;

    public ListAdapter(@NonNull Context context, int resource, List<Transfert> transferts) {
        super(context, resource, transferts);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        Transfert p = getItem(position);

        if (p != null) {
            designation = v.findViewById(R.id.des);
            of = v.findViewById(R.id.of);
            quantity = v.findViewById(R.id.quantity);
            statusTV = v.findViewById(R.id.status);

            if (designation != null)
                designation.setText(p.getDES());

            if (of != null)
                of.setText(String.valueOf(p.getUNUMOF()));

            if (quantity != null)
                quantity.setText(String.valueOf(p.getCDQTE()));

            if(statusTV!=null){
                if(p.getSTATUSENT()==1){
                    statusTV.setText("Transféré");
                    int color = Color.parseColor("#008000");
                    statusTV.setBackgroundColor(color);
                }else if(p.getSTATUSENT()==2){
                    statusTV.setText("Anomalie");
                    int color = Color.parseColor("#FD6A02");
                    statusTV.setBackgroundColor(color);
                }
            }
        }
//        else{
//            Toast.makeText(mContext, "famech", Toast.LENGTH_SHORT).show();
//        }

        return v;
    }



}
