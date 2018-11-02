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
	
	private boolean listed;
	
	private Pet first;
	
	public Owner(int id, String name, String dOB) {
		this.id = id;
		this.name = name;
		this.dOB = dOB;
		
		first=null;
		
		listed=false;
		
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
	
	public Pet getFirst() {
		return first;
	}

	public void setFirst(Pet first) {
		this.first = first;
	}
	
	public void add(String name, String dOB, String gender, String type) throws ExistentPet {
		Pet incoming=new Pet(name, dOB, gender, type);
		if(first==null) {
			first=incoming;
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
	
	public String findByName(Pet thisOne, String name) throws NoSuchPet, EmptyList{
		if(first==null) {
			throw new EmptyList();
		}while(thisOne!=null) {
			if(thisOne.getName().equalsIgnoreCase(name)) {
				return thisOne.toString();
			}else {
				thisOne=thisOne.getNext();
			}
		}
		throw new NoSuchPet(name);
	}
	
	public String findByDOB(Pet thisOne, String dOBP) throws EmptyList, NoSuchPet {
		if(first==null) {
			throw new EmptyList();
		}while(thisOne!=null) {
			if(thisOne.getdOB().equalsIgnoreCase(dOBP)) {
				return thisOne.toString();
			}else {
				thisOne=thisOne.getNext();
			}
		}
		throw new NoSuchPet(dOBP);
	}
	
	public void delete(String name) throws NoSuchPet, EmptyList{
		Pet temp=first;
		boolean found=false;
		if(first==null) {
			throw new EmptyList();
		}else if(first.getName().equalsIgnoreCase(name)){
			first=first.getNext();
		}else {
			while(temp.getNext()!=null) {
				if(temp.getNext().getName().equalsIgnoreCase(name)) {
					temp.setNext(temp.getNext().getNext());
					found=true;
				}else {
					temp=temp.getNext();
				}
			}if(found==false) {
				throw new NoSuchPet(name);
			}
		}
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
				if(thisOne.getdOB().equalsIgnoreCase(filter)) {
					listed+=thisOne.toString()+"#";
				}
				thisOne=thisOne.getNext();
			}
		}
		return listed;
	}
	
	public String listRepeatedPets(String filter) {
		Pet thisOne=first;
		if(thisOne==null) {
			return "";
		}else if(filter==null) {
			return listRepeatedNoFilter();
		}else {
			return listRepeatedFilter(filter);
		}
	}
	
	public String listRepeatedNoFilter(){
		String list="";
		Pet thisOne=first;
		Pet temp=thisOne;
		while(thisOne!=null) {
			temp=thisOne;
			while(temp!=null) {
				if(thisOne.getGender().equalsIgnoreCase(temp.getGender()) && temp.isListed()==false) {
					list+=temp.toString()+"#";
					temp.setListed(true);
				}else {
					temp=temp.getNext();
				}
			}
			thisOne=thisOne.getNext();
		}
		return list;
	}
	
	public String listRepeatedFilter(String filter){
		String list="";
		Pet thisOne=first;
		Pet temp=thisOne;
		while(thisOne!=null) {
			while(temp!=null) {
				if(thisOne.getGender().equalsIgnoreCase(temp.getGender()) && temp.getdOB().equalsIgnoreCase(filter) && temp.isListed()==false) {
					list+=temp.toString()+"#";
					temp.setListed(true);
				}else {
					temp=temp.getNext();
				}
			}
			thisOne=thisOne.getNext();
		}
		return list;
	}
	
	@Override
	public String toString() {
		return ""+name+" "+id+" "+dOB;
	}

	public boolean isListed() {
		return listed;
	}

	public void setListed(boolean listed) {
		this.listed = listed;
	}
	
}
