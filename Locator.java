package pkg.app;

import android.content.*;
import android.os.*;
import android.app.*;
import android.location.*;

import java.util.*;

public class Locator 
{
  LocationListener ll;
  LocationManager lm;
  Location l; 
  Context ctx;                                      

  public Locator(Context c)
  {
    	ll=null;lm=null;l=null;ctx=c;
    	ll= new LocationListener()   
 	{
  	public void onLocationChanged(Location l) {   Locator.this.l=l;  }  
        public void onProviderEnabled(String provider) {  }
	public void onProviderDisabled(String provider) {  }
        public void onStatusChanged(String provider, int status, Bundle extras) { }
        };
    locate();
  }

  void locate()
  {
    lm = (LocationManager)ctx.getSystemService(Context.LOCATION_SERVICE); 
    l = lm.getLastKnownLocation( LocationManager.GPS_PROVIDER);           
    if(l==null)l = lm.getLastKnownLocation( LocationManager.NETWORK_PROVIDER); 
    lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000,0f , ll);     
    lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0f, ll); 
   }

}
