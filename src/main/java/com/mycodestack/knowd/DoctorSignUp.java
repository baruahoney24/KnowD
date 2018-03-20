package com.mycodestack.knowd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorSignUp extends Activity {


    private TextView knowd ;
    private EditText HospitalName , DoctorName , ContactNumber , City ;
    private DatabaseReference mDatabase;
    private String Hospital , Doctor , CityString , DoctorId;
    private String Contact ;
    private SharedPreferences myPreferences ;
    private SharedPreferences.Editor editor ;
    public Typeface myfonts ;
    private Button button ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_sign_up);

        // Adding Database Reference
        mDatabase = FirebaseDatabase.getInstance().getReference();
        myfonts = Typeface.createFromAsset(getAssets(),"fonts/Acme-Regular.ttf");

        knowd = (TextView)findViewById(R.id.knowd);
        HospitalName = (EditText)findViewById(R.id.HospitalName);
        DoctorName = (EditText)findViewById(R.id.DoctorName);
        ContactNumber= (EditText)findViewById(R.id.ContactNumber);
        knowd.setTypeface(myfonts);
        HospitalName.setTypeface(myfonts);
        DoctorName.setTypeface(myfonts);
        ContactNumber.setTypeface(myfonts);
      //  ContactNumber.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        City = (EditText)findViewById(R.id.City);
        City.setTypeface(myfonts);

        button = (Button)findViewById(R.id.DoctorSubmitButton);
        button.setTypeface(myfonts);

        // SHARED PREFERENCES
        myPreferences = getSharedPreferences("AppInfo", Context.MODE_PRIVATE);
        editor = myPreferences.edit();
    }

    public void AddDoctorToDatabase(View view) {


        Hospital = HospitalName.getText().toString();
        Doctor = DoctorName.getText().toString();
        CityString = City.getText().toString();
        Contact = (ContactNumber.getText().toString());
        DoctorData doctorData = new DoctorData();
        doctorData.setHospitalName(Hospital);
        doctorData.setDoctorName(Doctor);
        doctorData.setCity(CityString);
        doctorData.setContact(Contact);
        doctorData.setLocation("a....locationString");
        doctorData.setCurrent_number("0");
        DoctorId = Doctor.substring(0,3)+Hospital.substring(1,4) + CityString.substring(1,4);
        doctorData = new DoctorData(Hospital,Doctor,CityString,Contact,doctorData.getLocation(),doctorData.getCurrent_number());
        mDatabase.child("Doctors").child(DoctorId).setValue(doctorData);

        editor.putString("hospitalName",Hospital);
        editor.putString("doctorName",Doctor);
        editor.putString("city",CityString);
        editor.putString("contact",Contact);
        editor.putString("doctorID",DoctorId);
        editor.putBoolean("DoctorSignUp",false);
        editor.apply();

        Intent intent = new Intent(DoctorSignUp.this,UpdateStatus.class);
        startActivity(intent);

    }
}
