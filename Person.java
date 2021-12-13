/*
	Person Class 
*/

public abstract class Person {
	/* FIELDS */
	private String name;
	private String id;
	
	/* CONSTRUCTORS */
	//empty constructor
	public Person(){
		name = "";
		id = "";
		System.out.println("Person constructor");
	}
	
	// full param constructor
	public Person(String name, String id) {
		this.name = name;
		this.id = id;
	}
	
	/* GETTERS AND SETTERS*/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	/* ABSTRACT METHODS */
	public abstract void print();
	
}