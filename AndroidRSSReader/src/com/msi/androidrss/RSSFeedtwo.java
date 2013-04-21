package com.msi.androidrss;


import java.util.List;
import java.util.Vector;
import com.msi.androidrss.RSSItem;

public class RSSFeedtwo 
{
	private String _title2 = null;
	private String _pubdate2 = null;
	private int _itemcount2 = 0;
	private List<RSSItem> _itemlist2;
	
	
	RSSFeedtwo()
	{
		_itemlist2 = new Vector(0); 
	}
	int addItem(RSSItem item)
	{
		_itemlist2.add(item);
		_itemcount2++;
		return _itemcount2;
	}
	RSSItem getItem(int location)
	{
		return _itemlist2.get(location);
	}
	List getAllItems()
	{
		return _itemlist2;
	}
	int getItemCount()
	{
		return _itemcount2;
	}
	void setTitle(String title)
	{
		_title2 = title;
	}
	void setPubDate(String pubdate)
	{
		_pubdate2 = pubdate;
	}
	String getTitle()
	{
		return _title2;
	}
	String getPubDate()
	{
		return _pubdate2;
	}
	
	
}
