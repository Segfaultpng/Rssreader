package com.msi.androidrss;

import android.app.Activity;
import android.os.Bundle;
import android.view.*;
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

public class RSSReader extends Activity implements OnItemClickListener
{

	public final String RSSFEEDOFCHOICE = "http://www.senatorwashington.com/feed";
	
	public final String tag = "RSSReader";
	private RSSFeed feed = null;
	
	/** Called when the activity is first created. */

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);
        
        // go get our feed!
        feed = getFeed(RSSFEEDOFCHOICE);

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
    	
    	menu.add(0,0,0, "Twitter");
    	menu.add(0, 1, 3, "Refresh");
    	menu.add(0, 1, 2, "News");
    	menu.add(0,1,1, "Events");
    	Log.i(tag,"onCreateOptionsMenu");
    	return true;
    }

    
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
        case 0:
        	Log.i(tag,"Set RSS Feed");
            return true;
        case 1:
        	Log.i(tag,"Refreshing RSS Feed");
            return true;
        }
        return false;
    }
    
    
    private void UpdateDisplay()
    {
   //    TextView feedtitle = (TextView) findViewById(R.id.feedtitle);
    //    TextView feedpubdate = (TextView) findViewById(R.id.feedpubdate);
        ListView itemlist = (ListView) findViewById(R.id.itemlist);
  
        
        if (feed == null)
        {
       	itemlist.setFilterText("No RSS Feed Available");
        	return;
        }
        
     //   feedtitle.setText(feed.getTitle());
     //   feedpubdate.setText(feed.getPubDate());

       @SuppressWarnings("unchecked")
	ArrayAdapter<RSSItem> adapter = new ArrayAdapter<RSSItem>(this,android.R.layout.simple_list_item_1,feed.getAllItems());

           itemlist.setAdapter(adapter);
        
           itemlist.setOnItemClickListener(this);
        
          itemlist.setSelection(0);
          
          
        
    }
    public void refresh(){
    	UpdateDisplay();
    }
    
    
    
     public void onItemClick(AdapterView<?> parent, View v, int position, long id)
     {
    	 Log.i(tag,"item clicked! [" + feed.getItem(position).getTitle() + "]");

    	 Intent itemintent = new Intent(this,ShowDescription.class);
         
    	 Bundle b = new Bundle();
    	 b.putString("title", feed.getItem(position).getTitle());
    	 b.putString("description", feed.getItem(position).getDescription());
    	 b.putString("link", feed.getItem(position).getLink());
    	 b.putString("pubdate", feed.getItem(position).getPubDate());
    	 
    	 itemintent.putExtra("android.intent.extra.INTENT", b);
         
         startActivityForResult(itemintent,0);
     }
    
}