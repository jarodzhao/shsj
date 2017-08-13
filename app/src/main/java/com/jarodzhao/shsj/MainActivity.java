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


		findView();

		Note note  = new Note();
		DDHelper ddHelper = new DDHelper(this, null, 1);
		SQLiteDatabase db = ddHelper.getWritableDatabase();

		Log.d("jarod", ddHelper.getDatabaseName());

		Cursor cursor = null;
		ContentValues values = new ContentValues();
		values.put("id", note.getId());
		values.put("title", "abc123");
		values.put("content", "这里显示正文内容...");
		values.put("pub_date", "2017-07-30");
		long a = db.insert("t_note", null, values);

		List<String> list = new ArrayList<String>();
		
		try
		{
			cursor = db.rawQuery("select * from t_note", null);

			while (cursor.moveToNext())
				list.add(cursor.getString(cursor.getColumnIndex("id")) + " __ ");

			ArrayAdapter sa = new ArrayAdapter(this,
						android.R.layout.simple_list_item_1,
						list);
//			SimpleCursorAdapter sca = new SimpleCursorAdapter(this, 
//															  android.R.layout.simple_list_item_1,
//															  cursor,
//															  new String[]{"id"},
//															  new int[]{android.R.id.text1});
			mainListView.setAdapter(sa);

		}
		catch (Exception ex)
		{
			Log.d("jarod", "error=" + ex.toString());
		}
		finally
		{
			cursor.close();
			db.close();
		}

    }

	//初始化控件方法
	void findView()
	{
		//mainTextView = (TextView) findViewById(R.id.mainTextView1);
		mainEditText = (EditText) findViewById(R.id.mainEditText1);
		mainListView = (ListView) findViewById(R.id.mainListView1);
		mainButton = (Button) findViewById(R.id.mainButton1);
		mainButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View view)
	{
		switch (view.getId())
		{
			case R.id.mainButton1:

				break;
		}
	}
}
