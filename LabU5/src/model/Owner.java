package model;

import exceptions.EmptyList;
import exceptions.ExistentPet;
import exceptions.NoSuchPet;

public class Owner {

	private Owner previous;
	private Owner next;
	
	private int id;
	
	private String name;
	private String dOB;
	
	private Pet first;
	
	public Owner(int id, String name, String dOB) {
		this.id = id;
		this.name = name;
		this.dOB = dOB;
		
		first=null;
		
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
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	@Override
	public String toString() {
		return ""+name+" "+id+" "+dOB;
	}
	
	public Pet getFirst() {
		return first;
	}

	public void setFirst(Pet first) {
		this.first = first;
	}
	
	public void add(String name, String dOB, String gender, String type) throws ExistentPet {
		Pet incoming=new Pet(name, dOB, gender, type);
		if(first==null) {
			first=new Pet(name, dOB, gender, type);
		}else if(first.getNext()==null) {
			if(first.getName().equals(name)) {
				throw new ExistentPet(name);
			}else {
				first.setNext(incoming);
			}
		}else {
			Pet temp=first;
			while(temp.getNext()!=null) {
				if(temp.getName().equals(name)) {
					throw new ExistentPet(name);
				}else {
					temp=temp.getNext();
				}
			}
			temp.setNext(incoming);
		}
	}
	
	public Pet find(Pet thisOne, String name, String dOB) throws NoSuchPet{
		if(thisOne.getName().equals(name) && thisOne.getdOB().equals(dOB)) {
			return thisOne;
		}else {
			find(thisOne.getNext(), name, dOB);
		}
		throw new NoSuchPet(""+name+" "+dOB);
	}
	
	public void delete(Pet thisOne, String name) throws NoSuchPet{
		if(first.getName().equals(name)) {
			first.setNext(null);
			first=first.getNext();
		}else if(thisOne.getNext().getName().equals(name)) {
			thisOne.setNext(thisOne.getNext().getNext());
		}else {
			delete(thisOne.getNext(), name);
		}
		throw new NoSuchPet(name);
	}

	public String list(String filter) throws EmptyList{
		if(filter==null) {
			return listNoFilter();
		}else {
			return listFilter(filter);
		}
	}
	
	public String listNoFilter() throws EmptyList{
		String listed="";
		Pet thisOne=first;
		if(thisOne==null) {
			throw new EmptyList();
		}else {
			while(thisOne!=null) {
				listed+=thisOne.toString()+"#";
				thisOne=thisOne.getNext();
			}
		}
		return listed;
	}
	
	public String listFilter(String filter) throws EmptyList{
		Pet thisOne=first;
		String listed="";
		if(thisOne==null) {
			throw new EmptyList();
		}else {
			while(thisOne!=null) {
				if(thisOne.getName().equalsIgnoreCase(filter)) {
					listed+=thisOne.toString()+"#";
				}
				thisOne=thisOne.getNext();
			}
		}
		return listed;
	}
	
}
