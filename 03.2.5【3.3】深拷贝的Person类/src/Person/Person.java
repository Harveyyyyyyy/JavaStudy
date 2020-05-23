package Person;
import MyDate.MyDate;
public class Person implements Comparable<Person>{
	public String name;
	public MyDate birthdate;
	public String gender,province,city;
	private static int count=0;
	public Person(String name,MyDate birthdate,String gender,String province,String city) {
		this.set(name,birthdate,gender,province,city);
		count++;
	}
	public Person(String name,MyDate birthdate) {
		this(name,birthdate,"","","");
	}
	public Person() {
		this("",new MyDate());
	}
	public Person(Person per) {
		this(per.name,new MyDate(per.birthdate),per.gender,per.province,per.city);
	}
	public void finalize() {
		System.out.println("释放对象（"+this.toString()+"）");
		Person.count--;
	}
	public static void howMany() {
		System.out.println(Person.count+"个Perso对象");
	}
	public void set(String name,MyDate birthdate,String gender,String province,String city) {
		this.name=name==null?"":name;
		this.birthdate=birthdate;
		this.gender=gender==null?"":gender;
		this.province=province==null?"":province;
		this.city=city==null?"":city;
	}
	public void set(String name,MyDate birthdate) {
		this.set(name, birthdate,"","","");
	}
	public String toString() {
		return this.name+","+(this.birthdate==null?"":birthdate.toString())+","+this.gender+","+this.province+","+this.city;
	}
	public boolean equals(Object obj) {
		if(this==obj)
			return true;
		if(obj instanceof Person) {
			Person p=(Person)obj;
			return this.name.equals(p.name)&&this.birthdate.equals(p.birthdate)&&this.gender.equals(gender)
					&&this.province.equals(p.province)&&this.city.equals(p.city);
		}
		return false;
	}
	public int getAge() {
		return MyDate.getThisYear()-this.birthdate.getYear();
	}
	public int older(Person p) {
		return p.birthdate.getYear()-this.birthdate.getYear();
	}
	public int compareTo(Person p) {
		return this.birthdate.compareTo(p.birthdate);
	}
	

}
