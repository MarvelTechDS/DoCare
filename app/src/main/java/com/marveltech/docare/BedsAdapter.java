package com.marveltech.docare;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BedsAdapter extends RecyclerView.Adapter<BedsAdapter.bedsViewHolder> {
    Context context;
    ArrayList<BedsData> bedsData;

    public BedsAdapter(Context context, ArrayList<BedsData> bedsData) {
        this.context = context;
        this.bedsData = bedsData;
    }

    @NonNull
    @Override
    public BedsAdapter.bedsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.bedsrecyclerlist,parent,false);
        return new bedsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BedsAdapter.bedsViewHolder holder, int position) {
            BedsData data = bedsData.get(position);
            holder.beds_availability.setText(data.getBedsavaibility());
            holder.phonenumber.setText(data.getPhonenumber());
            holder.address.setText(data.getAddress());
            holder.location.setText(data.getLocation());
            holder.hospitalname.setText(data.getHospitalname());
            String a  = data.getBedsavaibility();
            int avail = Integer.parseInt(a);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,BedSecondActivity.class);
                    intent.putExtra("hospitalname",data.getHospitalname());
                    intent.putExtra("location",data.getLocation());
//                    intent.putExtra("address",data.getAddress());
//                    intent.putExtra("phonenumber",data.getPhonenumber());
                    intent.putExtra("availability",avail);
                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return bedsData.size();
    }

    public static class bedsViewHolder extends RecyclerView.ViewHolder{
        TextView hospitalname,location,address,phonenumber,beds_availability;
        public bedsViewHolder(@NonNull View itemView) {
            super(itemView);
            hospitalname = itemView.findViewById(R.id.beds_hospitalname);
            location = itemView.findViewById(R.id.beds_location);
            address = itemView.findViewById(R.id.hospital_address);
            phonenumber = itemView.findViewById(R.id.bed_hospital_phone);
            beds_availability = itemView.findViewById(R.id.beds_available);
        }
    }
}
