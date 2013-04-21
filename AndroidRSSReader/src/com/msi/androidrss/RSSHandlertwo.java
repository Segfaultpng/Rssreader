package com.msi.androidrss;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;
import android.util.Log;



public class RSSHandlertwo extends DefaultHandler 
{
	
	RSSFeed _feed2;
	RSSItem _item2;
	String _lastElementName2 = "";
	boolean bFoundChannel2 = false;
	final int RSS_TITLE2 = 1;
	final int RSS_LINK2 = 2;
	final int RSS_DESCRIPTION2 = 3;
	final int RSS_CATEGORY2 = 4;
	final int RSS_PUBDATE2 = 5;
	
	int depth2 = 0;
	int currentstate2 = 0;
	/*
	 * Constructor 
	 */
	RSSHandlertwo()
	{
	}
	
	/*
	 * getFeed - this returns our feed when all of the parsing is complete
	 */
	RSSFeed getFeed()
	{
		return _feed2;
	}
	
	
	public void startDocument() throws SAXException
	{
		// initialize our RSSFeed object - this will hold our parsed contents
		_feed2 = new RSSFeed();
		// initialize the RSSItem object - we will use this as a crutch to grab the info from the channel
		// because the channel and items have very similar entries..
		_item2 = new RSSItem();

	}
	public void endDocument() throws SAXException
	{
	}
	public void startElement(String namespaceURI, String localName,String qName, Attributes atts) throws SAXException
	{
		depth2++;
		if (localName.equals("channel"))
		{
			currentstate2 = 0;
			return;
		}
		if (localName.equals("image"))
		{
			// record our feed data - we temporarily stored it in the item :)
			_feed2.setTitle(_item2.getTitle());
			_feed2.setPubDate(_item2.getPubDate());
		}
		if (localName.equals("item"))
		{
			// create a new item
			_item2 = new RSSItem();
			return;
		}
		if (localName.equals("title"))
		{
			currentstate2 = RSS_TITLE2;
			return;
		}
		if (localName.equals("description"))
		{
			currentstate2 = RSS_DESCRIPTION2;
			return;
		}
		if (localName.equals("link"))
		{
			currentstate2 = RSS_LINK2;
			return;
		}
		if (localName.equals("category"))
		{
			currentstate2 = RSS_CATEGORY2;
			return;
		}
		if (localName.equals("pubDate"))
		{
			currentstate2 = RSS_PUBDATE2;
			return;
		}
		// if we don't explicitly handle the element, make sure we don't wind up erroneously 
		// storing a newline or other bogus data into one of our existing elements
		currentstate2 = 0;
	}
	
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException
	{
		depth2--;
		if (localName.equals("item"))
		{
			// add our item to the list!
			_feed2.addItem(_item2);
			return;
		}
	}
	 
	public void characters(char ch[], int start, int length)
	{
		String theString = new String(ch,start,length);
		Log.i("RSSReader","characters[" + theString + "]");
		
		switch (currentstate2)
		{
			case RSS_TITLE2:
				_item2.setTitle(theString);
				currentstate2 = 0;
				break;
			case RSS_LINK2:
				_item2.setLink(theString);
				currentstate2 = 0;
				break;
			case RSS_DESCRIPTION2:
				_item2.setDescription(theString);
				currentstate2 = 0;
				break;
			case RSS_CATEGORY2:
				_item2.setCategory(theString);
				currentstate2 = 0;
				break;
			case RSS_PUBDATE2:
				_item2.setPubDate(theString);
				currentstate2 = 0;
				break;
			default:
				return;
		}
		
	}
}
