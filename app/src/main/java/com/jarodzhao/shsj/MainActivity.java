package com.jarodzhao.shsj;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.View.*;
import android.view.*;
import com.jarodzhao.shsj.utils.*;
import android.util.*;
import android.database.sqlite.*;
import android.database.*;
import android.content.*;

import java.util.List;
import java.util.ArrayList;
import java.util.*;

public class MainActivity extends Activity implements OnClickListener
{
	TextView mainTextView;
	EditText mainEditText;
	Button mainButton;
	ListView mainListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		insertDate();

		mainListView = (ListView) findViewById(R.id.mainListView1);

		//Log.d("jarod", ddHelper.getDatabaseName());

		DDHelper ddHelper = new DDHelper(this, null, 1);
		SQLiteDatabase db = ddHelper.getWritableDatabase();

		//db.execSQL("delete from t_note");

		Cursor cursor = null;

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		try
		{
			cursor = db.rawQuery("select * from t_note", null);

			while (cursor.moveToNext())
			{
				Map<String,Object> map = new HashMap<String, Object>();

				map.put("title", cursor.getString(cursor.getColumnIndex("title")));
				map.put("content", cursor.getString(cursor.getColumnIndex("content")));
				map.put("pub_date", cursor.getString(cursor.getColumnIndex("pub_date")));
				list.add(map);
			}

			SimpleAdapter sa = new SimpleAdapter(this, list,
												 R.layout.layout_list_style,
												 new String[]{"title","content","pub_date"},
												 new int[]{R.id.text_title, R.id.text_content,R.id.text_date}
												 );

			mainListView.setAdapter(sa);
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

	//生成测试数据
	void insertDate()
	{

		DDHelper ddHelper = new DDHelper(this, null, 1);
		SQLiteDatabase db = ddHelper.getWritableDatabase();

		Note note;
		ContentValues values;

		for (int i=1;i < 20;i++)
		{
			note  = new Note();
			values = new ContentValues();

			values.put("id", note.getId());
			values.put("title", "abc #" + i);
			values.put("content", "这里显示正文内容... #" + i);
			values.put("pub_date", new Date().toLocaleString());

			db.insert("t_note", null, values);
		}

		db.close();
	}

	@Override
	public void onClick(View view)
	{
		switch (view.getId())
		{
			
		}
	}
}
