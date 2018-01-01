package pkg.app;

import android.os.*;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.text.*;


public class Login extends Activity
{
	EditText et1,et2,et3;
	Global g;

	public void onCreate(Bundle b)
	{
		super.onCreate(b);
		setContentView(R.layout.login);
	}

        public void login(View v)
        {
		g=(Global) getApplicationContext();
		

		et1=(EditText)findViewById(R.id.umail);
		et2=(EditText)findViewById(R.id.upass);
		et3=(EditText)findViewById(R.id.ucell);

                g.umail=""+et1.getText();
		g.upass=""+et2.getText();
		g.ucell=""+et3.getText();
		
		if(!validate())return;		

		new Backend(getApplicationContext());
              	startActivity(new Intent(Login.this,MyMap.class) );               
        }

	boolean validate()
	{

		if((g.umail).length()==0)
		{
			Toast.makeText(getApplicationContext(), "Email cannot be Blank", Toast.LENGTH_LONG).show();
			et1.setError("Email cannot be Blank");
			return false;
		}

		
		else if( !android.util.Patterns.EMAIL_ADDRESS.matcher(g.umail).matches() )
		{
		//Validation for Invalid Email Address
			Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_LONG).show();
			et1.setError("Invalid Email");
			return false;
		}

		if (  ( (g.upass).length() < 6 ) || (  TextUtils.isEmpty ( (g.upass).trim() ) ) )
		{//Validation for Invalid Password
			Toast.makeText(getApplicationContext(), "Invalid Password", Toast.LENGTH_LONG).show();
			et2.setError("Invalid Password:- Blank or Less than 6 characters");
			return false;
		}
		
		
		return true;
	}
}

