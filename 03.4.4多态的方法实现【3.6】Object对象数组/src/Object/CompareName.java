package Object;
import Person.Person;
public class CompareName implements java.util.Comparator<Person>{
	public int compare(Person p1,Person p2) {
		return p1.name.compareTo(p2.name);
	}
}
 class CompareBirthdate implements java.util.Comparator<Person>{
	public int compare(Person p1,Person p2) {
		return p1.birthdate.compareTo(p2.birthdate);
	}
}
