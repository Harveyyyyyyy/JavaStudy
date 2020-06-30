package MyDate;

import DateFormatException.DateFormatException;

public class MyDate implements Comparable<MyDate>,java.io.Serializable{
	private int year,month,day;
	private static int thisYear;
	static {
		thisYear=2020;
	}
	public MyDate(int year,int month,int day) throws DateFormatException{
		this.set(year,month,day);
	}
	public MyDate() {
		this(1970,1,1);
	}
	public MyDate(String datestr) throws NumberFormatException,DateFormatException{
		if(datestr.isEmpty())
			throw new DateFormatException("空串，日期错误");
		int i=datestr.indexOf('年'),j=datestr.indexOf('月',i),k=datestr.indexOf('日',j);
		int year=Integer.parseInt(datestr.substring(0, i));
		int month=Integer.parseInt(datestr.substring(i+1, j));
		int day=Integer.parseInt(datestr.substring(j+1, k));
		this.set(year,month,day);
	}
	public MyDate(MyDate date) {
		this.set(date);
	}
	public void set(int year,int month,int day)throws DateFormatException{
		if(year<=-2000||year>2500)
			throw new DateFormatException(year+"，年份不合适，有效年份为-2000~2500。");
		if(month<1||month>12)
			throw new DateFormatException(month+"月，月份错误");
		if(day<1||day>MyDate.daysOfMonth(year, month))
			throw new DateFormatException(year+"年"+month+"月"+day+"日，日期错误");
		this.year=year;
		this.month=month;
		this.day=day;
	}
	public void set(MyDate date) {
		this.set(date.year,date.month,date.day);
	}
	

	public int getYear() {
		return year;
	}
	public int getMonth() {
		return month;
	}
	public int getDay() {
		return day;
	}
	public  char getWeek() {
		int y=this.year-1904,count=0,total;
		String Week="日一二三四五六";
		count=y/4+1;
		total=(this.year-1900)*365+count+allMonthDay(this.month-1)+day;
		int week=total%7;
	    return Week.charAt(week);
	}
	public static int allMonthDay(int month) {
		switch(month){
		case 0:return 0;
		case 1:return 31;
		case 2:return 31+28;
		case 3:return 31+28+31;
		case 4:return 31+28+31+30;
		case 5:return 31+28+31+30+31;
		case 6:return 31+28+31+30+31+30;
		case 7:return 31+28+31+30+31+30+31;
		case 8:return 31+28+31+30+31+30+31+31;
		case 9:return 31+28+31+30+31+30+31+31+30;
		case 10:return 31+28+31+30+31+30+31+31+30+31;
		case 11:return 31+28+31+30+31+30+31+31+30+31+30;
		case 12:return 31+28+31+30+31+30+31+31+30+31+30+31;
		}
		return 0;
		
		
		
		
	}
	public String toString() {
		return year+"年"+String.format("%02d", month)+"月"+String.format("%02d", day)+"日";
	}
	public static int getThisYear() {
		return thisYear;
	}
	public static boolean isLeapYear(int year) {
		return year%400==0||year%100!=0&&year%4==0;
	}
	public boolean isLeapYear() {
		return isLeapYear(this.year);
	}
	public boolean equals(MyDate date) {
		return this==date||date!=null&&this.year==date.year&&this.month==date.month&&this.day==date.day;
	}
	public static int daysOfMonth(int year,int month) {
		switch(month) {
		case 1:case 3:case 5: case 7: case 8: case 10: case 12:return 31;
		case 4: case 6:case 9: case 11: return 30;
		case 2:return MyDate.isLeapYear(year)?29:28;
		default: return 0;
		}
	}
	public int daysOfMonth() {
		return daysOfMonth(this.year,this.month);
	}
	public void tomorrow() {
		this.day=this.day%this.daysOfMonth()+1;
		if(this.day==1) {
			this.month=this.month%12+1;
			if(this.month==1)
				this.year++;
		}
	}
	public MyDate daysAfter(int n)   {
//		MyDate dn=new MyDate(this);
//		if((this.day+n)>this.daysOfMonth()) {
//			dn.month=(this.month%12+(this.day+n)/this.daysOfMonth())%12;
//			if((this.month%12+(this.day+n)/this.daysOfMonth())>12)
//				dn.year++;
//		}
//		dn.day=(this.day%this.daysOfMonth()+n%31);
//		return dn;
		MyDate date=new MyDate(this);
		while(n>0)
			if(date.day+n<=date.daysOfMonth()) {
				date.day+=n;
				n=0;
			}else {
				n-=date.daysOfMonth()-date.day;
				date.day=0;
				date.month++;
				if(date.month>12) {
					date.month=1;
					date.year++;
					while(n>365) {
						n-=date.isLeapYear()?366:365;
						date.year++;
					}
				}
			}
		return date;
		}
		
	public MyDate yesterday() {
		MyDate date=new MyDate(this);
		date.day--;
		if(date.day==0) {
			date.month=(date.month-2+12)%12+1;
			if(date.month==12)
				date.year--;
			date.day=daysOfMonth(date.year,date.month);
		}
		return date;
	}
	public int compareTo(MyDate date) {
		if(this.year==date.year&&this.month==date.month&&this.day==date.day)
			return 0;
		return (this.year>date.year||this.year==date.year&&this.month>date.month||
				this.year==date.year&&this.month==date.month&&this.day>date.day)?1:-1;
	}
//}
//class MyDate_ex{
	public static void main(String[] args) {
		System.out.println("今年是"+MyDate.getThisYear()+"，闰年？"+MyDate.isLeapYear(MyDate.getThisYear()));
		MyDate d1=new MyDate(2020,5,9);
		System.out.println(d1.getYear()+"年，闰年?"+d1.isLeapYear());
		MyDate d2=new MyDate(d1);
		System.out.println("d1:"+d1+",d2:"+d2+",d1==d2?"+(d1==d2)+",equals(d2)?"+d1.equals(d2));
		System.out.print(d1+"明天是:");
		d1.tomorrow();
		System.out.println(d1+"\n"+d2+"的昨天是"+(d2=d2.yesterday()));
		System.out.println(d1+"这天是星期"+d1.getWeek());
		System.out.println(d2+"这天是"+d2.getWeek());
		MyDate d3=new MyDate(2020,5,9);
		System.out.println(d3+"这天是"+d3.getWeek());
		System.out.println(d3+"这天的后31天为"+d3.daysAfter(31));
//		new MyDate("2019年2月29日");
		

	}
}
