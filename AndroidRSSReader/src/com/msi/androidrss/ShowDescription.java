package com.msi.androidrss;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.view.*;

public class ShowDescription extends Activity 
{
    public void onCreate(Bundle icicle) 
    {
        super.onCreate(icicle);
          setContentView(R.layout.showdescription);
        
        String theStory2 = null;
        
        
        Intent startingIntent2 = getIntent();
        
        if (startingIntent2 != null)
        {
        	Bundle b = startingIntent2.getBundleExtra("android.intent.extra.INTENT");
        	if (b == null)
        	{
        		theStory2 = "bad bundle?";
        	}
        	else
    		{
        		theStory2 = b.getString("title") + "\n\n" + b.getString("pubdate") + "\n\n" + b.getString("description").replace('\n',' ') + "\n\nMore information:\n" + b.getString("link");
    		}
        }
        else
        {
        	theStory2 = "Information Not Found.";
        
        }
        
            TextView db= (TextView) findViewById(R.id.storybox);
           db.setText(theStory2);
        
          Button backbutton = (Button) findViewById(R.id.back);
        
            backbutton.setOnClickListener(new Button.OnClickListener() 
        {
        	         public void onClick(View v) 
            {
            	finish();
            }
                  });        
    }
}

