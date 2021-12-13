/*
	Main class, IdException class, main method
*/

import java.io.*;
import java.util.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

public class Main {
	public static void main(String[] args) {
		/* FIELDS */
		String name, id = "", dept, rank, status, choice, searchID, save;
		double gpa = 0.0;
		int hours = -1, arrCount = 0;
		Person arr[] = new Person[100];
		
		// instantiate scanner object 
		Scanner in = new Scanner(System.in);
		
		// display header
		System.out.println("\t\t\t\tWelcome to my Personal Management Program");
		System.out.println("Choose one of the options:");

		// enter infinite loop
		while (true) {
			// flag and run variables
			int flag = 0;
			boolean done = false;
			// prompt user with a list of options and scan in choice
			System.out.println("\n1-Enter the information of a faculty\n"
					+ "2-Enter the information of a student\n"
					+ "3-Print tuition invoice for a student\n"
					+ "4-Print faculty information\n"
					+ "5-Enter the information of a staff member\n"
					+ "6-Print the information of a staff member\n"
					+ "7-Exit Program");
			System.out.print("\n\tEnter your selection: ");
			choice = in.nextLine();
			// enter switch of choices
			switch (choice) {
			// enter info of a facutly case
			case "1":
				System.out.println("\nEnter the faculty info:");
				System.out.print("\n\tName of the faculty: ");
				name = in.nextLine();
				// enter loop to check ID format and catch thrown IdException
				while(!done) {
					System.out.print("\tID: ");
					id = in.nextLine();
					try {
						checkId(id);
						done = true;
					} catch(IdException e) {
						System.out.println(e.getMessage());
					}
				}
				System.out.print("\tRank: ");
				rank = in.nextLine();
				// enter loop to catch invalid rank input
				while (!rank.toLowerCase().equalsIgnoreCase("professor") 
					&& !rank.toLowerCase().equalsIgnoreCase("adjunct")) {
					System.out.println("\t\t\"" + rank + "\" is invalid");
					System.out.print("\tRank: ");
					rank = in.nextLine();
				}
				System.out.print("\tDepartment: ");
				dept = in.nextLine();
				// enter loop to catch invalid department input
				while (
						!dept.toLowerCase().equalsIgnoreCase("engineering") 
						&& !dept.toLowerCase().equalsIgnoreCase("mathematics") 
						&& !dept.toLowerCase().equalsIgnoreCase("sciences")
			    ) {
					System.out.println("\t\t\"" + dept + "\" is invalid");
					System.out.print("\tDepartment: ");
					dept = in.nextLine();
				}
				// create and store faculty object in array of persons
				arr[arrCount++] = new Faculty(name, id, dept, rank);
				break;
				
			// enter info of a student case
			case "2":
				System.out.println("\nEnter the student info: ");
				System.out.print("\n\tName of Student: ");
				name = in.nextLine();
				// handle invalid id format
				while(!done) {
					System.out.print("\tID: ");
					id = in.nextLine();
					try {
						checkId(id);
						done = true;
					} catch (IdException e) {
						System.out.println(e.getMessage());
					}
				}
				// handle InputMismatchException to avoid crash
				do {
				    try {
				        System.out.print("\tGpa: ");
				        gpa = in.nextDouble();
				    } catch (InputMismatchException e) {
				        System.out.print("\n\tInvalid entry - Please try again.\n\n");
				    }
				    // clear input buffer
				    in.nextLine();
				} while (gpa <= 0);
				// handle InputMismatchException to avoid crash
				do {
				    try {
				        System.out.print("\tCredit hours: ");
				        hours = in.nextInt();
				    } catch (InputMismatchException e) {
				        System.out.print("\n\tInvalid entry - Please try again.\n\n");
				    }
				    // clear input buffer
				    in.nextLine();
				} while (hours < 0);
				// create and store student object in array of persons
				arr[arrCount++] = new Student(name, id, gpa, hours);
				break;
				
			// print tuition invoice of student
			case "3": 
				System.out.print("\n\tEnter the Student's ID: ");
				searchID = in.nextLine();
				// search for and print student
				for(int i = 0; i < arrCount; i++) {
					if (arr[i].getId().equalsIgnoreCase(searchID) && arr[i] instanceof Student) {
						System.out.println("\nHere is the tuition invoice for " + arr[i].getName() + ":");
						arr[i].print();
						flag = 1;
					}
				}
				// no matching object found
				if (flag == 0) System.out.println("\n\tNo student matched!");
				break;
			// print faculty info
			case "4":
				System.out.print("\n\tEnter the Faculty's ID: ");
				searchID = in.nextLine();
				// search for and print faculty
				for(int i = 0; i < arrCount; i++) {
					if (arr[i].getId().equalsIgnoreCase(searchID) && arr[i] instanceof Faculty) {
						arr[i].print();
						flag = 1;
					}
				}
				// no matching object found
				if (flag == 0) 
					System.out.println("\n\tNo Faculty member matched!");
				break;
				
			// enter info of a staff
			case "5": 
				System.out.println("\nEnter the Staff's info: ");
				System.out.print("\n\tName of the Staff member: ");
				name = in.nextLine();
				// enter loop to check ID format and catch thrown IdException
				while(!done) {
					System.out.print("\tID: ");
					id = in.nextLine();
					try {
						checkId(id);
						done = true;
					} catch(IdException e) {
						System.out.println(e.getMessage());
					}
				}
				System.out.print("\tDepartment: ");
				dept = in.nextLine();
				// enter loop to catch invalid department input
				while (
						!dept.toLowerCase().equalsIgnoreCase("engineering") 
						&& !dept.toLowerCase().equalsIgnoreCase("mathematics") 
						&& !dept.toLowerCase().equalsIgnoreCase("sciences")
				) {
					System.out.println("\t\t\"" + dept + "\" is invalid");
					System.out.print("\tDepartment: ");
					dept = in.nextLine();
				}
				System.out.print("\tStatus, Enter P for Part Time,or Enter F for Full Time: ");
				status = in.nextLine();
				// enter loop to catch invalid status input
				while (!status.toLowerCase().equalsIgnoreCase("f") && !status.toLowerCase().equalsIgnoreCase("p")) {
					System.out.println("\n\tInvalid entry - Please try again.\n");
					System.out.print("\tStatus, Enter P for Part Time,or Enter F for Full Time: ");
					status = in.nextLine();
				}
				// create and store staff object in array of persons
				arr[arrCount++] = new Staff(name, id, dept, status);
				break;
				
			// print staff info
			case "6": 
				System.out.print("\n\tEnter the Staff's ID: ");
				searchID = in.nextLine();
				// search for and print staff 
				for(int i = 0; i < arrCount; i++) {
					if (arr[i].getId().equalsIgnoreCase(searchID) && arr[i] instanceof Staff) {
						arr[i].print();
						flag = 1;
					}
				}
				// no matching object found
				if (flag == 0) System.out.println("\n\tNo Staff member matched!");
				break;
			// quit
			case "7":
				System.out.print("\nWould you like to create the report? (Y/N): ");
				save = in.next();
				if (save.toLowerCase().equalsIgnoreCase("y")) {
					try {
						createReport(arr);
					} catch (Exception e) {
						System.out.println("\n\tError opening file!");
					}
				}
				System.out.println("Goodbye!");
				System.exit(0);
				break;
			default: // catch invalid input
				System.out.println("\n\tInvalid entry - Please try again");
				break;
				
			} // switch()
			
		} // while()
		
	} // main()

