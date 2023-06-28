package com.example.foodchecker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private EditText alcoholTextView;
    private EditText foodTextView;


    private FrameLayout frmLayout;
    private RelativeLayout rootLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //frmLayout = findViewById(R.id.frmLayout);
        rootLayout = findViewById(R.id.rootLayout);
        alcoholTextView = findViewById(R.id.editText1);
        foodTextView = findViewById(R.id.editText2);
        frmLayout = findViewById(R.id.frmLayout);
        // Get a reference to the "sensorData" path in the Firebase Realtime Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference("sensorData").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Retrieve the alcohol value from the dataSnapshot

                frmLayout.setBackgroundColor(getResources().getColor(R.color.yellow));
                String alcoholValue = dataSnapshot.child("value").getValue(String.class);
                // Retrieve the food status value from the dataSnapshot

                double value = Double.parseDouble(alcoholValue);
                String foodStatus;
                foodTextView.setTextColor(getResources().getColor(R.color.black));
                Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_700)));
                if(value > 115)
                {
                    foodStatus = "FOOD IS SPOILT";
                    Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));
                    foodTextView.setTextColor(getResources().getColor(R.color.blue));
                    rootLayout.setBackgroundColor(getResources().getColor(R.color.red));
                }
                else
                {
                    foodStatus = "FOOD IS FINE!!!";
                    Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_700)));
                    foodTextView.setTextColor(getResources().getColor(R.color.blue));
                    rootLayout.setBackgroundResource(R.drawable.border);
                }

                // Update the edittextViews with the retrieved values
                alcoholTextView.setText( alcoholValue);
                foodTextView.setText( foodStatus);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle any errors that occur during the data retrieval

            }
        });
    }
}