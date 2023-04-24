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
        String fileName = "lec.txt";
        String name, id = "", officeLocation, rank, choice, searchID, crns, list[];
        ArrayList<Person> people = new ArrayList<Person>();
        ArrayList<Lecture> lectures = new ArrayList<Lecture>();
        ArrayList<Lab> labs = new ArrayList<Lab>();

        // read file contents
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean isLab = false;
            boolean hasLab = false;
            String lectureCrn = "";

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] tokens = line.split(",");

                hasLab = tokens.length > 6 && "YES".equalsIgnoreCase(tokens[6]);
                isLab = tokens.length < 3;

                if (isLab) {
                    labs.add(new Lab(lectureCrn, tokens[0], tokens[1]));
                } else {
                    if (tokens.length < 6 && !isLab) {
                        lectures.add(new Lecture(tokens[0], tokens[1], tokens.length > 2 ? tokens[2] : null,
                                tokens.length > 3 ? tokens[3] : null, tokens.length > 4 ? tokens[4] : null, null,
                                false));
                    } else {
                        hasLab = tokens.length > 6 && "YES".equalsIgnoreCase(tokens[6]);
                        lectures.add(
                                new Lecture(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], hasLab));
                        if (hasLab) {
                            lectureCrn = tokens[0];
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

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
            System.out.println("\n1-Add a new Faculty to the schedule\n"
                    + "2-Enroll a Student to a Lecture\n"
                    + "3-Print the schedule of a Faculty\n"
                    + "4-Print the schedule of an TA\n"
                    + "5-Print the schedule of a Student\n"
                    + "6-Delete a Lecture\n"
                    + "7-Exit");
            System.out.print("\n\tEnter your selection: ");
            choice = in.nextLine();

            // enter switch of choices
            switch (choice) {
                // Enter faculty info
                case "1":
                    // enter loop to check ID format and catch thrown IdException
                    while (!done) {
                        System.out.print("\n\tEnter UCF id: ");
                        id = in.nextLine();
                        try {
                            checkId(id);
                            done = true;
                        } catch (IdException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    System.out.print("\tEnter name: ");
                    name = in.nextLine();

                    System.out.print("\tEnter rank: ");
                    rank = in.nextLine();
                    // enter loop to catch invalid rank input
                    while (!rank.toLowerCase().equalsIgnoreCase("professor")
                            && !rank.toLowerCase().equalsIgnoreCase("associate professor")
                            && !rank.toLowerCase().equalsIgnoreCase("assistant professor")
                            && !rank.toLowerCase().equalsIgnoreCase("adjunct")) {
                        System.out.println("\t\t\"" + rank + "\" is invalid");
                        System.out.print("\tEnter rank: ");
                        rank = in.nextLine();
                    }

                    System.out.print("\tEnter office location: ");
                    officeLocation = in.nextLine();

                    System.out.print("\tEnter how many lectures: ");
                    int numLectures = Integer.parseInt(in.nextLine());

                    Faculty f = new Faculty(name, id, officeLocation, numLectures, rank);

                    System.out.print("\tEnter the crns of the lectures to assign to this faculty: ");
                    crns = in.nextLine();

                    list = crns.split(" ", numLectures);
                    for (String s : list) {
                        f.addCourse(Integer.parseInt(s));
                    }

                    // create and store faculty object in array of persons
                    people.add(f);

                    System.out.println("\nFaculty added!");
                    break;

                // enter info of a student case
                case "2":
                    // enter loop to check ID format and catch thrown IdException
                    while (!done) {
                        System.out.print("\n\tEnter UCF id: ");
                        id = in.nextLine();
                        try {
                            checkId(id);
                            done = true;
                        } catch (IdException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    System.out.print("\tEnter name: ");
                    name = in.nextLine();

                    Student s = new Student(name, id);

                    System.out.print("\tWhich lecture to enroll [" + name + "] in: ");
                    String crn = in.nextLine();

                    for (Lecture lec : lectures) {
                        if (lec.crn.equalsIgnoreCase(crn)) {
                            s.addCourse(lec);
                        }
                    }

                    ArrayList<Lab> courseLabs = new ArrayList<>();

                    for(Lecture lecture : lectures) {
                        if (lecture.hasLab && lecture.crn.equalsIgnoreCase(crn)) {
                            System.out.println("\n["+ lecture.crn + "/" + lecture.title + "] has these labs:");
                            for (Lab lab : labs) {
                                if (lab.lectureCrn.equalsIgnoreCase(lecture.crn)) {
                                    System.out.println("\t" + lab);
                                    courseLabs.add(lab);
                                }
                            }
                        }
                    }

                    int randomIndex = (int) (Math.random() * courseLabs.size());
                    Lab randLab = courseLabs.get(randomIndex);
                    System.out.println("\n[" + name + "] is added to lab: " + randLab);

                    s.addLab(randLab);

                    // create and store faculty object in array of persons
                    people.add(s);

                    System.out.println("\nStudent enrolled!");
                    break;

                // print schedule of Faculty
                case "3":
                    break;

                // print schedule of TA
                case "4":
                    break;

                // print schedule of student
                case "5":
                    System.out.print("\n\tEnter the UCF id: ");
                    searchID = in.nextLine();
                    for (Person person : people) {
                        if (person.getId().equalsIgnoreCase(searchID) && person instanceof Student) {
                            System.out.println("Record Found: ");
                            System.out.println(person);
                        }
                    }
                    break;

                // delete a lecture
                case "6":
                    
                default: // catch invalid input
                    System.out.println("\n\tInvalid entry - Please try again");
                    break;

            } // switch()

        } // while()

    } // main()

    // method to check the ID format. Throws IdException for invalid format
    private static void checkId(String id) throws IdException {
        // check if ID is correct length
        if (id.length() != 7)
            throw new IdException();
    }
}

// _____________________________
class IdException extends Exception {
    // method to print personalized error message to console
    public String getMessage() {
        return "\n\tSorry incorrect format. (Ids are 7 digits)\n";
    }
}