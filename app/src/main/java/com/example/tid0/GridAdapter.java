package com.example.tid0;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class GridAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Transfert> transfertList ;

    public GridAdapter(Context c, List<Transfert> transfertList){
        context = c;
        this.transfertList = transfertList;
    }

    @Override
    public int getCount() {
        return transfertList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(layoutInflater==null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }
        if(convertView==null){
            convertView = layoutInflater.inflate(R.layout.row_item,null);
            TextView designation = convertView.findViewById(R.id.des);
            TextView of = convertView.findViewById(R.id.of);
            TextView quantity = convertView.findViewById(R.id.quantity);

            Transfert transfert=transfertList.get(position);

            designation.setText(transfert.getDES()+"");
            of.setText(transfert.getN_OF()+"");
            quantity.setText(transfert.getCDDT()+"");
        }
        return convertView;
    }
}
