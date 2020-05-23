package Student;
import MyDate.MyDate;
import Person.Person;
public class StudentTest extends Student{
	public static void main(String[] args) {
		Person per=new Person("李小明",new MyDate(1994,3,15),"男","湖南省","长沙市");
		Student stu1=new Student(per,"计算机系","计算机科学与技术","202190338",true);
		Student stu2=new Student(stu1);
		stu2.set("张莉",new MyDate(1998,4,5),"女","湖北省","武汉市");
		stu2.set("经济管理系", "信息管理专业","2021903310",true);
		Student.howMany();
		System.out.println("per:"+per.toString()+"\nstu1:"+stu1.toString()+"\nstu2:"+stu2);
		stu2.finalize();
		Student.howMany();

	}

}
