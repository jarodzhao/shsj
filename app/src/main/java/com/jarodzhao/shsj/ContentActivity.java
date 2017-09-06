package com.jarodzhao.shsj;
import android.app.*;
import android.os.*;
import com.jarodzhao.shsj.utils.*;
import android.widget.*;
import android.content.*;
import org.xml.sax.*;
import android.icu.text.*;
import android.view.*;
import android.view.inputmethod.*;
import android.util.*;
import java.util.*;

public class ContentActivity extends Activity
{
	TextView content_text_title;
	TextView content_text_content;
	CheckBox content_check_favorited;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_content);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		setTitle("");

		Intent intent = getIntent();
		Note note = (Note)intent.getSerializableExtra("note");

		content_text_title = (TextView) findViewById(R.id.content_text_title);
		content_text_content = (TextView) findViewById(R.id.content_text_content);

		TextView content_text_pub_date = (TextView) findViewById(R.id.content_text_pub_date);

		content_text_title.setText(note.getTitle());
		content_text_content.setText(note.getContent());

		SimpleDateFormat sdf = new SimpleDateFormat("M-dd HH:mm");
		content_text_pub_date.setText(sdf.format(note.getPubDate()));

		content_check_favorited = (CheckBox) findViewById(R.id.content_checkbox_favorited);

		//进入详情视图，是否收藏处理
		if (("1").equals(note.getFavorited()))
			content_check_favorited.setChecked(true);
		else
			content_check_favorited.setChecked(false);

//		setTitle(note.getFavorited());
//		catch (Exception e)
//		{
//			System.out.println(e.toString());
//			Toast.makeText(this, e.toString(),Toast.LENGTH_SHORT).show();
//		}
		
		//NoteService ns = new NoteService();
		//List<String> result = ns.getAllFavorited(this);
		//Log.d("jar",result.toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.content_menu, menu);
		return true;
	}



	@Override
    public boolean onOptionsItemSelected(MenuItem item)
	{
        switch (item.getItemId())
		{
			//导航键返回 home
            case android.R.id.home:
                UIHelper.returnHome(this);
                return true;
			case R.id.content_item_save:
				Toast.makeText(this, "==", Toast.LENGTH_SHORT).show();
				break;
        }
        return super.onOptionsItemSelected(item);
    }
}
