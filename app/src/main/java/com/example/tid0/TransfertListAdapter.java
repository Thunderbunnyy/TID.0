package com.example.tid0;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TransfertListAdapter extends RecyclerView.Adapter<TransfertListAdapter.TransfertHolder> {

    private List<Transfert> transfertList ;

    public TransfertListAdapter(List<Transfert> transfertList) {
        this.transfertList = transfertList;
    }

    @NonNull
    @Override
    public TransfertHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);

        return new TransfertHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransfertListAdapter.TransfertHolder holder, int position) {

        holder.designation.setText(transfertList.get(position).getDES());
        holder.of.setText(transfertList.get(position).getUNUMOF());
        holder.quantity.setText(String.valueOf(transfertList.get(position).getCDQTE()));
    }

    @Override
    public int getItemCount() {
        return transfertList.size();
    }

    public class TransfertHolder extends RecyclerView.ViewHolder {
        TextView designation;
        TextView of;
        TextView quantity;

        public TransfertHolder(@NonNull View itemView) {
            super(itemView);
            designation = itemView.findViewById(R.id.des);
            of = itemView.findViewById(R.id.of);
            quantity = itemView.findViewById(R.id.quantity);
        }
    }
}
