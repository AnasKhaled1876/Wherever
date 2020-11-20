package com.example.wherever;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity extends FragmentActivity implements View.OnClickListener, OnMapReadyCallback {


    private GoogleMap mMap;
    private Button anas_loc, basma_loc, lina_loc;
    private LatLng location;
    private DatabaseReference reference;
    private double anas_longitude, anas_latitude, basma_longitude, basma_latitude, lina_longitude, lina_latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        anas_loc = findViewById(R.id.anas_location);
        basma_loc = findViewById(R.id.basma_location);
        lina_loc = findViewById(R.id.lina_location);
        anas_loc.setOnClickListener(this);
        basma_loc.setOnClickListener(this);
        lina_loc.setOnClickListener(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //mMap.addMarker(new MarkerOptions().position(location).title("Marker in Tech store"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.anas_location:
                getAnasLocation();
                mMap.clear();
                location = new LatLng(anas_latitude, anas_longitude);
                mMap.addMarker(new MarkerOptions().position(location).title("Marker in Tech store"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
                break;

            case R.id.basma_location:
                getBasmaLocation();
                mMap.clear();
                location = new LatLng(basma_latitude, basma_longitude);
                mMap.addMarker(new MarkerOptions().position(location).title("Marker in Tech store"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
                break;

            case R.id.lina_location:
                getLinaLocation();
                mMap.clear();
                location = new LatLng(lina_latitude, lina_longitude);
                mMap.addMarker(new MarkerOptions().position(location).title("Marker in Tech store"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
                break;
        }
    }

    private void getAnasLocation() {
        reference = FirebaseDatabase.getInstance().getReference("anas_location");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() > 0) {
                    anas_longitude = dataSnapshot.child("anas_long").getValue(Double.class);
                    anas_latitude = dataSnapshot.child("anas_lan").getValue(Double.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getBasmaLocation() {
        reference = FirebaseDatabase.getInstance().getReference("basma_location");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() > 0) {
                    basma_longitude = dataSnapshot.child("basma_long").getValue(Double.class);
                    basma_latitude = dataSnapshot.child("basma_lan").getValue(Double.class);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getLinaLocation() {
        reference = FirebaseDatabase.getInstance().getReference("lina_location");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() > 0) {
                    lina_longitude = dataSnapshot.child("lina_long").getValue(Double.class);
                    lina_latitude = dataSnapshot.child("lina_lan").getValue(Double.class);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

}



