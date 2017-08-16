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

	MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		myAdapter = new MyAdapter(this, getNotes());
		
		mainListView = (ListView) findViewById(R.id.mainListView1);
		mainListView.setAdapter(myAdapter);
	}

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		// 取消后后，返回主界面
		switch (resultCode)
		{
			case 1:
				//Bundle bundle = data.getExtras();
				//Toast.makeText(this, "回来了😁！", Toast.LENGTH_SHORT).show();
				break;
			case 2:
				myAdapter = new MyAdapter(this, getNotes());
				mainListView.setAdapter(myAdapter);
				//Toast.makeText(this, "保存后返回...", Toast.LENGTH_SHORT).show();
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
		switch (item.getItemId())
		{
			case R.id.menu_add:
				//				菜单中的添加跳转
				Intent intent = new Intent(this, AddActivity.class);
				startActivityForResult(intent, 1);
				//startActivity(intent);
				return true;
			case R.id.menu_delete:
				Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
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

	List<Note> getNotes()
	{

		Cursor cursor = null;

		DDHelper ddHelper = new DDHelper(this, null, 1);
		SQLiteDatabase db = ddHelper.getWritableDatabase();
		
		Note note;
		List<Note> notes = new ArrayList<>();

		try
		{
			cursor = db.rawQuery("select * from t_note order BY pub_date desc", null);
			while (cursor.moveToNext())
			{
				note = new Note(UUID.fromString(cursor.getString(cursor.getColumnIndex("id"))), cursor.getString(cursor.getColumnIndex("title")),
								cursor.getString(cursor.getColumnIndex("content")), new Date());
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

		return notes;
	}


}
