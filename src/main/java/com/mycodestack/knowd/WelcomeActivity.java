package com.mycodestack.knowd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WelcomeActivity extends Activity {

    private Button DoctorSignUpButton ,FindDoctorButton ;
    private TextView LogoKnowD ;
    private  SharedPreferences myPreferences ;
    private SharedPreferences.Editor editor ;
    private boolean IsDoctorLoggedIn ;
    public Typeface myfonts ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        myfonts = Typeface.createFromAsset(getAssets(),"fonts/Acme-Regular.ttf");
        DoctorSignUpButton = (Button)findViewById(R.id.DoctorSignUpButton);
        FindDoctorButton = (Button)findViewById(R.id.FindDoctorButton);
        DoctorSignUpButton.setTypeface(myfonts);
        FindDoctorButton.setTypeface(myfonts);





        LogoKnowD = (TextView)findViewById(R.id.LogoKnowD);
        LogoKnowD.setTypeface(myfonts);
        // SHARED PREFERENCES
        myPreferences = getSharedPreferences("AppInfo", Context.MODE_PRIVATE);
        editor = myPreferences.edit();

        IsDoctorLoggedIn = myPreferences.getBoolean("DoctorSignUp",true);

        DoctorSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IsDoctorLoggedIn) {
                Intent DoctorSignUp = new Intent(WelcomeActivity.this, com.mycodestack.knowd.DoctorSignUp.class);
                startActivity(DoctorSignUp);

            }
            else{
                Intent UpdateNumber = new Intent(WelcomeActivity.this,UpdateStatus.class);
                startActivity(UpdateNumber);
            }
            }
        });

        FindDoctorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent AvailableDoctor = new Intent(WelcomeActivity.this, DoctorStatus.class);
                    startActivity(AvailableDoctor);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        IsDoctorLoggedIn = myPreferences.getBoolean("DoctorSignUp",true);
    }
}
