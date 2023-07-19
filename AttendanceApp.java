import java.util.ArrayList; // Used for storing queries in the appropriate files

public class AttendanceApp {
	
	static boolean DEBUG = false; // Displays additional checks on each function in AttendanceApp if true
	
	// AttendanceApp()
	// Default constructor for an AttendanceApp Object
	public AttendanceApp(String filename_swipe, String filename_roster) {
		this.attendance = new AttendanceLog();
		attendance.load_log(filename_swipe);
		this.students = new StudentRoster();
		students.load_roster(filename_roster);
	}
	
	protected StudentRoster students;
	protected AttendanceLog attendance;
	
	// list_students_not_in_class()
	// Displays a list of Student Objects whose names are NOT found in the AttendanceLog Object
	protected void list_students_not_in_class() {
		if (DEBUG) System.out.println("Creating query for PRESENT students.");
		// Create an ArrayList of all Students who ARE present
		ArrayList<Student> query = new ArrayList<Student>();
		for (int i = 0; i < attendance.getSize(); i++) {
			Log currLog = attendance.catalog.get(i);
			Student currStudent = new Student(currLog.getLastName(), currLog.getFirstName());
			if (students.roster.contains(currStudent) && !(query.contains(currStudent))) { query.add(currStudent); }
		}
		if (DEBUG) {
			print_query_list(new ArrayList<Log>(), query);
			print_count(query.size());
		}
		
		// Use this extra ArrayList to find students who AREN'T present
		System.out.println("****** Students missing in class *************");
		for (int j = 0; j < students.getSize(); j++) {
			Student curr = students.roster.get(j);
			if (!(query.contains(curr))) {
				System.out.println(curr);
			}
		}
	}
	
	// list_all_times_checking_in_and_out()
	// Given student name strings, displays the corresponding Student's full attendance data as Log Objects
	protected void list_all_times_checking_in_and_out(String lastName, String firstName) {
		System.out.println("****** List all swipe in and out for "+lastName+", "+firstName+" *******");
		for (int i = 0; i < attendance.getSize(); i++) {
			Log currLog = attendance.catalog.get(i);
			if (currLog.getLastName().equals(lastName) && currLog.getFirstName().equals(firstName)) {
				System.out.println(currLog);
			}
		}
	}
	
	// list_all_times_checked_in
	// Displays the FIRST Log sharing a name with EACH Student in the roster
	protected void list_all_times_checked_in() {
		System.out.println("****** Check in times for all students who attended ***");
		ArrayList<Student> query = new ArrayList<Student>();
		for (int i = 0; i < attendance.getSize(); i++) {
			Log currLog = attendance.catalog.get(i);
			Student currStudent = new Student(currLog.getLastName(), currLog.getFirstName());
			if (!(query.contains(currStudent))) {
				System.out.println(currLog);
				query.add(currStudent);
			}
		}
		if (DEBUG) print_count(query.size());
	}
	
	// list_students_late_to_class
	// Given 2 different time Strings, displays all Student objects sharing names with a Log object whose time String is between the 2 parameters
	protected void list_students_late_to_class(String startTime, String endTime) {
		System.out.println("****** Students that arrived late ********************");
		for (int i = 0; i < attendance.getSize(); i++) {
			Log curr = attendance.catalog.get(i);
			String currTime = curr.getTime();
			if (currTime.compareTo(startTime) > 0 && currTime.compareTo(endTime) <= 0) {
				System.out.println(curr);
			}
		}
	}
	
	// get_first_student_to_enter
	// Given a date string, 
	protected void get_first_student_to_enter(String date) {
		System.out.println("**** First student to enter on "+date+" ****");
		for (int i = 0; i < attendance.getSize(); i++) {
			Log curr = attendance.catalog.get(i);
			if (curr.getDate().equals(date)) {
				System.out.println(curr.getLastName()+", "+curr.getFirstName());
				return;
			}
		}
		System.out.println("No student found.");
	}
	
	// print_attendance_data_for_student()
	// Given student name strings, displays the corresponding Student's full attendance data as a one-line list of time and date Strings
	protected void print_attendance_data_for_student(String lastName, String firstName) {
		System.out.println("********* Looking up Student Attendance Data ***********");
		Student s = new Student(lastName, firstName);
		if (students.roster.contains(s)) {
			System.out.print(lastName+", "+firstName+": ");
			for (int i = 0; i < attendance.getSize(); i++) {
				Log curr = attendance.catalog.get(i);
				if (curr.getLastName().equals(lastName) && curr.getFirstName().equals(firstName)) {
					System.out.print("'"+curr.getTime()+", "+curr.getDate()+"' ");
				}
			}
			System.out.println();
		} else {
			System.out.println("No student of this name in the attendance log");
		}
	}
	
