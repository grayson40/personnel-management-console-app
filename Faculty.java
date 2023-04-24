import java.util.*;

/*
	Faculty Class 
*/

public class Faculty extends Person {
	/* FIELDS */
	private String rank, officeLocation;
    private int numLectures;
    private ArrayList<Integer> lectureCrns = new ArrayList<Integer>();
	
	/* CONSTRUCTORS */
	// empty constructor
	public Faculty() {
		super();
		this.rank = "";
	}
	
	// full param constructor
	public Faculty(String name, String id, String officeLocation, int numLectures, String rank) {
		super(name, id);
        this.officeLocation = officeLocation;
		this.rank = rank;
        this.numLectures = numLectures;
	}
	
	/* METHODS */
	@Override
	public void print() {
        System.out.println("\nCourses for " + super.getName() + ":\n");
        for (Integer i : lectureCrns) {
            System.out.println(i);
        }
	} // print()
	
	// toString method
	public String toString() {
		String msg = "";
		msg += super.getName() + "\n";
		msg += "ID: " + super.getId() + "\n";
		return msg;
	} // toString()

    public void addCourse(int CRN) {
        this.lectureCrns.add(CRN);
    }
}