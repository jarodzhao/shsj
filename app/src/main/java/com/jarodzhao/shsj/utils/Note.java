package com.jarodzhao.shsj.utils;
import java.util.*;
import java.io.*;

public class Note implements Serializable,Comparable<Note>
{
	
	private Integer order;
	
	private UUID id;

	private String title;

	private String content;

	private Date pub_date;
	
	private String favorited;

	public Note(){
		this.id = UUID.randomUUID();
	}
	
	public Note(UUID id, String title, String content, Date pubDate, String favorited){
		this.id = id;
		this.title = title;
		this.content = content;
		this.pub_date = pubDate;
		this.favorited = favorited;
	}

	public void setFavorited(String favorited)
	{
		this.favorited = favorited;
	}

	public String getFavorited()
	{
		return favorited;
	}

	public UUID getId()
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
	
	public Integer getOrder(){
		return order;
	}
	
	public void setOrder(Integer order){
		this.order = order;
	}
	
	@Override
	public int compareTo(Note note)
	{
		// TODO: Implement this method
		return this.getOrder().compareTo(note.getOrder());
	}
}
