package com.example.wherever;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;

    private Button signIn;
    private RadioButton adminBtn, anasBtn, basmaBtn, linaBtn;
    private RadioGroup group;
    private EditText pass;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        adminBtn = findViewById(R.id.admin_button);
        anasBtn = findViewById(R.id.anas_button);
        basmaBtn = findViewById(R.id.basma_button);
        linaBtn = findViewById(R.id.lina_button);
        group = findViewById(R.id.radio_group);
        signIn = findViewById(R.id.sign_button);
        pass = findViewById(R.id.pass_box);


   /*     Map<String, Object> data = new HashMap<>();
        data.put("Password", "admin");
        FirebaseDatabase.getInstance().getReference().child("passwords").child("admin").setValue("admin");
        data.clear();

        data.put("Password", "anas2000");
        FirebaseDatabase.getInstance().getReference().child("passwords").child("anas").setValue("anas2000");
        data.clear();

        data.put("Password", "basma288");
        FirebaseDatabase.getInstance().getReference().child("passwords").child("basma").setValue("basma288");
        data.clear();

        data.put("Password", "lina3084");
        FirebaseDatabase.getInstance().getReference().child("passwords").child("lina").setValue("lina3084");
        data.clear();

     */

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adminBtn.isChecked()) {
                    reference = FirebaseDatabase.getInstance().getReference().child("passwords");
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                if (snapshot.getValue().toString().equalsIgnoreCase(pass.getText().toString())) {
                                    Intent loginIntent = new Intent(MainActivity.this,MapsActivity.class);
                                    startActivity(loginIntent);
                                    break;
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                } else if (anasBtn.isChecked()) {
                    reference = FirebaseDatabase.getInstance().getReference().child("passwords");
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                if (snapshot.getValue().toString().equalsIgnoreCase(pass.getText().toString())) {
                                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION_PERMISSION);
                                    } else {
                                        getCurrentLocation();
                                    }
                                    break;
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                } else if (basmaBtn.isChecked()) {
                    reference = FirebaseDatabase.getInstance().getReference().child("passwords");
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                if (snapshot.getValue().toString().equalsIgnoreCase(pass.getText().toString())) {
                                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION_PERMISSION);
                                    } else {
                                        getCurrentLocation();
                                    }


                                    break;
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                } else if (linaBtn.isChecked()) {
                    reference = FirebaseDatabase.getInstance().getReference().child("passwords");
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                if (snapshot.getValue().toString().equalsIgnoreCase(pass.getText().toString())) {
                                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION_PERMISSION);
                                    } else {
                                        getCurrentLocation();
                                    }
                                    break;
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

            }
        });


    }






    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            }
            else {
                Toast.makeText(this,"Permission Denied",Toast.LENGTH_LONG);
            }
        }

    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {

        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(locationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        LocationServices.getFusedLocationProviderClient(MainActivity.this)
                .requestLocationUpdates(locationRequest, new LocationCallback(){

                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(MainActivity.this).removeLocationUpdates(this);
                        if(locationResult != null && locationResult.getLocations().size() > 0){
                            int latestLocationIndex = locationResult.getLocations().size() - 1;
                            double latitude = locationResult.getLocations().get(latestLocationIndex).getLatitude();
                            double longitude = locationResult.getLocations().get(latestLocationIndex).getLongitude();
                            if(anasBtn.isChecked()) {
                                FirebaseDatabase.getInstance().getReference().child("anas_location").child("anas_long").setValue(longitude);
                                FirebaseDatabase.getInstance().getReference().child("anas_location").child("anas_lan").setValue(latitude);
                            }
                            if(basmaBtn.isChecked()) {
                                FirebaseDatabase.getInstance().getReference().child("basma_location").child("basma_long").setValue(longitude);
                                FirebaseDatabase.getInstance().getReference().child("basma_location").child("basma_lan").setValue(latitude);
                            }
                            if(linaBtn.isChecked()) {
                                FirebaseDatabase.getInstance().getReference().child("lina_location").child("lina_long").setValue(longitude);
                                FirebaseDatabase.getInstance().getReference().child("lina_location").child("lina_lan").setValue(latitude);
                            }
                        }
                    }
                }, Looper.getMainLooper());


    }
}