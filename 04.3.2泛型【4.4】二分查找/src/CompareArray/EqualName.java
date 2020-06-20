package CompareArray;

import Person.Person;

public class EqualName implements Equalable<Person>{
	public boolean equals(Person p1,Person p2) {
		return p1.name.equals(p2.name);
	}

}
