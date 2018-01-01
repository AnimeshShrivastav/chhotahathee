package pkg.app;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.content.*;

import com.backendless.*;
import com.backendless.persistence.*;

import java.util.*;


public class Backend 
{
	Context c;
	Global g;
	OnlineUsers u;

	Map saved;
	DataQueryBuilder query;

	IDataStore<OnlineUsers> table;
	List<OnlineUsers> result;

	public Backend(Context ctx)
    	{
    		this.c=ctx;
		g=(Global) c;

                u=new OnlineUsers();
    		u.location=g.ucell;
		u.username=g.umail;
		u.password=g.upass;

		result=null;
		
		Toast.makeText(c,"Logging..",Toast.LENGTH_LONG).show();

		Backendless.setUrl( Defaults.SERVER_URL );
        	Backendless.initApp( c, Defaults.APPLICATION_ID, Defaults.API_KEY );
		
		table=Backendless.Data.of( OnlineUsers.class );

		query=DataQueryBuilder.create();
		String where="username = '"+ u.username +"'";
		query.setWhereClause(where);
		result=table.find(query);
		
		if(result.size()==0)
			{
				Toast.makeText(c,"Registering as new user..",Toast.LENGTH_LONG).show();
				g.register=true;
				table.save(u);
			}
		if(result.size()==1)
			{
				Toast.makeText(c,"You are successfully logged in...",Toast.LENGTH_LONG).show();
				g.login=true;	
			}
		
	}

}                                    