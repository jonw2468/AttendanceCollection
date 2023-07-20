import java.io.*;
import java.util.*;

public class AttendanceLog {
	
	public AttendanceLog() { this.catalog = new ArrayList<Log>(); }

	protected ArrayList<Log> catalog;
	
	protected void load_log(String filename) {
		Scanner infile = null;
		try {
		infile = new Scanner(new FileReader(filename));
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found.");
			e.printStackTrace(); // Print any errors
			System.exit(0); // Exit the program
		}
		this.catalog = new ArrayList<Log>(); // Empty the ArrayList to avoid duplicates
		while (infile.hasNextLine()) {
			String data = infile.nextLine();
			StringTokenizer delimiter = new StringTokenizer(data, ", ");
			String lastName = delimiter.nextToken();
			String firstName = delimiter.nextToken();
			String time = delimiter.nextToken();
			String date = delimiter.nextToken();
			
			Log newLog = new Log(lastName, firstName, time, date);
			catalog.add(newLog);
		}
	}
	
	protected void print_collection() {
		System.out.println("** This is the entire Collection Data currently stored **");
		if (catalog.isEmpty()) {
			System.out.println("No attendance records found.");
		} else {
			ArrayList<Log> noNameRepeats = (ArrayList<Log>) catalog.clone();
			while (!(noNameRepeats.isEmpty())) {
				Log curr = noNameRepeats.get(0);
				String currLast = curr.getLastName();
				String currFirst = curr.getFirstName();
				String currTime = curr.getTime();
				String currDate = curr.getDate();
				System.out.print(currLast+", "+currFirst+" ['"+currTime+", "+currDate+"'");
				for (int i = 0; i < catalog.size(); i++) {
					Log l = catalog.get(i);
					if (l.getLastName().equals(currLast) && l.getFirstName().equals(currFirst)) {
						if (!(l.getTime().equals(currTime)) || !(l.getDate().equals(currDate))) {
							System.out.print(", '"+l.getTime()+", "+l.getDate()+"'");
						}
						noNameRepeats.remove(l);
					}
				}
				System.out.println("]");
			}
		}
	}
	
	protected void print_count() {
		System.out.println("Number of swipes read: " + catalog.size());
	}
	
	protected ArrayList<Log> getCatalog() { return catalog; }

	protected void setCatalog(ArrayList<Log> catalog) { this.catalog = catalog; }
	
	// Helper function
	protected int getSize() { return catalog.size(); }
}
