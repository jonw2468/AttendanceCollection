import java.util.Objects;

public class Log {
	public Log() {
	}
	
	public Log(String lastName, String firstName, String time, String date) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.time = time;
		this.date = date;
	}

	private String firstName;
	private String lastName;
	private String time;
	private String date;
	
	protected String getFirstName() { return firstName; }
	
	protected void setFirstName(String firstName) { this.firstName = firstName; }
	
	protected String getLastName() { return lastName; }
	
	protected void setLastName(String lastName) { this.lastName = lastName; }
	
	protected String getTime() { return time; }
	
	protected void setTime(String time) { this.time = time; }
	
	protected String getDate() { return date; }
	
	protected void setDate(String date) { this.date = date; }

	@Override
	public String toString() {
		return lastName + ", " + firstName + ", " + time + ", " + date;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Log other = (Log) obj;
		return Objects.equals(date, other.date) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(time, other.time);
	}
}
