/*
	Employee Class 
*/

public abstract class Employee extends Person {
	/* FIELDS */
	private String dept;

	/* CONSTRUCTORS */
	// empty constructor
	public Employee() {
		super();
		this.dept = "";
	}
	// full param constructor
	public Employee(String name, String id, String dept) {
		super(name, id);
		this.dept = dept;
	}
	
	/* GETTERS AND SETTERS */
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	
}