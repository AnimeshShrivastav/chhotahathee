package com.cargo;


//using ver 4.2 of osmdroid
 
import org.osmdroid.*;
import org.osmdroid.api.*;
import org.osmdroid.util.*;
import org.osmdroid.views.*;
import org.osmdroid.views.overlay.*;
import org.osmdroid.views.overlay.mylocation.*;
import org.osmdroid.views.overlay.compass.*;

//using android-23 
 
import android.os.*;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.widget.*;
import android.view.*;
import android.location.*;

//using java 1.6

import java.util.*;


 
public class HomePage extends Activity implements Runnable
{


  MyLay fixLay,truckLay;


  MapView mv ;
  IMapController imc;
  ResourceProxy rp;
  Drawable manMark,truckMark;

  ArrayList<OverlayItem> fixes,marks;
  Locator loc;
  Thread t;
  Location lastFix=null,nowFix=null;

  public void run()
  {
         while(true)
         {
                 nowFix=loc.fix;
                 if( nowFix != lastFix && nowFix!=null ) updateLoc(nowFix);
                 lastFix=nowFix;
                 try{Thread.sleep(1000);}catch(Exception e){}
         }
  }

  

  void startThread()
  {
      t=new Thread(this);
      t.start();
  }

 
  void updateLoc(Location l)
  {
     GeoPoint gp = new GeoPoint(l.getLatitude(), l.getLongitude());


     Random r = new Random();int i = r.nextInt(100);
     GeoPoint gp2 = new GeoPoint( l.getLatitude()+(0.0001*i) , l.getLongitude()+(0.0001*i) );  //simmulate random truck posns

     imc.setCenter(gp);  //center on map

     fixLay.addItem(gp,"fix","fix");

     truckLay.addItem(gp2,"truck","truck");

     mv.postInvalidate();   //because we came here by a thread of another class Locator's object 'loc' which is listening & reacting to location-updates
                            //thats why we cant directly us invalidate() we must post it so that it gets invalidated in the UI thread later on.
  }

 

 
    public void onCreate(Bundle b) 
    {
        super.onCreate(b);
        setContentView(R.layout.main);

	
    } 

    public void onResume()
    {
        super.onResume();
      try{
             init();    // without try catch it will unfortunately stop
         }catch(Exception e){Toast.makeText(this,e.toString(),Toast.LENGTH_LONG);}
    }

   
void init()
{
loc=new Locator(this);

setMap();
setMarks();
setLays();
startThread();
}


void setMap()
{
        mv= (MapView) findViewById(R.id.mapview);
        mv.setBuiltInZoomControls(true);
	mv.setMultiTouchControls(true);
        imc = mv.getController();
        imc.setZoom(15);

        
        if(loc.fix !=null )imc.setCenter(new GeoPoint ( loc.fix.getLatitude(),loc.fix.getLongitude() ) );
        rp = new DefaultResourceProxyImpl(getApplicationContext());
}

void setMarks()
{
       
	manMark=getResources().getDrawable(R.drawable.man);
        int w  = manMark.getIntrinsicWidth();
        int h  = manMark.getIntrinsicHeight();
        manMark.setBounds(0, h,w, 0);

	truckMark=getResources().getDrawable(R.drawable.truck);
        w  = truckMark.getIntrinsicWidth();
        h  = truckMark.getIntrinsicHeight();
        truckMark.setBounds(0, h,w, 0);


}

void setLays()
{
	ScaleBarOverlay myScaleBarOverlay = new ScaleBarOverlay(this);
	mv.getOverlays().add(myScaleBarOverlay);

	fixLay = new MyLay(manMark, rp);
	truckLay = new MyLay(truckMark, rp);

	mv.getOverlays().add(fixLay);
	mv.getOverlays().add(truckLay);

}


}

