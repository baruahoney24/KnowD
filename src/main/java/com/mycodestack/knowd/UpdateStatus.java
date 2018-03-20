package com.mycodestack.knowd;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateStatus extends AppCompatActivity {

    private TextView DoctorNAME , HospitalNAME ;
    private EditText EnterCurrentNumberText ;
    private Button UpdateNumberButton , DoctorNotAvaialableButton ;
    private SharedPreferences myPreferences ;
    private SharedPreferences.Editor editor ;
    private DatabaseReference mDatabase;
    public Typeface myfonts ;
    String doctorID , text ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_status);


        mDatabase = FirebaseDatabase.getInstance().getReference();

        myPreferences = getSharedPreferences("AppInfo", Context.MODE_PRIVATE);
        myfonts = Typeface.createFromAsset(getAssets(),"fonts/Acme-Regular.ttf");
        editor = myPreferences.edit();
        DoctorNAME = (TextView)findViewById(R.id.DoctorNAME);
        DoctorNAME.setTypeface(myfonts);
        HospitalNAME = (TextView)findViewById(R.id.HospitalNAME);
        HospitalNAME.setTypeface(myfonts);
        EnterCurrentNumberText = (EditText)findViewById(R.id.EnterCurrentNumberText);
        EnterCurrentNumberText.setTypeface(myfonts);
        UpdateNumberButton = (Button)findViewById(R.id.UpdateNumberButton);
        UpdateNumberButton.setTypeface(myfonts);
        DoctorNotAvaialableButton = (Button)findViewById(R.id.DoctorNotAvailableButton);
        DoctorNotAvaialableButton.setTypeface(myfonts);
        DoctorNAME.setText(myPreferences.getString("doctorName","Not Available"));
        HospitalNAME.setText(myPreferences.getString("hospitalName","Not Available"));
        doctorID = myPreferences.getString("doctorID","Not Available");

        UpdateNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = EnterCurrentNumberText.getText().toString();
                mDatabase.child("Doctors/"+doctorID).child("current_number").setValue(text);
            }
        });

        DoctorNotAvaialableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long val = 0 ;
                mDatabase.child("Doctors/"+doctorID).child("current_number").setValue(val);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent DoctorSignUp = new Intent(UpdateStatus.this,WelcomeActivity.class);
        startActivity(DoctorSignUp);
    }
}
