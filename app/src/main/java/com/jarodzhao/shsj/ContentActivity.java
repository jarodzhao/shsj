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

public class ContentActivity extends Activity
{
	TextView main_text_title;
	TextView main_text_content;
	EditText main_edit_title;
	TextView main_edit_content;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_content);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		setTitle("");

		Intent intent = getIntent();
		Note note = (Note)intent.getSerializableExtra("note");

		main_text_title = (TextView) findViewById(R.id.main_text_title);
		main_edit_title = (EditText) findViewById(R.id.main_edit_title);

		main_text_content = (TextView) findViewById(R.id.main_text_content);
		main_edit_content = (EditText) findViewById(R.id.main_edit_content);

		TextView main_text_pub_date = (TextView) findViewById(R.id.main_text_pub_date);

		main_text_title.setText(note.getTitle());
		main_text_content.setText(note.getContent());

		main_edit_title.setText(note.getTitle());
		main_edit_content.setText(note.getContent());

		SimpleDateFormat sdf = new SimpleDateFormat("M-dd HH:mm");
		main_text_pub_date.setText(sdf.format(note.getPubDate()));

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

			case R.id.content_item_edit:
				//Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show();
				main_text_title.setVisibility(View.GONE);
				main_edit_title.setVisibility(View.VISIBLE);
				
				
				main_text_content.setVisibility(View.GONE);
				main_edit_content.setVisibility(View.VISIBLE);
				break;
        }
        return super.onOptionsItemSelected(item);
    }
}
