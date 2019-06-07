package app.mycompany.warnapp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

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

        // Add a marker in Sydney and move the camera
        LatLng Lima = new LatLng(-12.02175347671593, -77.04777275897878);
        mMap.addMarker(new MarkerOptions().position(Lima).title("Ubicacion Actual ").snippet("UNI").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Lima,13));


        LatLng SMP = new LatLng(-12.0062974, -77.0587863);
        mMap.addMarker(new MarkerOptions().position(SMP).title("Robo de celular").snippet(" Cerca a Plaza Norte 4pm").icon(BitmapDescriptorFactory.fromResource(R.drawable.referencia)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SMP,13));


        LatLng Rimac = new LatLng(-12.024231066357245, -77.03881091175897);
        mMap.addMarker(new MarkerOptions().position(Rimac).title("Robo con arma").snippet("Barristas Hinchas de Alianza Lima").icon(BitmapDescriptorFactory.fromResource(R.drawable.referencia)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Rimac,13));


        LatLng Independencia = new LatLng(-12.009649513643595, -77.05076285985106);
        mMap.addMarker(new MarkerOptions().position(Independencia).title("Robo de automovil").snippet("cerca a la plaza Metro UNI 3 pm").icon(BitmapDescriptorFactory.fromResource(R.drawable.referencia)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Independencia,13));


        LatLng Bolognesi = new LatLng(-12.059667229549092, -77.04223751667847);
        mMap.addMarker(new MarkerOptions().position(Bolognesi).title("Robo de cartera").snippet("cerca a la plaza Bolognesi 3 pm").icon(BitmapDescriptorFactory.fromResource(R.drawable.referencia)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Bolognesi,13));


        LatLng Metro = new LatLng(-12.02850207920963, -77.04996739640971);
        mMap.addMarker(new MarkerOptions().position(Metro).title("Raqueteros robaron a un grupo de Estudiantes").snippet("Cerca a caqueta 8 pm").icon(BitmapDescriptorFactory.fromResource(R.drawable.referencia)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Metro,13));



        LatLng Victoria = new LatLng(-12.0739937, -77.0181966);
        mMap.addMarker(new MarkerOptions().position(Victoria).title("Robo con arma blanca de pertenencias").snippet("Cuadra 6 avenida Mexico 10 pm").icon(BitmapDescriptorFactory.fromResource(R.drawable.referencia)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Victoria,13));




        LatLng Tacna = new LatLng(-12.06381996662374, -77.03454367412584);
        mMap.addMarker(new MarkerOptions().position(Tacna).title("Estafa de productos adquiridos").snippet(" Av 28 de julio cuadra 15 ").icon(BitmapDescriptorFactory.fromResource(R.drawable.referencia)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Tacna,13));

        LatLng Miraflores = new LatLng(-12.1322691, -77.0301446);
        mMap.addMarker(new MarkerOptions().position(Miraflores).title("Robo de celular y camara de Turista").snippet("cerca a LarcoMar 3 pm").icon(BitmapDescriptorFactory.fromResource(R.drawable.referencia)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Miraflores,13));

    }
}
