import java.util.Objects;

public class Student {
	public Student(String lastName, String firstName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	private String firstName;
	private String lastName;
	
	protected String getFirstName() { return firstName; }
	
	protected void setFirstName(String firstName) { this.firstName = firstName; }
	
	protected String getLastName() { return lastName; }
	
	protected void setLastName(String lastName) { this.lastName = lastName; }
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName);
	}

	@Override
	public String toString() { return lastName + ", " + firstName; }
}
