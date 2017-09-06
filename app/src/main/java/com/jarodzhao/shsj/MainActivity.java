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

import java.util.*;
import java.text.*;
import android.widget.AdapterView.*;
import android.animation.*;

public class MainActivity extends ListActivity
{
	List<Note> notes;
	MyAdapter myAdapter;
	String keyword;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);

		getListView().setDividerHeight(0);
		myAdapter = new MyAdapter(this, getNotes(null));
		setListAdapter(myAdapter);

		getListView().setOnTouchListener(new OnTouchListener(){
			
				@Override
				public boolean onTouch(View view, MotionEvent event)
				{
					// listview 的点击事件捕捉
					ListView v = (ListView) view;
					int position = v.pointToPosition((int)event.getX(), (int)event.getY());
					//Toast.makeText(MainActivity.this, position + "...", Toast.LENGTH_SHORT).show();
					
					switch(event.getAction()){
						case event.ACTION_DOWN:
//							Toast.makeText(MainActivity.this, "按下", Toast.LENGTH_SHORT).show();
							break;
						case event.ACTION_UP:
//							Toast.makeText(MainActivity.this, "抬起",Toast.LENGTH_SHORT).show();
							break;
					}
					
					return false;
				}
				
			
		});
		
		//点击列表中的项目后，根据列表中的内容生成一个note对象
		//传递到做的视图中，新视图中不用再读取数据库了
		getListView().setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id)
				{
					
					Note note = new Note();
					String title = (String) ((TextView) view.findViewById(R.id.text_title)).getText();
					String content = (String) ((TextView) view.findViewById(R.id.text_content)).getText();
					String pubDate = (String)((TextView) view.findViewById(R.id.text_date)).getText();
					//String favorited = (String) ((TextView)view.findViewById(R.id.text_favoried)).getText();
					
					//首页中listview没有checkbox的收藏???
					//CheckBox favorited = (CheckBox) view.findViewById(R.id.checkbox_favorited);
					ImageView iv_favorited = (ImageView) view.findViewById(R.id.iv_favorited);
					if(iv_favorited.VISIBLE == View.GONE)
						note.setFavorited("0");
					else
						note.setFavorited("1");
						//Toast.makeText(MainActivity.this, iv_favorited.VISIBLE + "",Toast.LENGTH_SHORT).show();
						
					//显示当前记录的收藏值
//					Toast.makeText(MainActivity.this,iv_favorited.VISIBLE,Toast.LENGTH_SHORT).show();
					
					
					note.setTitle(title);
					note.setContent(content);
					//note.setFavorited(favorited);
					try{
					note.setPubDate(new SimpleDateFormat("M-dd HH:mm").parse(pubDate));
					}catch(Exception e){}
					
					
					Bundle bundle = new Bundle();
					bundle.putSerializable("note",note);
					
					Intent intent = new Intent(MainActivity.this, ContentActivity.class);
					
					intent.putExtras(bundle);
					startActivity(intent);
				}
			});
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		// 返回主界面
		switch (resultCode)
		{
			case 1:		//取消保存
		
				break;

			case 2: 	//保存后
				Note note = (Note) data.getSerializableExtra("new");
				notes.add(note);

				myAdapter.notifyDataSetChanged();
				break;

			case 3:	//搜索提交
				keyword = data.getStringExtra("keyword");

				myAdapter = new MyAdapter(MainActivity.this, getNotes(keyword));

				setListAdapter(myAdapter);
				break;
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
				if (keyword != null && keyword != "")
					intent.putExtra("keyword", keyword);
				startActivityForResult(intent, 1);
				return true;
			case R.id.menu_favorited:
				Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
				return true;
			case R.id.menu_about:
				
				//执行 SQL 语句
				//………………………………………………………………………………………………………………………………
				//………………………………………………………………………………………………………………………………
				DDHelper ddHelper = new DDHelper(this, null, 1);
				SQLiteDatabase db = ddHelper.getWritableDatabase();
				db.execSQL("update t_note set favorited='1'");
				Cursor cursor = db.rawQuery("SELECT * FROM t_note", null,null);
				StringBuffer str = new StringBuffer();
				while(cursor.moveToNext())
				{
					str.append(cursor.getString(cursor.getColumnIndex("favorited"))).append("|");
				}
				db.close();
				
				Toast.makeText(this, str.toString(), Toast.LENGTH_SHORT).show();
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

		if (keyword != null && keyword != "")
			strsql.append(" where title like '%" + keyword + "%' or content like '%" + keyword + "%'");

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
									cursor.getString(cursor.getColumnIndex("pub_date"))),
									cursor.getString(cursor.getColumnIndex("favorited")));
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
