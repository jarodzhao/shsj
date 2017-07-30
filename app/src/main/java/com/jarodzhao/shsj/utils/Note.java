package com.jarodzhao.shsj.utils;
import java.util.*;

public class Note
{
	private String id;

	private String title;

	private String content;

	private Date pub_date;

	public Note(){
		this.id = Common.getUuid();
	}

	public String getId()
	{
		return id;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getTitle()
	{
		return title;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getContent()
	{
		return content;
	}

	public void setPubDate(Date pub_date)
	{
		this.pub_date = pub_date;
	}

	public Date getPubDate()
	{
		return pub_date;
	}
}
