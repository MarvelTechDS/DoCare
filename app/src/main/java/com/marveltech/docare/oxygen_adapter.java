package com.marveltech.docare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class oxygen_adapter extends RecyclerView.Adapter<oxygen_adapter.oxygenViewHolder>
{
    Context context;
    ArrayList<Oxygen_data> oxygenData;

    public oxygen_adapter(Context context, ArrayList<Oxygen_data> oxygenData) {
        this.context = context;
        this.oxygenData = oxygenData;
    }

    @NonNull
    @Override
    public oxygenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(context).inflate(R.layout.oxygenrecyclerist,parent,false);
       return new oxygenViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull oxygenViewHolder holder, int position) {
        Oxygen_data data = oxygenData.get(position);
        holder.oxygensupplyName.setText(data.getOxygensupplyname());
        holder.location_oxygen.setText(data.getLocation());
        holder.oxygen_availability.setText(data.getAvaibility());
        holder.PhoneNnumber_oxygen.setText(data.getPhonenumber());
    }

    @Override
    public int getItemCount() {
        return oxygenData.size();
    }

    public static class oxygenViewHolder extends RecyclerView.ViewHolder{
        TextView oxygensupplyName,location_oxygen,PhoneNnumber_oxygen,oxygen_availability,oxygen_address;
        public oxygenViewHolder(@NonNull View itemView) {
            super(itemView);
            oxygensupplyName = itemView.findViewById(R.id.oxygen_supplyname);
            location_oxygen = itemView.findViewById(R.id.oxygen_location);
            PhoneNnumber_oxygen = itemView.findViewById(R.id.oxygen_phone);
            oxygen_availability = itemView.findViewById(R.id.oxygen_available);
            oxygen_address = itemView.findViewById(R.id.oxygen_address);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
