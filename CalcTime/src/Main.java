import java.util.*;

public class Main
{
	public static void main(String[] args)
	{
		int ic = 10000;
		
		long t1=0L;
		long t2=0L;
		
		StringBuffer strTemp = new StringBuffer();
		
		t1 = System.currentTimeMillis();
		
		for(int i=0;i<ic;i++)
		{
			strTemp.append(i).append(",");
		}
		
		t2 = System.currentTimeMillis();

		System.out.println("StringBuffer Run Times");
		
		System.out.println(t1);
		System.out.println(t2);
		
		//System.out.println(strTemp.toString());
		System.out.println();
		
		//////////////////////////////////////
		
		String strTemp2 = "";
		
		t1 = System.currentTimeMillis();
		
		for(int i=0;i<ic;i++)
		{
			strTemp2 += i + ",";
		}
		
		t2 = System.currentTimeMillis();
		
		System.out.println("String Run Times");
		
		System.out.println(t1);
		System.out.println(t2);
		
		//System.out.println(strTemp2);
		System.out.println();
		
		StringBuilder strTemp3 = new StringBuilder();
		
		t1 = System.currentTimeMillis();
		
		for(int i = 0;i<ic;i++)
		{
			strTemp3.append(i).append(",");
		}
		
		t2 = System.currentTimeMillis();
		
		System.out.println("StringBuilder Run Times");
		System.out.println(t1);
		System.out.println(t2);
		
//		System.out.println(strTemp3.toString());
	}
}
