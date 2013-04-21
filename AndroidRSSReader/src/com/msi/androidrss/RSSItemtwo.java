package com.msi.androidrss;

public class RSSItemtwo 
{
	private String _title2 = null;
	private String _description2 = null;
	private String _link2 = null;
	private String _category2 = null;
	private String _pubdate2 = null;

	
	RSSItemtwo()
	{
	}
	void setTitle2(String title)
	{
		_title2 = title;
	}
	void setDescription2(String description)
	{
		_description2 = description;
	}
	void setLink2(String link)
	{
		_link2 = link;
	}
	void setCategory2(String category)
	{
		_category2 = category;
	}
	void setPubDate2(String pubdate)
	{
		_pubdate2 = pubdate;
	}
	String getTitle2()
	{
		return _title2;
	}
	String getDescription2()
	{
		return _description2;
	}
	String getLink2()
	{
		return _link2;
	}
	String getCategory2()
	{
		return _category2;
	}
	String getPubDate2()
	{
		return _pubdate2;
	}
	public String toString2()
	{
		// limit how much text we display
		if (_title2.length() > 45)
		{
			return _title2.substring(0, 42) + "...";
		}
		return _title2;
	}
}
