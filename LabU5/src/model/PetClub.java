package model;

import exceptions.EmptyList;
import exceptions.ExistentOwner;
import exceptions.NoSuchOwner;

public class PetClub {

	private Owner first;
	
	public PetClub() {
		first=null;
	}

	public Owner getFirstOwners() {
		return first;
	}
	
	public void deleteOwner(Owner thisOne, int iD) throws NoSuchOwner, EmptyList{
		if(first==null) {
			throw new EmptyList();
		}else if(thisOne.getNext()!=null && thisOne.getId()==iD) {
			thisOne.getPrevious().setNext(thisOne.getNext());
			thisOne.getNext().setPrevious(thisOne.getPrevious());
		}else if(thisOne.getNext()==null){
			throw new NoSuchOwner(Integer.toString(iD));
		}else {
			deleteOwner(thisOne.getNext(), iD);
		}
	}
	
	public void add(int id, String name, String lastName, String dOB) throws ExistentOwner{
		Owner incoming = new Owner(id, name+" "+lastName, dOB);
		Owner actual = first;
		Owner temp=first;
		if(first==null) {
			first=incoming;
		}else if(temp!=null){
			while(temp!=null) {
				if(temp.getId()==id) {
					throw new ExistentOwner(id);
				}else {
					temp=temp.getNext();
				}
			}
		}
		if(actual!=null && id<actual.getId()) {	
			incoming.setNext(first);
			first = incoming;
		}else {
			while(actual.getId()<incoming.getId() && actual.getNext()!=null){
				actual = actual.getNext();
			}if(actual.getNext()==null) {
				actual.setNext(incoming);
				incoming.setPrevious(actual);
			}else {
				incoming.setNext(actual);
				actual.getPrevious().setNext(incoming);
			}
		}
	}
	
	public Owner find(Owner thisOne, String name, String lastName, int id) throws NoSuchOwner {
		if(thisOne.getName().equalsIgnoreCase(name+" "+lastName) && thisOne.getId()==id) {
			return thisOne;
		}else if(thisOne.getNext()!=null) {
			find(thisOne.getNext(), name, lastName, id);
		}
		throw new NoSuchOwner(name);
	}
	
	public String findById(int id) throws NoSuchOwner, EmptyList {
		if(first==null) {
			throw new EmptyList();
		}else {
			Owner temp=first;
			while(temp!=null) {
				if(temp.getId()==id) {
					return temp.toString();
				}
				temp=temp.getNext();
			}
		}
		throw new NoSuchOwner(Integer.toString(id));
	}
	
	public String findByName(String name) throws NoSuchOwner, EmptyList {
		if(first==null) {
			throw new EmptyList();
		}else {
			Owner temp=first;
			while(temp!=null) {
				if(temp.getName().equals(name)) {
					return temp.toString();
				}
				temp=temp.getNext();
			}
		}
		throw new NoSuchOwner(name);
	}
	
	public void delete(int id) throws NoSuchOwner, EmptyList {
		Owner thisOne=first;
		if(thisOne==null) {
			throw new EmptyList();
		}else {
			while(thisOne!=null) {
				if(thisOne.getId()==id) {
					thisOne.getPrevious().setNext(thisOne.getNext());
					thisOne.getNext().setPrevious(thisOne.getPrevious());
				}else {
					thisOne=thisOne.getNext();
				}
			}
		}
		throw new NoSuchOwner(Integer.toString(id));
	}

	public String owners(String filter) throws EmptyList{
		String listed="";
		if(filter!=null) {
			return listOwnerByFilter(filter);
		}else {
			return listOwnerNoFilter(first, listed);
		}
	}
	
	public String listOwnerByFilter(String filter) throws EmptyList{
		Owner temp=first;
		String listed="";
		if(first==null) {
			throw new EmptyList();
		}else {
			while(temp!=null) {
				if(temp.getName().equalsIgnoreCase(filter)) {
					listed+=temp.toString()+"#";
				}else {
					temp=temp.getNext();
				}
			}
			return listed;
		}
	}
	
	public String listOwnerNoFilter(Owner actual, String listed) throws EmptyList{
		if(first==null) {
			throw new EmptyList();
		}else {
			while(actual!=null) {
				listed+=actual.toString()+"#";
				actual=actual.getNext();
			}
		}
		return listed;
	}
	
	public String listPets(String filter) throws EmptyList {
		String listed="";
		if(filter!=null) {
			return listPetByFilter(filter);
		}else {
			return listPetNoFilter(first, listed);
		}
	}
	
	public String listPetByFilter(String filter) throws EmptyList {
		Owner temp=first;
		String listed="";
		if(first==null) {
			throw new EmptyList();			
		}else {
			while(temp!=null) {
				if(temp.getFirst()==null) {
					temp=temp.getNext();
				}else {
					listed+=temp.listFilter(filter)+"#";
					temp=temp.getNext();
				}
			}
			return listed;
		}
	}
	
	public String listPetNoFilter(Owner actual, String listed) throws EmptyList {
		if(first==null) {
			throw new EmptyList();
		}else {
			while(actual!=null) {
				if(actual.getFirst()==null) {
					actual=actual.getNext();
				}else {
					listed+=actual.listNoFilter()+"#";
					actual=actual.getNext();
				}
			}
		}
		return listed;
	}
}
