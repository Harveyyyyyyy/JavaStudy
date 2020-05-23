package Calendar;
import java.util.*;
import java.text.SimpleDateFormat;
public class MonthlyCalendar {
	public static void main(String[] args) {
		String datestr="yyyy��MM��dd�� E HHʱmm��ss��";
		SimpleDateFormat datef=new SimpleDateFormat(datestr);
		System.out.print("������"+datef.format(new Date()));
		long now=System.currentTimeMillis();
		datef=new SimpleDateFormat("yyyy��MM��dd�� E ");
		System.out.println(",����ʱ"+datef.format(new Date(now+24*60*60*1000)));
		MonthlyCalendar.print(new GregorianCalendar());
	}
	public static void print(Calendar calendar) {
		int year=calendar.get(Calendar.YEAR);
		int month=calendar.get(Calendar.MONTH)+1;
		calendar.set(year, month-1,1);
		int week=calendar.get(Calendar.DAY_OF_WEEK)-1;
		System.out.println(year+"��"+month+"�µ�����\n     ��    һ    ��    ��    ��    ��    ��");
		if(week>0)
			System.out.print(String.format("%"+4*week+"c", ' '));
		int days=MyDate.MyDate.daysOfMonth(year, month);
		for(int i=1;i<=days;i++) {
			System.out.print(String.format("%4d", i));
			if((week+i)%7==0)
				System.out.println();
		}
		System.out.println();
	}

}
