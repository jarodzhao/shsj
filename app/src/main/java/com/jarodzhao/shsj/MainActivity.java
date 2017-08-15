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

		mainListView = (ListView) findViewById(R.id.mainListView1);

		DDHelper ddHelper = new DDHelper(this, null, 1);
		SQLiteDatabase db = ddHelper.getWritableDatabase();

		Cursor cursor = null;

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

		MyAdapter myAdapter = new MyAdapter(this, notes);
		
		mainListView.setAdapter(myAdapter);
		
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
				Intent intent = new Intent(this, AddActivity.class);
				startActivity(intent);
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


}
