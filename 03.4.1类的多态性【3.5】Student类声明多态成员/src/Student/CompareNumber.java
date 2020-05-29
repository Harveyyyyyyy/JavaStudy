package Student;

public class CompareNumber implements java.util.Comparator<Student>{
	public int compare(Student s1,Student s2) {
		return s1.number.compareTo(s2.number);
	}
	

}
