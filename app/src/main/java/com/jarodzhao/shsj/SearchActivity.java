package com.jarodzhao.shsj;
import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.*;
import android.content.*;
import android.view.inputmethod.*;
import com.jarodzhao.shsj.utils.*;

public class SearchActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_search);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		final EditText editText_keyword = (EditText) findViewById(R.id.editText_keyword);

		editText_keyword.setOnKeyListener(new View.OnKeyListener(){

				@Override
				public boolean onKey(View view, int keyCode, KeyEvent event)
				{
					if (keyCode == KeyEvent.KEYCODE_ENTER && 
						event.getAction() == KeyEvent.ACTION_DOWN)
					{
						// 返回主界面关键字
						String keyword = editText_keyword.getText().toString();

						Bundle bundle = new Bundle();
						bundle.putString("keyword", keyword);
						Intent intent = new Intent();
						intent.putExtras(bundle);
						setResult(3, intent);
						finish();
					}
					return false;
				}


			});
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


}