	// is_present()
	// Given student name strings and a date string, returns whether the student was present on that date
	protected boolean is_present(String lastName, String firstName, String date) {
		System.out.println("**** Looking to see if Student was present on date****");
		for (int i = 0; i < attendance.getSize(); i++) {
			Log curr = attendance.catalog.get(i);
			if (curr.getLastName().equals(lastName) && curr.getFirstName().equals(firstName) && curr.getDate().equals(date)) {
				return true;
			}
		}
		return false;
	}
	
	// list_all_students_checked_in
	// Given a date string, displays a list of Student objects who have Log Objects on that date
	protected void list_all_students_checked_in(String date) {
		System.out.println("**** Students present on this date ****");
		ArrayList<Student> query = new ArrayList<Student>();
		for (int i = 0; i < attendance.getSize(); i++) {
			Log curr = attendance.catalog.get(i);
			Student currStudent = new Student(curr.getLastName(), curr.getFirstName());
			if (students.roster.contains(currStudent) && !(query.contains(currStudent)) && curr.getDate().equals(date)) {
				System.out.println(currStudent);
				query.add(currStudent);
			}
		}
		if (query.isEmpty()) System.out.println("No students found.");
		
		if (DEBUG) print_count(query.size());
	}
	
	// list_all_students_checked_in_before
	// Given date and time string, displays a list of Student objects who have Log Objects on that date and before that time
	protected void list_all_students_checked_in_before(String date, String time) {
		ArrayList<Student> query = new ArrayList<Student>();
		
		System.out.println("**** Those present on "+date+" & before a time assigned ****");
		for (int i = 0; i < attendance.getSize(); i++) {
			Log curr = attendance.catalog.get(i);
			Student currStudent = new Student(curr.getLastName(), curr.getFirstName());
			if (students.roster.contains(currStudent) && !(query.contains(currStudent)) && curr.getDate().equals(date) && curr.getTime().compareTo(time) < 0) {
				System.out.println(currStudent);
				query.add(currStudent);
			}
		}
		if (query.isEmpty()) System.out.println("No students found.");
		
		print_count(query.size()); // Show the number of students displayed by the function
	}
	
	// list_students_attendance_count
	// Given a number of classes attended, displays a list of 
	protected void list_students_attendance_count(int count) {
		ArrayList<Student> query = new ArrayList<Student>();
		
		System.out.println("**** Those who attended "+count+" classes ****");
		for (int i = 0; i < students.getSize(); i++) {
			int studentCount = 0;
			Student currStudent = students.roster.get(i);
			String currLastName = currStudent.getLastName();
			String currFirstName = currStudent.getFirstName();
			String currDate = "";
			for (int j = 0; j < attendance.getSize(); j++) {
				Log currLog = attendance.catalog.get(j);
				String logDate = currLog.getDate();
				if (currLog.getLastName().equals(currLastName) && currLog.getFirstName().equals(currFirstName) && !(logDate.equals(currDate))) {
					studentCount++;
					currDate = logDate;
				}
			}
			if (studentCount == count) {
				System.out.println(currStudent);
				query.add(currStudent);
			}
		}
		if (query.isEmpty()) { System.out.println("No students found."); }
		
		print_count(query.size()); // Show the number of students displayed by the function
	}
	
	// print_query_list
	// Given 2 ArrayLists of different types, displays 
	// In the program, Driver.main() fills both parameters, while other functions call this with at least one being empty
	protected void print_query_list(ArrayList<Log> logQuery, ArrayList<Student> rosterQuery) {
		if (!(logQuery.isEmpty())) {
			System.out.println("**** Printing query list elements (Log) ****");
			for (int i = 0; i < logQuery.size(); i++) System.out.println(logQuery.get(i));
		}
		if (!rosterQuery.isEmpty()) {
			System.out.println("**** Printing query list elements (Student) ****");
			for (int i = 0; i < rosterQuery.size(); i++) System.out.println(rosterQuery.get(i));
		}
		if (logQuery.isEmpty() && rosterQuery.isEmpty()) { System.out.println("This query is empty."); }
	}
	
	// print_count
	// Given an integer, displays that integer as the size of a query ArrayList
	protected void print_count(int queryCount) {
		System.out.println("There were "+queryCount+" records for this query");
	}
}
