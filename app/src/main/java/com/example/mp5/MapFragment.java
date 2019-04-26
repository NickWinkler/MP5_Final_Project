package com.example.mp5;

import android.os.Bundle;
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
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap mGoogleMap;
    MapView mMapView;
    View mView;

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

        for (String[] location : LocationData.locationData) {
            googleMap.addMarker(new MarkerOptions().position(new LatLng(Double.valueOf(location[2]),
                    Double.valueOf(location[3]))).title(location[0]).visible(true));
        }
        /*
        //DCL markers
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.113183, -88.226328)).title("DCL L416").visible(true));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.113175, -88.226435)).title("DCL L426").visible(true));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.113167, -88.226548)).title("DCL L440").visible(true));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.113269, -88.226569)).title("DCL L520").visible(true));

        //ECEB markers
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.114745, -88.227947)).title("ECEB 2022").visible(true));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.114692, -88.227888)).title("ECEB 3022").visible(true));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.115090, -88.227908)).title("ECEB 3070").visible(true));

        //EH markers
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.110995, -88.227061)).title("EH 406B1").visible(true));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.110995, -88.226879)).title("EH 406B8").visible(true));

        //ESPL & FAR TRE markers
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.111788, -88.222519)).title("ESPL").visible(true));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.098943, -88.220494)).title("FAR TRE 231").visible(true));

        //GELIB markers
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.112426, -88.226842)).title("GELIB 4C").visible(true));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.112352, -88.226504)).title("GELIB 4E").visible(true));

        //MEL markers
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.111607, -88.226448)).title("MEL 1001").visible(true));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.111850, -88.226448)).title("MEL 1009").visible(true));

        //PAR & REC markers
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.099894, -88.220363)).title("PAR 104").visible(true));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.101040, -88.235533)).title("REC 121E").visible(true));

        //SDRP markers
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.103535, -88.234682)).title("SDRP 1010").visible(true));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.103444, -88.234634)).title("SDRP 2054").visible(true));

        //SIEBL markers
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.113729, -88.225238)).title("SIEBL 0218").visible(true));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.113737, -88.225002)).title("SIEBL 0220").visible(true));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.113754, -88.224900)).title("SIEBL 0222").visible(true));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.114016, -88.224514)).title("SIEBL 0403").visible(true));

        //TB markers
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.111471, -88.225280)).title("TB 207").visible(true));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.111898, -88.225280)).title("TB 302").visible(true));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.111922, -88.225119)).title("TB 316").visible(true));
        */

        //set camera position on the main quad
        CameraPosition MainQuad = CameraPosition.builder().target(new LatLng(40.108376, -88.227210)).zoom(17).bearing(0).tilt(45).build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(MainQuad));
    }
}
