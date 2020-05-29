package Student;
import Person.Person;
import MyDate.MyDate;
public class Student extends Person {
	public String department,speciality,number;
	public boolean member;
	private static int count=0;
	public Student(String name,MyDate birthdate,String gender,String province,String city
			,String department,String specility,String number,boolean member) {
		super(name,birthdate,gender,province,city);
		this.set(department, specility,number,member);
		count++;
	}
	public Student() {
		super();
		this.set("","","",false);
		Student.count++;
	}
	public Student(Person person,String department,String specility,String number,boolean member) {
		super(person);
		this.set(department, specility,number,member);
		Student.count++;
	}
	public Student(Student stu) {
		this(stu,stu.department,stu.speciality,stu.number,stu.member);
	}
	public void finalize() {
		super.finalize();
		Student.count--;
	}
	public static void howMany() {
		Person.howMany();
		System.out.println(Student.count+"个Student对象");
	}
	public void set(String department,String specility,String number,boolean member) {
		this.department=department==null?"":department;
		this.speciality=specility==null?"":specility;
		this.number=number==null?"":number;
		this.member=member;
	}
	public String toString() {
		return super.toString()+","+this.department+","+this.speciality+","+this.number+(member?",团员":"");
	}
	public boolean equals(Object obj) {
		if(this==obj)
			return true;
		if(obj instanceof Student) {
			Student stu=(Student)obj;
			return super.equals(stu)
					&&department.equals(stu.department)&&speciality.equals(stu.speciality)
					&&this.number.equals(stu.number)&&this.member==stu.member;
		}
		return false;
	}
	

}
