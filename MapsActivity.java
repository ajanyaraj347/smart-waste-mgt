package com.example.wastemanagement;


import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;

    private Marker LH;
    private Marker Cafteria;
    private Marker MH;
    private Marker CS;
    private Marker MCA;
    private Marker Civilcafe;
    private Marker Archi;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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

        mMap.setOnMarkerClickListener(this);

        LH=googleMap.addMarker(new MarkerOptions().position(new LatLng(8.547324,76.9060656)).title("Marker in LH"));
        Cafteria=googleMap.addMarker(new MarkerOptions().position(new LatLng(8.545276, 76.907179)).title("Marker in Cafteria"));
        MH=googleMap.addMarker(new MarkerOptions().position(new LatLng(8.5432461,76.9028629)).title("Marker in MH"));
        MCA=googleMap.addMarker(new MarkerOptions().position(new LatLng(8.545659,76.9047099)).title("Marker in MCA"));
        CS=googleMap.addMarker(new MarkerOptions().position(new LatLng(8.545872,76.9030507)).title("Marker in CS"));
        Civilcafe=googleMap.addMarker(new MarkerOptions().position(new LatLng(8.5455908,76.9038553)).title("Marker in Civilcafe"));
        Archi=googleMap.addMarker(new MarkerOptions().position(new LatLng(8.5454078,76.90455)).title("Marker in Archi"));



        LatLng cet = new LatLng(8.5458513,76.9063407);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cet, 18.0f));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (marker.equals(LH))
        {
            Uri gmmIntentUri = Uri.parse("google.navigation:q="+"8.547324,76.9060656");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }
        if (marker.equals(Cafteria))
        {
            Uri gmmIntentUri = Uri.parse("google.navigation:q="+"8.545276, 76.907179");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }
        if (marker.equals(MH))
        {
            Uri gmmIntentUri = Uri.parse("google.navigation:q="+"8.5432461,76.9028629");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }
        if (marker.equals(MCA))
        {
            Uri gmmIntentUri = Uri.parse("google.navigation:q="+"8.545659,76.9047099");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }
        if (marker.equals(Archi))
        {
            Uri gmmIntentUri = Uri.parse("google.navigation:q="+"8.5454078,76.90455");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }
        if (marker.equals(Civilcafe))
        {
            Uri gmmIntentUri = Uri.parse("google.navigation:q="+"8.5455908,76.9038553");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }
        if (marker.equals(CS))
        {
            Uri gmmIntentUri = Uri.parse("google.navigation:q="+"8.545872,76.9030507");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }
        return false;
    }
}