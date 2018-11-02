package model;

public class Pet {

	private Pet next;
	
	private String name;
	private String dOB;
	private String gender;
	private String type;
	
	private boolean listed;
	
	public Pet(String name, String dOB, String gender, String type) {
		this.name = name;
		this.dOB = dOB;
		this.gender = gender;
		this.type = type;
		
		setListed(false);
		next=null;
	}
	
	public Pet getNext() {
		return next;
	}
	
	public void setNext(Pet next) {
		this.next = next;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getdOB() {
		return dOB;
	}
	
	public void setdOB(String dOB) {
		this.dOB = dOB;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public boolean isListed() {
		return listed;
	}

	public void setListed(boolean listed) {
		this.listed = listed;
	}
	
	@Override
	public String toString() {
		return name+" "+gender+" "+type+" "+dOB;
	}
	
}
