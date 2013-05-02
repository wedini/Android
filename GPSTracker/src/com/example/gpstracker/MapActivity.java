package com.example.gpstracker;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MapActivity extends Activity {

	MapView mapview;
	GoogleMap gMap;
	MapController mc;
	
	Bundle extras;
	String lat, lon;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);
		
		//gMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapView)).getMap();
		    
		   // if (gMap!=null){
		
		
		mapview = (MapView) findViewById(R.id.mapView);
		mapview.setClickable(true);
		
		if (savedInstanceState != null) {
            extras = getIntent().getExtras();
            if (extras != null) {
                lat = extras.getString("latitude");
                lon = extras.getString("longitude");
            } 
		  }
		double lati = this.getIntent().getExtras().getDouble("latitude");
		double logi = this.getIntent().getExtras().getDouble("longitude");
		int latitude= (int) (lati*1000000);
        int longitude=(int) (logi*1000000);
        
        //Marker latMarker = gMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("Haarika"));
        //gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 15));
        GeoPoint gp=new GeoPoint(latitude, longitude);
       GeoPoint gp1=new GeoPoint((int)(24.456747*1000000), (int)(91.456747*1000000));

        
        
        mc.animateTo(gp);
       mc.setZoom(12);
        
        
		    
	}
	
	 
}



















/*public class MapActivity extends Activity {
MapView mapview;
MapController mc;
Bundle extras;
String lat, lon;
/** Called when the activity is first created. 
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.map);
    
    mapview=(MapView)findViewById(R.id.mapView);
    mapview.setClickable(true);
    
    
    // gathered all overlays already there in the mapview
   List<Overlay> mapoverlays=.getOverlays();
    
    
    
    //creating the marker drawable
    Drawable marker=getResources().getDrawable(R.drawable.ic_launcher);
    
    MyItemizedOverlay myItemizedOverlay=new MyItemizedOverlay(marker, this);
    
    if (savedInstanceState != null) {
        extras = getIntent().getExtras();
        if (extras != null) {
            lat = extras.getString("latitude");
            lon = extras.getString("longitude");
        } 
	  }
    
    //create geopoint
    double lati=Double.parseDouble(lat);
    double longi=Double.parseDouble(lon);
    int latitude= (int) (lati*1000000);
    int longitude=(int) (longi*1000000);
    GeoPoint gp=new GeoPoint(latitude, longitude);
    GeoPoint gp1=new GeoPoint((int)(24.456747*1000000), (int)(91.456747*1000000));
    
    mc.animateTo(gp);
    mc.setZoom(12);
    
    
    //create an OverlayItem
    OverlayItem item=new OverlayItem(gp, "My Position", "I live here");
    myItemizedOverlay.addOverlay(item);
    
    item=new OverlayItem(gp1, "Another", "I dont live here");
    myItemizedOverlay.addOverlay(item);
    
   
    mapoverlays.add(myItemizedOverlay);
}


}*/

