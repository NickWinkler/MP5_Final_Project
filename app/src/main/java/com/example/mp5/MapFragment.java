package com.example.mp5;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lib.LocationData;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap mGoogleMap;
    MapView mMapView;
    View mView;
    private float currentLocation = 210.0f;
    private float notFull = 120.0f;
    private float halfFull = 60.0f;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_map, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView = (MapView) mView.findViewById(R.id.map);
        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());

        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        for (LocationItem locationItem : DataManager.getLocationItems()) {
            float color = 0.0f;
            if (locationItem.getFill() == -1) {
                color = notFull;
            } else if (locationItem.getFill() == 0) {
                color = halfFull;
            }
            googleMap.addMarker(new MarkerOptions().position(new LatLng(locationItem.getLatitude(),
                    locationItem.getLongitude())).title(locationItem.getName()).icon(BitmapDescriptorFactory.defaultMarker(color)).snippet("Time: " + locationItem.getTime()).visible(true));
        }

        googleMap.addMarker(new MarkerOptions().position(new LatLng(DataManager.getCurLatitude(), DataManager.getCurLongitude())).title("You :)").icon(BitmapDescriptorFactory.defaultMarker(currentLocation)).visible(true));

        CameraPosition bardeenQuad = CameraPosition.builder().target(new LatLng(40.111691, -88.227062)).zoom(17).bearing(0).tilt(45).build();
        CameraPosition currentLoc = CameraPosition.builder().target(new LatLng(DataManager.getCurLatitude(), DataManager.getCurLongitude())).zoom(17).bearing(0).tilt(45).build();

        if (DataManager.getCurLatitude() == 0 || DataManager.getCurLongitude() == 0) {
            //set camera position on the Bardeen quad if current location is invalid
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(bardeenQuad));
        } else {
            //set camera position on current location
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(currentLoc));
        }

    }
}
