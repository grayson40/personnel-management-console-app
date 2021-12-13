/*
	Faculty Class 
*/

public class Faculty extends Employee {
	/* FIELDS */
	private String rank;
	
	/* CONSTRUCTORS */
	// empty constructor
	public Faculty() {
		super();
		this.rank = "";
	}
	
	// full param constructor
	public Faculty(String name, String id, String dept, String rank) {
		super(name, id, dept);
		this.rank = rank;
		System.out.println("\nFaculty added!");
	}
	
	/* METHODS */
	@Override
	public void print() {
		String formatDept, formatRank;
		formatDept = super.getDept().substring(0, 1).toUpperCase();
		formatRank = this.rank.substring(0, 1).toUpperCase();
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println(super.getName() + "\t\t" + super.getId() + "\n" + formatDept + super.getDept().substring(1).toLowerCase() 
				+ " Department, " + formatRank + this.rank.substring(1).toLowerCase());
		System.out.println("---------------------------------------------------------------------------");
	} // print()
	
	// toString method
	public String toString() {
		String msg = "";
		String formatDept, formatRank;
		formatDept = super.getDept().substring(0, 1).toUpperCase();
		formatRank = this.rank.substring(0, 1).toUpperCase();
		msg += super.getName() + "\n";
		msg += "ID: " + super.getId() + "\n";
		msg += formatRank + this.rank.substring(1).toLowerCase() + ", " + formatDept + super.getDept().substring(1).toLowerCase() + "\n";
		return msg;
	} // toString()
}