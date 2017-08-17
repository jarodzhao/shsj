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
import java.text.*;

public class MainActivity extends Activity implements OnClickListener
{
	TextView mainTextView;
	EditText mainEditText;
	Button mainButton;
	ListView mainListView;

	List<Note> notes;
	MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		myAdapter = new MyAdapter(this, getNotes(null));

		mainListView = (ListView) findViewById(R.id.mainListView1);
		mainListView.setAdapter(myAdapter);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		// 返回主界面
		switch (resultCode)
		{
			case 1:		//取消保存

//				DDHelper ddHelper = new DDHelper(this, null, 1);
//				SQLiteDatabase db = ddHelper.getWritableDatabase();
//				db.execSQL("delete from t_note where pub_date like '%15 16%'");
//				db.close();

				break;
			case 2: 	//保存后

				Note note = (Note) data.getSerializableExtra("new");
				notes.add(note);

				myAdapter.notifyDataSetChanged();

				break;
			case 3:	//搜索提交
				String keyword = data.getStringExtra("keyword");
				
				myAdapter = new MyAdapter(MainActivity.this, getNotes(keyword));
				mainListView = (ListView) findViewById(R.id.mainListView1);
				
				mainListView.setAdapter(myAdapter);
				break;
		}
	}




	@Override
	public void onClick(View view)
	{
		switch (view.getId())
		{

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.options_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		super.onOptionsItemSelected(item);
		Intent intent;
		switch (item.getItemId())
		{
			case R.id.menu_add:
				//菜单中的添加
				intent = new Intent(this, AddActivity.class);
				startActivityForResult(intent, 1);
				return true;
			case R.id.menu_search:
				//菜单中的查找
				intent = new Intent(this, SearchActivity.class);
				startActivityForResult(intent, 1);
				return true;
			case R.id.menu_setup:
				Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
				return true;
			case R.id.menu_about:
				Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
				return true;
		}

		return false;

	}

	List<Note> getNotes(String keyword)
	{

		Cursor cursor = null;

		DDHelper ddHelper = new DDHelper(this, null, 1);
		SQLiteDatabase db = ddHelper.getWritableDatabase();

		Note note;
		notes = new ArrayList<>();
		StringBuffer strsql = new StringBuffer("select * from t_note ");

		if(keyword != null && keyword != "")
			strsql.append(" where title like '%" + keyword +"%' or content like '%" + keyword +"%'");
			
		strsql.append(" order BY pub_date desc");
		
		try
		{
			cursor = db.rawQuery(strsql.toString(), null);
			while (cursor.moveToNext())
			{

				note = new Note(UUID.fromString(cursor.getString(cursor.getColumnIndex("id"))),
								cursor.getString(cursor.getColumnIndex("title")),
								cursor.getString(cursor.getColumnIndex("content")), 
								new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(
									cursor.getString(cursor.getColumnIndex("pub_date"))));
				notes.add(note);
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

		//Collections.sort(notes);

		return notes;
	}


}
