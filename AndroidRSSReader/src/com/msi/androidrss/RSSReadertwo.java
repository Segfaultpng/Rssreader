package com.msi.androidrss;

import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener; 
import android.util.Log;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;

import org.xml.sax.XMLReader;

import android.content.Intent;

import com.msi.androidrss.ShowDescription;

public class RSSReadertwo extends Activity implements OnItemClickListener
{

	public final String RSSFEEDOFCHOICE2 = "http://inside.nike.com/community/feeds/allcontent?communityID=2222";
	
	public final String tag2 = "RSSReader";
	private RSSFeed feed2 = null;
	
	/** Called when the activity is first created. */

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
            setContentView(R.layout.maintwo);
        
        // go get our feed!
        feed2 = getFeed(RSSFEEDOFCHOICE2);

        // display UI
        UpdateDisplay();
        
    }

    
    private RSSFeed getFeed(String urlToRssFeed)
    {
    	try
    	{
    		// setup the url
    	   URL url = new URL(urlToRssFeed);

           // create the factory
           SAXParserFactory factory = SAXParserFactory.newInstance();
           // create a parser
           SAXParser parser = factory.newSAXParser();

           // create the reader (scanner)
           XMLReader xmlreader = parser.getXMLReader();
           // instantiate our handler
           RSSHandler theRssHandler = new RSSHandler();
           // assign our handler
           xmlreader.setContentHandler(theRssHandler);
           // get our data via the url class
           InputSource is = new InputSource(url.openStream());
           // perform the synchronous parse           
           xmlreader.parse(is);
           // get the results - should be a fully populated RSSFeed instance, or null on error
           return theRssHandler.getFeed();
    	}
    	catch (Exception ee)
    	{
    		// if we have a problem, simply return null
    		return null;
    	}
    }
    public boolean onCreateOptionsMenu(Menu menu) 
    {
    	super.onCreateOptionsMenu(menu);
    	
    	menu.add(0,0,0, "Choose RSS Feed");
    	menu.add(0, 1, 1, "Refresh");
    	Log.i(tag2,"onCreateOptionsMenu");
    	return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
        case 0:
        	Log.i(tag2,"Set RSS Feed");
            return true;
        case 1:
        	Log.i(tag2,"Refreshing RSS Feed");
            return true;
        }
        return false;
    }
    
    
    private void UpdateDisplay()
    {
    //	  TextView feedtitle = (TextView) findViewById(R.id.feedtitle);
    //	  TextView feedpubdate = (TextView) findViewById(R.id.feedpubdate);
    	  ListView itemlist = (ListView) findViewById(R.id.itemlist);
  
        
        if (feed2 == null)
        {
   //     	 	feedtitle.setText("No RSS Feed Available");
        	return;
        }
        
     //      feedtitle.setText(feed2.getTitle());
    //      feedpubdate.setText(feed2.getPubDate());

        ArrayAdapter<RSSItem> adapter = new ArrayAdapter<RSSItem>(this,android.R.layout.simple_list_item_1,feed2.getAllItems());

           itemlist.setAdapter(adapter);
        
              itemlist.setOnItemClickListener(this);
        
              itemlist.setSelection(0);
        
    }
    
    
     public void onItemClick(AdapterView parent, View v, int position, long id)
     {
    	 Log.i(tag2,"item clicked! [" + feed2.getItem(position).getTitle() + "]");

    	 Intent itemintent = new Intent(this,ShowDescription.class);
         
    	 Bundle b = new Bundle();
    	 b.putString("title", feed2.getItem(position).getTitle());
    	 b.putString("description", feed2.getItem(position).getDescription());
    	 b.putString("link", feed2.getItem(position).getLink());
    	 b.putString("pubdate", feed2.getItem(position).getPubDate());
    	 
    	 itemintent.putExtra("android.intent.extra.INTENT", b);
         
         startActivityForResult(itemintent,0);
     }
    
}