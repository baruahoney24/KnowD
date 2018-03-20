package com.mycodestack.knowd;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DoctorStatus extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AlbumsAdapter adapter;
    private List<DoctorData> albumList;
    private FirebaseDatabase database ;
    private DatabaseReference databaseReference ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_status);

        recyclerView = (RecyclerView) findViewById(R.id.cardList);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        albumList = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Doctors");
        BindDataToList();

        Collections.sort(albumList,DoctorData.waiting);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        adapter = new AlbumsAdapter(this, albumList);
        recyclerView.setAdapter(adapter);


    }


    public void BindDataToList() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    DoctorData doctorData1 = new DoctorData(ds.child("hospitalName").getValue(String.class),ds.child("doctorName").getValue(String.class)
                            ,ds.child("city").getValue(String.class),ds.child("contact").getValue(String.class),
                            ds.child("location").getValue(String.class), ds.child("current_number").getValue(String.class));
                    albumList.add(doctorData1);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent DoctorSignUp = new Intent(DoctorStatus.this,WelcomeActivity.class);
        startActivity(DoctorSignUp);
    }



}