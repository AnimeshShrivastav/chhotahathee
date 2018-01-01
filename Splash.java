package pkg.app;

import android.os.*;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.content.*;

public class Splash extends Activity
{
	public void onCreate(Bundle b)
	{
		super.onCreate(b);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);   
		
		ImageView iv=new ImageView(this);             

		iv.setImageResource(R.drawable.splash);
                setContentView(iv);
		
		new Handler().postDelayed(
						new Runnable() 
						{
						  public void run() { Splash.this.startActivity(new Intent(Splash.this,Login.class) );  } 
						}
			              ,3000 );
	}

 }