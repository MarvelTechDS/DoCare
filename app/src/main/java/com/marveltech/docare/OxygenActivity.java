package com.marveltech.docare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OxygenActivity extends AppCompatActivity {
    TextView total_supply;
    RecyclerView recyclerView;
    DatabaseReference reference;
    oxygen_adapter oxygenAdapter;
    ArrayList<Oxygen_data> oxygenDatalist;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxygen);
        recyclerView = findViewById(R.id.oxygen_recycler);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Docare ");
        dialog.setMessage("Fetching Data. Please wait..........");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();
//        total_supply = findViewById(R.id.oxygen_supply_total);
        reference = FirebaseDatabase.getInstance().getReference("OxygenSuppliers");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        oxygenDatalist = new ArrayList<>();
        oxygenAdapter = new oxygen_adapter(this,oxygenDatalist);
        recyclerView.setAdapter(oxygenAdapter);
//        total_supply.setText(oxygenAdapter.getItemCount());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Oxygen_data oxygenData = dataSnapshot.getValue(Oxygen_data.class);
                    oxygenDatalist.add(oxygenData);
                    dialog.dismiss();
                }
                oxygenAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}