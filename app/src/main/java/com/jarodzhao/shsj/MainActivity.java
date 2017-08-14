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

		//Log.d("jarod", ddHelper.getDatabaseName());

		DDHelper ddHelper = new DDHelper(this, null, 1);
		SQLiteDatabase db = ddHelper.getWritableDatabase();

		//db.execSQL("delete from t_note");

		Cursor cursor = null;

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		try
		{
			cursor = db.rawQuery("select * from t_note order BY pub_date desc", null);

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
