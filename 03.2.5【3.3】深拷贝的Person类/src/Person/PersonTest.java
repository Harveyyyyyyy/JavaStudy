package Person;
import MyDate.MyDate;
public class PersonTest extends Person {
	public static void main(String[] args) {
		Person p1=new Person("李小明",new MyDate(1994,3,15));
		Person p2=new Person(p1);
		Person.howMany();
		System.out.println("p1:"+p1+";p2:"+p2+"\np1==p2?"+(p1==p2)+";p1.name==p2.name?"
				+(p1.name==p2.name)+",p1.birthdate==p2.birthdate?"+(p1.birthdate==p2.birthdate));
		p2.name="张"+p2.name.substring(1);
		MyDate date=p2.birthdate;
		date.set(date.getYear()+2,date.getMonth(),date.getDay());
		System.out.println("p1:"+p1+";p2"+p2);
		p1.finalize();
		Person.howMany();
		

	}

}
