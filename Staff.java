/*
	Staff Class 
*/
	
public class Staff extends Employee {
	/* FIELDS */
	private String status;
	
	/* CONSTRUCTORS */
	// empty constructor
	public Staff() {
		super();
		this.status = "";
	}

	// full param constructor
	public Staff(String name, String id, String dept, String status) {
		super(name, id, dept);
		this.status = status;
		System.out.println("\nStaff added!");
	}
	
	/* METHODS */
	@Override
	public void print() {
		String formatDept, formatStatus;
		formatDept = super.getDept().substring(0, 1).toUpperCase();
		if (this.status.toLowerCase().equalsIgnoreCase("f")) formatStatus = "Full Time";
		else formatStatus = "Part Time";
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println(super.getName() + "\t\t" + super.getId() + "\n" + formatDept 
				+ super.getDept().substring(1).toLowerCase() + " Department, " + formatStatus);
		System.out.println("---------------------------------------------------------------------------");
	} // print()
	
	// toString method
	public String toString() {
		String formatDept, formatStatus;
		formatDept = super.getDept().substring(0, 1).toUpperCase();
		if (this.status.toLowerCase().equalsIgnoreCase("f")) formatStatus = "Full Time";
		else formatStatus = "Part Time";
		String msg = "";
		msg += super.getName() + "\n";
		msg += "ID: " + super.getId() + "\n";
		msg += formatDept + super.getDept().substring(1).toLowerCase() + ", " + formatStatus + "\n";
		return msg;
	} // toString()
}