package pkg.app;

import android.os.*;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.location.*;
import android.graphics.*;


import org.osmdroid.*;
import org.osmdroid.util.*;
import org.osmdroid.views.*;

import java.util.*;

public class MyMap extends Activity implements Runnable
{
 	Locator loc;
	Thread t;
	Location last=null,now=null;
	MapView mv;
	TextView tv;
        Global g;

	public void onCreate(Bundle b)
	{
		super.onCreate(b);
           	setContentView(R.layout.map);

		g=(Global) getApplicationContext();

		tv = new TextView(this);
		tv.setText(g.umail);
		tv.setTextColor(0xFF00FF00);
		tv.setBackgroundColor(0x88ff0000);

		WindowManager.LayoutParams wlp = new WindowManager.LayoutParams();
		wlp.gravity = Gravity.LEFT | Gravity.BOTTOM;
		wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
		wlp.width = WindowManager.LayoutParams.WRAP_CONTENT;
		wlp.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN 
				|  WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE ;
		wlp.format = PixelFormat.TRANSLUCENT;
		//wlp.x=0;
		//wlp.y=0;
 
		WindowManager wm = (WindowManager) getSystemService("window");
		wm.addView(tv, wlp);
		



	}

	public void onResume() 
    	{
        	super.onResume(); 
		startThread();   
       	}

	public void run()  
  	{
        	while(true) 
         	{
                 now=loc.l;                                       
                 if( now != last && now!=null )updateLoc(now); 
                 last=now;
                 try{Thread.sleep(1000);}catch(Exception e){} 
         	}
  	}


	void startThread()
  	{
		mv = (MapView) findViewById(R.id.mv);
      		mv.setBuiltInZoomControls(true);
		mv.getController().setZoom(12);

		loc=new Locator(getApplicationContext());

	      	t=new Thread(this);
      		t.start();          
	}
 
 	void updateLoc(Location l)  
  	{
		
		mv.getController().setCenter( new GeoPoint(l.getLatitude(), l.getLongitude()));  
		tv.bringToFront();
		tv.postInvalidate();
	}

}




   

 
