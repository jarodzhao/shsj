package com.jarodzhao.shsj.utils;

import android.content.*;
import android.app.*;
import android.database.sqlite.*;
import android.util.*;
import android.widget.*;

import java.text.SimpleDateFormat;
import java.util.*;
import android.database.*;

public class NoteService
{
	ContentValues values;

	//保存方法
	public void saveNote(Activity activity, Note note)
	{
		DDHelper ddHelper = new DDHelper(activity, null, 1);
		SQLiteDatabase db = ddHelper.getWritableDatabase();


		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		values = new ContentValues();

		values.put("id", note.getId().toString());
		values.put("title", note.getTitle());
		values.put("content", note.getContent());
		values.put("pub_date", sdf.format(note.getPubDate()));

		values.put("favorited", note.getFavorited());

		db.insert("t_note", null, values);

		db.close();
	}

	public List<String> getAllFavorited(Context context)
	{
		List<String> result = new ArrayList<>();

		DDHelper ddHelper = new DDHelper(context, null, 1);
		SQLiteDatabase db = ddHelper.getWritableDatabase();
		
		//db.execSQL("update t_note set favorited = '0'");
		
		String sql = "SELECT favorited FROM t_note";
		Cursor cursor = db.query(sql,null,null,null,null,null,null);
		
		while(cursor.moveToNext())
		{
			result.add(cursor.getString(0));
		}
		
		return result;
	}
}
