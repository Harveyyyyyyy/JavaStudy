package Object;
import Person.Person;
import Student.Student;
public class ObjectArray {
	public static void print(Object[] objs) {
		if(objs!=null)
			for(int i=0;i<objs.length;i++)
				System.out.println(objs[i]==null?"nill":objs[i].toString());
	}
	public static Object[] conact(Object[] objs1,Object[] objs2) {
		if(objs1==null)
			return objs2;
		if(objs2==null)
			return objs1;
		Object[] result=new Object[objs1.length+objs2.length];
		int i=0,j=0;
		for(j=0;j<objs1.length;j++)
			result[i++]=objs1[j];
		for(j=0;j<objs2.length;j++)
			result[i++]=objs2[j];
		return result;
	}
	public static void searchPrintAll(Object[] objs,Object key) {
		if(objs!=null&&key!=null)
			for(int i=0;i<objs.length;i++)
				if(objs[i]!=null&&key.equals(objs[i]))
					System.out.println(objs[i].toString());
	}
	//顺序查找objs对象数组中首次出现的与key相等元素，若查找成功返回元素，否则返回null
	public static Object search(Object[] objs, Object key) {
		if(objs!=null&&key!=null)
			for(int i=0;i<objs.length;i++)
				if(objs[i]!=null&&key.equals(objs[i]))
					return objs[i];
		return null;
	}
	//返回年龄最大的对象
	public static Person oldest(Person[] pers) {//compareTo 最小值
		int max=pers[0].getAge();
		int index=0;
		for(int i=1;i<pers.length;i++) 
			if(pers[i].getAge()>max) {
				max=pers[i].getAge();	
				index=i;
			}
			return pers[index];
					
	}

}