	// method to check the ID format. Throws IdException for invalid format
	private static void checkId(String id) throws IdException {
		// check if ID is correct length and parse each character
		if (id.length() != 6) throw new IdException();
		else if (!Character.isLetter(id.charAt(0))) throw new IdException();
		else if (!Character.isLetter(id.charAt(1))) throw new IdException();
		else if (!Character.isDigit(id.charAt(2))) throw new IdException();
		else if (!Character.isDigit(id.charAt(3))) throw new IdException();
		else if (!Character.isDigit(id.charAt(4))) throw new IdException();
		else if (!Character.isDigit(id.charAt(5))) throw new IdException();
	} // checkId()
	
	// method to create report of all persons. Writes output to "report.txt"
	private static void createReport(Person[] arr) throws Exception{
		int facultyCount = 0, staffCount = 0, studentCount = 0;
		// instantiate File and PrintStream objects
		File facultyFile = new File("faculty.txt");
		File staffFile = new File("staff.txt");
		File studentFile = new File("student.txt");
		File reportFile = new File("report.txt");
		PrintStream facultyOut = new PrintStream(facultyFile);
		PrintStream staffOut = new PrintStream(staffFile);
		PrintStream studentOut = new PrintStream(studentFile);
		PrintStream reportOut = new PrintStream(reportFile);
		// write persons info to designated output files based off instance
		for (Person p : arr) {
			if (p != null) {
				if (p instanceof Faculty) {
					facultyCount++;
					facultyOut.print(facultyCount + ". ");
					facultyOut.println(p);
				}
				else if (p instanceof Staff) {
					staffCount++;
					staffOut.print(staffCount + ". ");
					staffOut.println(p);
				}
				else {
					studentCount++;
					studentOut.print(studentCount + ". ");
					studentOut.println(p);
				}
			} 
		}
		// close PrintStream objects
		facultyOut.close();
		staffOut.close();
		studentOut.close();
		// instantiate scanner objects to read info from accessory files
		Scanner facultyScan = new Scanner(facultyFile);
		Scanner staffScan = new Scanner(staffFile);
		Scanner studentScan = new Scanner(studentFile);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDateTime now = LocalDateTime.now();
		// read from accessory files and write final report to "report.txt"
		reportOut.println("Report created on " + dtf.format(now) + "\n***********************\n\n");
		reportOut.println("Faculty Members\n-------------------------");
		// check if no faculty. Write output accordingly
		if (!facultyScan.hasNextLine())
			reportOut.println("\tNone");
		while (facultyScan.hasNextLine()) 
			reportOut.println("\t" + facultyScan.nextLine());
		reportOut.println("Staff Members\n-------------------------");
		if (!staffScan.hasNextLine())
			reportOut.println("\tNone");
		while (staffScan.hasNextLine()) 
			reportOut.println("\t" + staffScan.nextLine());
		reportOut.println("Students\n-------------------------");
		if (!studentScan.hasNextLine()) 
			reportOut.println("\tNone");
		while (studentScan.hasNextLine()) 
			reportOut.println("\t" + studentScan.nextLine());
		reportOut.close();
		System.out.println("Report created and saved on your hard drive!");
		
	} // createReport()

}

//_____________________________
class IdException extends Exception {
	// method to print personalized error message to console
	public String getMessage() {
		return "\n\tInvalid ID format. Must be LetterLetterDigitDigitDigitDigit\n";
	}
}