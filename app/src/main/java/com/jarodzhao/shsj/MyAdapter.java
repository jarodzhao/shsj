package com.jarodzhao.shsj;
import android.widget.*;
import android.view.*;
import android.content.*;
import com.jarodzhao.shsj.utils.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class MyAdapter extends BaseAdapter
{
	private Context context;
	private ArrayList<Note> notes;

	public MyAdapter(Context context, List<Note> notes)
	{
		this.context = context;
		this.notes = (ArrayList<Note>) notes;
	}

	@Override
	public int getCount()
	{
		// TODO: Implement this method
		return notes.size();
	}

	@Override
	public Object getItem(int arg0)
	{
		// TODO: Implement this method
		return notes.get(arg0);
	}

	@Override
	public long getItemId(int position)
	{
		// TODO: Implement this method
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View view;
		ViewHolder viewHolder;

		Note note = (Note) getItem(position);

		if (convertView == null)
		{
			view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.layout_list_style, parent, false);

			viewHolder = new ViewHolder();
			viewHolder.textView_title = (TextView)view.findViewById(R.id.text_title);
			viewHolder.textView_contend = (TextView)view.findViewById(R.id.text_content);
			viewHolder.textView_date = (TextView)view.findViewById(R.id.text_date);
			viewHolder.textview_type = (TextView)view.findViewById(R.id.text_type);
			//viewHolder.textview_favoried = (TextView) view.findViewById(R.id.text_favoried);
			viewHolder.iv_favorited = (ImageView) view.findViewById(R.id.iv_favorited);

			view.setTag(viewHolder);
		}
		else
		{
			view = convertView;
			viewHolder = (ViewHolder)view.getTag();
		}

		viewHolder.textView_title.setText(note.getTitle());
		viewHolder.textView_contend.setText(note.getContent());
		viewHolder.textView_date.setText(
			new SimpleDateFormat("M-dd HH:mm").format(note.getPubDate()));
		viewHolder.textview_type.setText("分类");
		//viewHolder.textview_favoried.setText(note.getFavorited());
		try{
		if("1".equals(note.getFavorited()))
			viewHolder.iv_favorited.setVisibility(View.VISIBLE);
		else
			viewHolder.iv_favorited.setVisibility(View.GONE);
		}catch(Exception e){
			System.out.println(e.toString());
		}
		return view;
	}

	class ViewHolder
	{
		TextView textView_title;
		TextView textView_contend;
		TextView textView_date;
		TextView textview_type;
		//TextView textview_favoried;
		ImageView iv_favorited;

	}
}

