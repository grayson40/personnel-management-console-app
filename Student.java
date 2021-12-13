/*
	Student Class 
*/

public class Student extends Person {
	/* FIELDS */
	private double gpa;
	private int hours;
	
	/* CONSTRUCTORS */
	// empty constructor
	public Student() {
		super();
		this.gpa = 0.0;
		this.hours = 0;
	}
	
	//  full param constructor
	public Student(String name, String id, double gpa, int hours) {
		super(name, id);
		this.gpa = gpa;
		this.hours = hours;
		System.out.println("\nStudent added!");
	}
	
	/* METHODS */
	@Override
	public void print() {
		tuitionInvoice();
	} // print()
	
	// method to calculate tuition and print invoice to console
	public void tuitionInvoice() {
		final double fees = 52.0, costPerHour = 236.45, discountPercent = 0.25;
		double total, discount = 0.0;
		
		total = this.hours * costPerHour;
		total += fees;
		
		if (this.gpa >= 3.85)
			discount = total * discountPercent;
		
		total -= discount;
		
		System.out.println("---------------------------------------------------------------------------");
		System.out.printf("%s\t\t\t%s\nCredit Hours: %d ($%.2f/credit hour)\nFees: $%.0f\n\nTotal payment (after discount): $%.2f\t($%.2f discount applied)\n",
				super.getName(), super.getId(), this.hours, costPerHour, fees, total, discount);
		System.out.println("---------------------------------------------------------------------------");
	} // tuitionInvoice()
	
	// toString method
	public String toString() {
		String msg = "";
		msg += super.getName() + "\n";
		msg += "ID: " + super.getId() + "\n";
		msg += "Gpa: " + this.gpa + "\n";
		msg += "Credit hours: " + this.hours + "\n";
		return msg;
	} // toString()
	

	/* GETTERS AND SETTERS */
	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}
	
}