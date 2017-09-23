package com.jarodzhao.shsj;
import java.util.*;
import com.jarodzhao.shsj.utils.*;
import android.database.sqlite.*;
import android.content.*;
import android.database.*;
import android.util.*;
import java.text.*;
import android.widget.*;

public class NoteLab
{
	private static NoteLab sNoteLab;
	private List<Note> mNotes;
	private Note mNote;

	private int read=0;

	//构造方法
	public NoteLab(Context context)
	{
		mNotes = new ArrayList<>();

		DDHelper ddHelper = new DDHelper(context, null, 1);
		SQLiteDatabase db = ddHelper.getWritableDatabase();

		//从库中读取所有对象
		//for(int i=0;i<100;i++){
		//	mNote = new Note();
		//}

		Cursor cursor = null;

		StringBuffer strsql = new StringBuffer("select * from t_note ");

		strsql.append(" order BY pub_date desc");

		try
		{

			cursor = db.rawQuery(strsql.toString(), null);
			//Toast.makeText(context, read++ + "", Toast.LENGTH_SHORT).show();
			while (cursor.moveToNext())
			{

				mNote = new Note(UUID.fromString(cursor.getString(cursor.getColumnIndex("id"))),
								 cursor.getString(cursor.getColumnIndex("title")),
								 cursor.getString(cursor.getColumnIndex("content")), 
								 new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(
									 cursor.getString(cursor.getColumnIndex("pub_date"))),
								 cursor.getString(cursor.getColumnIndex("favorited")));
				mNotes.add(mNote);
			}
		}
		catch (Exception ex)
		{
			Log.d("jarod", ex.toString());
		}
		finally
		{
			cursor.close();
			db.close();
		}

	}

	//单例的 get
	public static NoteLab get(Context context)
	{
		if (sNoteLab == null)
		{
			sNoteLab = new NoteLab(context);
		}
		return sNoteLab;
	}

	//单例的方法
	public List<Note> getNotes()
	{
		return mNotes;
	}

	//单例的方法
	public Note getNote(UUID id)
	{
		for (Note note:mNotes)
		{
			if (note.getId().equals(id))
				return note;
		}

		return null;
	}

	//单例中的方法：检索
	public List<Note> Search(String keyword)
	{

		List<Note> result_list = new ArrayList<>();

		for (Note note:mNotes)
		{
			if (note.getTitle().indexOf(keyword) > 0 || note.getContent().indexOf(keyword) > 0)
			{
				result_list.add(note);
			}
		}

		return result_list;
	}
}
