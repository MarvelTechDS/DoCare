
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

public class BedsActivity extends AppCompatActivity {
    TextView total_beds;
    RecyclerView recyclerView;
    DatabaseReference reference;
    BedsAdapter bedsAdapter;
    ArrayList<BedsData> bedsDataArrayList;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beds);
        dialog = new ProgressDialog(this);
        dialog.setTitle("DoCare Loading");
        dialog.setMessage("Fetching Data. Please Wait............");
        dialog.setCancelable(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();
        recyclerView = findViewById(R.id.beds_recycler);
//        total_beds = findViewById(R.id.total_beds);
        reference = FirebaseDatabase.getInstance().getReference("Hospitals");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bedsDataArrayList = new ArrayList<>();
        bedsAdapter = new BedsAdapter(this,bedsDataArrayList);
        recyclerView.setAdapter(bedsAdapter);
//        total_beds.setText(bedsAdapter.getItemCount());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    BedsData bedsData = dataSnapshot.getValue(BedsData.class);
                    bedsDataArrayList.add(bedsData);
                    dialog.dismiss();
                }
                bedsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}