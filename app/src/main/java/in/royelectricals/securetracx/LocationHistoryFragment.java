package in.royelectricals.securetracx;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.ButtCap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;

public class LocationHistoryFragment extends Fragment {

    MapView mMapView;
    private GoogleMap googleMap;
    public LocationHistoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_location_history, container, false);

        getActivity().setTitle("POI Location");
        mMapView = (MapView) rootView.findViewById(R.id.mapViewHis);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                googleMap.isMyLocationEnabled();
                // For dropping a marker at a point on the Map

                //polyline

                Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
                        .clickable(true)
                        .color(Color.BLUE)
                        .geodesic(true)
                        .startCap(new ButtCap())
                        .width(20)
                        .endCap(new RoundCap())
                        .jointType(JointType.ROUND)
                        .add(
                                new LatLng(25.573946, 84.87007),
                                new LatLng(25.150663, 85.81852),
                                new LatLng(25.947538, 84.88026),
                                new LatLng(26.674435, 85.66113),
                                new LatLng(26.423178, 85.171974),
                                new LatLng(25.618444, 83.09053))
                );


                LatLng loc = new LatLng(25.573946, 84.87007);
                googleMap.addMarker(new MarkerOptions()
                        .position(loc)
                        .title("Start")
                        .icon(vectorToBitmap(R.drawable.ic_directions_car, Color.parseColor("#ff0000")))
                        .snippet("Live position of your vechile"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(25.618444, 83.09053))
                        .title("Stop")
                        .icon(vectorToBitmap(R.drawable.ic_directions_car, Color.parseColor("#00ff00")))
                );
                //  For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(loc).zoom(8).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        return rootView;
    }

    private BitmapDescriptor vectorToBitmap(@DrawableRes int id, @ColorInt int color) {
        Drawable vectorDrawable = ResourcesCompat.getDrawable(getResources(), id, null);
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        DrawableCompat.setTint(vectorDrawable, color);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);

    }

}