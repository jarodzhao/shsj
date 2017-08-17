package com.jarodzhao.shsj;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.View.*;
import android.view.*;

import com.jarodzhao.shsj.utils.*;

import java.util.*;
import android.content.*;

public class AddActivity extends Activity implements OnClickListener
{
	Note note;

	Button button_back;
	Button button_save;

	EditText editText_title;
	EditText editText_content;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_add);

		button_back = (Button) findViewById(R.id.button_back);
		button_save = (Button) findViewById(R.id.button_save);
		button_back.setOnClickListener(this);
		button_save.setOnClickListener(this);

		editText_title = (EditText) findViewById(R.id.editText_title);
		editText_content = (EditText) findViewById(R.id.editText_content);

		getActionBar().setDisplayHomeAsUpEnabled(true);
	}


	@Override
	public void onClick(View view)
	{
		switch (view.getId())
		{
			case R.id.button_back:
//				Intent Intent = new Intent();
				setResult(1);
				finish();
				break;
			case R.id.button_save:
				if (editText_title.getText().toString().length() < 1 || editText_content.getText().toString().length() < 1)
				{
					Toast.makeText(this, "请输入要保存的内容", Toast.LENGTH_SHORT).show();
					return;
				}
				Note note = save();
				Bundle bundle = new Bundle();
				bundle.putSerializable("new", note);
				Intent intent = new Intent();
				intent.putExtras(bundle);
				setResult(2, intent);
				finish();
				break;
		}
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
        }
        return super.onOptionsItemSelected(item);
    }
	

	Note save()
	{
		note = new Note();
		note.setTitle(editText_title.getText().toString().trim());
		note.setContent(editText_content.getText().toString());
		note.setPubDate(new Date());

		NoteService ns = new NoteService();
		ns.saveNote(this, note);

		return note;
	}
}
