package model;

public class Owner {

	private Owner previous;
	private Owner next;
	
	private String id;
	private String name;
	private String lastName;
	private String dOB;
	
	public Owner(String id, String name, String lastName, String dOB) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.dOB = dOB;
		
		previous=null;
		next=null;
	}
	
	public Owner getPrevious() {
		return previous;
	}
	
	public void setPrevious(Owner previous) {
		this.previous = previous;
	}
	
	public Owner getNext() {
		return next;
	}
	
	public void setNext(Owner next) {
		this.next = next;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getdOB() {
		return dOB;
	}
	
	public void setdOB(String dOB) {
		this.dOB = dOB;
	}
	
	@Override
	public String toString() {
		return ""+name+" "+lastName+" "+id+" "+dOB;
	}
	
}
