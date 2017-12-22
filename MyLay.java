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


public class MyLay extends ItemizedOverlay<OverlayItem> 
{
  
 private ArrayList<OverlayItem> fixes = new ArrayList<OverlayItem>();
 
 public MyLay(Drawable pDefaultMarker,   ResourceProxy pResourceProxy) 
{
  super(pDefaultMarker, pResourceProxy);
  // TODO Auto-generated constructor stub
 }
  
 public void addItem(GeoPoint p, String title, String snippet)
 {
  OverlayItem newItem = new OverlayItem(title, snippet, p);

  if(title.equals("fix"))fixes.clear(); // remove previous location fixes 

  fixes.add(newItem);    // if not posn fix then it wont be cleared
  populate(); 
 }
 
 public boolean onSnapToItem(int arg0, int arg1, Point arg2, IMapView arg3)  {  return false; }
 protected OverlayItem createItem(int arg0) {  return fixes.get(arg0); } //this item will be created
 public int size()  {  return fixes.size(); }
 
}