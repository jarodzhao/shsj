package com.jarodzhao.shsj.utils;
import android.content.*;
import com.jarodzhao.shsj.*;

public class UIHelper
{
	public static void returnHome(Context context)
	{
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }
}
