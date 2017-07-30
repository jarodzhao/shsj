package com.jarodzhao.shsj.utils;

import android.database.sqlite.*;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DDHelper extends SQLiteOpenHelper
{
	final static private String db_name = "shsj.db3";
	final private String CREATE_TABLE_SQL = "create table IF NOT EXISTS t_note (id , title , content, pub_date)";


	public DDHelper(Context context, CursorFactory factory, int version) 
	{
		super(context, db_name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL(CREATE_TABLE_SQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase p1, int p2, int p3)
	{

	}


}
