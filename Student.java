import java.util.ArrayList;

/*
	Student Class 
*/

public class Student extends Person {
	/* FIELDS */
    private ArrayList<Lecture> crns = new ArrayList<Lecture>();
    private ArrayList<Lab> labs = new ArrayList<Lab>();
	
	/* CONSTRUCTORS */
	
	//  full param constructor
	public Student(String name, String id) {
		super(name, id);
	}
	
	/* METHODS */
	@Override
	public void print() {
		System.out.println("hey");
	} // print()

    public void addCourse(Lecture lec) {
        this.crns.add(lec);
    }

    public void addLab(Lab lab) {
        this.labs.add(lab);
    }
	
	// toString method
	public String toString() {
		String msg = "\n";
		msg += "\t" + super.getName() + "\n";
		msg += "\tEnrolled in the following lectures\n";
        for (Lecture course : crns) {
            if (course.hasLab) {
                msg += "\t\t["+ course.prefix + "/" + course.title + "]/";
                for (Lab lab : labs) {
                    if (lab.lectureCrn.equalsIgnoreCase(course.crn)) {
                        msg += "[Lab: " + lab.crn + "]\n";
                    }
                }
            } else {
                msg += "\t["+ course.prefix + "/" + course.title + "] [" + course.modality + "]";
            }
        }
		return msg;
	} // toString()
	
}