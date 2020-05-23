package Object;
import MyDate.MyDate;
import Person.Person;
import Student.Student;
import java.util.Arrays;
import CompareArray.CompareArray;
public class ObjectArrayTest extends ObjectArray{
	public static void main(String[] args) {
		Person per=new Person("李小明",new MyDate(1994,3,15));
		Person per2=new Person("周家辉",new MyDate(2001,7,2));
		Person[] pers= {per,new Student(per,"","计算机","",false)};
		Student[] stus= {new Student("张莉",new MyDate(1998,4,5),"","","","","信息管理","",false),
				         new Student("朱红",new MyDate(1996,3,12),"","","","","通信工程","",false)};
		Person[] value= {new Person("a周家辉",new MyDate(2001,7,2)),new Person("b周泽平",new MyDate(2001,10,2)),new Person("c张雨婷",new MyDate(2001,1,22))};
		Object[] objs=conact(pers, stus);
		stus[1].birthdate.set(2001, 10, 1);
		print(objs);
		Person[] keys= {new Person(per),new Student((Student)pers[1])};
		for(int i=0;i<keys.length;i++) {
			System.out.println("查找："+keys[i].toString()+",结果:");
			searchPrintAll(objs, keys[i]);
		}
		System.out.println("查找"+ObjectArray.search(objs, per2));
		System.out.println(ObjectArray.oldest(stus)+"这个人最大");
		Arrays.sort(value,new CompareBirthdate());
		for(int i=0;i<value.length;i++)
			System.out.println(value[i]);
		System.out.println();
		Arrays.sort(value,new CompareName());
		for(int i=0;i<value.length;i++)
			System.out.println(value[i]);
		int i=Arrays.binarySearch(value, new Person("c张雨婷",new MyDate(2001,10,2)),new CompareBirthdate());
		System.out.println(i);
		Student.howMany();
		}
	}


