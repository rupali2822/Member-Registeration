package com.template.memberapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberViewHolder> {
    Context context;
    ArrayList<MemberModel> list;

    public MemberAdapter(Context context, ArrayList<MemberModel> arrayList) {
        this.context = context;
        this.list = arrayList;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.member_layout,parent,false);
        // MovieViewHolder holder=new MovieViewHolder(view,fragmentCommunication);
        return new MemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {
        Log.e("","list 1:"+list.size());
        holder.tvmName.setText(list.get(position).getmName());
        holder.tvmRole.setText(list.get(position).getRole());
        holder.tvmobile.setText(list.get(position).getmContact());
        holder.tvSAmt.setText(list.get(position).getSubFee());
        holder.cDepAmt.setText(list.get(position).getDepAmt());
        holder.cLAmt.setText(list.get(position).getLoanAmt());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MemberViewHolder extends RecyclerView.ViewHolder{


        TextView tvmName,tvmRole,tvSAmt,tvmobile,cDepAmt,cLAmt;
        MaterialCardView cardView;
        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);

            tvmobile=itemView.findViewById(R.id.tv_mContact);
            tvmName=itemView.findViewById(R.id.tv_mName);
            tvmRole=itemView.findViewById(R.id.tv_mRole);
            tvSAmt=itemView.findViewById(R.id.tv_sAmt);
            cDepAmt=itemView.findViewById(R.id.c_depAmt);
            cLAmt=itemView.findViewById(R.id.c_lAmt);
        }
    }
}
