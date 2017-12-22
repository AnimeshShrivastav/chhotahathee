package com.cargo;


//using android-23 
 

import android.content.*;
import android.os.*;
import android.app.*;
import android.location.*;

//using java 1.6

import java.util.*;



public class Locator 
{

  LocationListener ll; 
  LocationManager lm;
  Location fix;
  Context ctx;

  public Locator(Context c)
  {
    ll=null;lm=null;fix=null;
    ctx=c;
    init();
  }

  void init()
  {
    lm = (LocationManager)ctx.getSystemService(Context.LOCATION_SERVICE);
    ll= new LocationListener()
 	{
  	public void onLocationChanged(Location location) {   fix=location;  }
	public void onProviderDisabled(String provider) { }
        public void onProviderEnabled(String provider) {  }
        public void onStatusChanged(String provider, int status, Bundle extras) { }
        };

    fix = lm.getLastKnownLocation( LocationManager.GPS_PROVIDER);
    if(fix==null)fix = lm.getLastKnownLocation( LocationManager.NETWORK_PROVIDER);
    lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000,0f , ll);
   lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0f, ll);
   }

}