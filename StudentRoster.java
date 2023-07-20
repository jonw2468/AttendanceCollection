import java.io.*;
import java.util.*;

public class StudentRoster {
	
	public StudentRoster() { this.roster = new ArrayList<Student>(); }
	
	protected ArrayList<Student> roster;
	
	protected void load_roster(String filename) {
		Scanner infile = null;
		try {
		infile = new Scanner(new FileReader(filename));
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found.");
			e.printStackTrace(); // Print any errors
			System.exit(0); // Exit the program
		}
		this.roster = new ArrayList<Student>(); // Empty the ArrayList to avoid duplicates
		while (infile.hasNextLine()) {
			String data = infile.nextLine();
			StringTokenizer delimiter = new StringTokenizer(data, ", ");
			String lastName = delimiter.nextToken();
			String firstName = delimiter.nextToken();
			
			Student newStudent = new Student(lastName, firstName);
			roster.add(newStudent);
		}
	}
	
	protected void print_collection() {
		System.out.println("**** Those students on roster ****");
		if (roster.isEmpty()) {
			System.out.println("No students found.");
		} else {
			for (int i = 0; i < roster.size(); i++) { System.out.println(roster.get(i)); }
		}
	}
	
	protected void print_count() {
		System.out.println("Number of students read: " + roster.size());
	}
	
	protected ArrayList<Student> getRoster() { return roster; }

	protected void setRoster(ArrayList<Student> roster) { this.roster = roster; }

	// Helper function
	protected int getSize() { return roster.size(); }
}
