package com.jarodzhao.shsj.utils;

import android.content.*;
import android.app.*;
import android.database.sqlite.*;
import android.util.*;
import android.widget.*;

public class NoteService
{
	ContentValues values;

	//保存方法
	public void saveNote(Activity activity, Note note)
	{
		DDHelper ddHelper = new DDHelper(activity, null, 1);
		SQLiteDatabase db = ddHelper.getWritableDatabase();

		values = new ContentValues();

		values.put("id", note.getId().toString());
		values.put("title", note.getTitle());
		values.put("content", note.getContent());
		values.put("pub_date", note.getPubDate().toLocaleString());

		db.insert("t_note", null, values);
		
		db.close();
	}
}
