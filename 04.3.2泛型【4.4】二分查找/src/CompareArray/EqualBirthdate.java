package CompareArray;

import Person.Person;

public class EqualBirthdate implements Equalable<Person>{
	public boolean equals(Person p1,Person p2) {
		return p1.birthdate.equals(p2.birthdate);
	}

}
