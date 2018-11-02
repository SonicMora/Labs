package model;

import exceptions.EmptyList;
import exceptions.ExistentOwner;
import exceptions.NoSuchOwner;

public class PetClub {

	private Owner first;
	
	private int size;
	
	public PetClub() {
		
		size=0;
		
		first=null;
		
		try {
			add(1143876043, "Victor", "Mora", "11/11/1998");
			add(94501183, "Raul", "Mora", "23/12/1976");
			add(1192803061, "Valentina", "Restrepo", "09/09/2000");
		} catch (ExistentOwner e) {
			e.printStackTrace();
		}
	}

	public Owner getFirstOwners() {
		return first;
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
			first.setPrevious(incoming);
			first = incoming;
		}else {
			if(actual!=null) {
				while(actual.getId()<incoming.getId() && actual.getNext()!=null){
					actual = actual.getNext();
				}if(actual.getNext()==null) {
					actual.setNext(incoming);
					incoming.setPrevious(actual);
				}else {
					incoming.setNext(actual);
					incoming.setPrevious(actual.getPrevious());
					actual.getPrevious().setNext(incoming);
					actual.setPrevious(incoming);
				}
			}
		}
		size++;
	}
	
	public Owner findById(int id) throws NoSuchOwner, EmptyList {
		if(first==null) {
			throw new EmptyList();
		}else {
			Owner temp=first;
			while(temp!=null) {
				if(temp.getId()==id) {
					return temp;
				}
				temp=temp.getNext();
			}
		}
		throw new NoSuchOwner(Integer.toString(id));
	}
	
	public Owner findByName(String name) throws NoSuchOwner, EmptyList {
		if(first==null) {
			throw new EmptyList();
		}else {
			Owner temp=first;
			while(temp!=null) {
				if(temp.getName().equalsIgnoreCase(name)) {
					return temp;
				}
				temp=temp.getNext();
			}
		}
		throw new NoSuchOwner(name);
	}
	
	public void delete(int id) throws NoSuchOwner, EmptyList {
		Owner thisOne=first;
		boolean deleted=false;
		if(thisOne==null){
			throw new EmptyList();
		}else {
			if(first.getId()==id) {
				first=first.getNext();
			}else {
				while(thisOne!=null && !deleted) {
					if(thisOne.getId()==id) {
						if(thisOne.getNext()!=null) {
							thisOne.getPrevious().setNext(thisOne.getNext());
							thisOne.getNext().setPrevious(thisOne.getPrevious());
							thisOne.setNext(null);
							thisOne.setPrevious(null);
						}else {
							thisOne.getPrevious().setNext(thisOne.getNext());
						}
						thisOne.setNext(null);
						thisOne.setPrevious(null);
						deleted=true;
					}else{
						thisOne=thisOne.getNext();
					}
				}if(thisOne==null) {
					throw new NoSuchOwner(Integer.toString(id));
				}
			}
		}
		size--;
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
				if(temp.getdOB().equalsIgnoreCase(filter)) {
					listed+=temp.toString()+"#";
				}
				temp=temp.getNext();
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
	
	public String listRepeatedPets(String filter) throws EmptyList {
		Owner thisOne=first;
		String listed="";
		if(thisOne==null) {
			throw new EmptyList();
		}else {
			while(thisOne!=null) {
				listed+=thisOne.listRepeatedPets(filter)+"#";
				thisOne=thisOne.getNext();
			}
		}
		return listed;
	}
	
	public String listRepeatedOwners(String filter) throws EmptyList {
		if(filter==null) {
			return listRepeatedOwnersNoFilter();
		}else {
			return listRepeatedOwnersByFilter(filter);
		}
	}
	
	public String listRepeatedOwnersNoFilter() throws EmptyList{
		String listed="";
		Owner temp=first;
		Owner thisOne=temp;
		if(first==null) {
			throw new EmptyList();
		}else {
			while(temp!=null) {
				thisOne=temp;
				while(thisOne!=null) {
					if(thisOne.getName().equals(temp.getName()) && thisOne.isListed()==false) {
						listed+=thisOne.toString();
						thisOne.setListed(true);
					}else {
						thisOne=thisOne.getNext();
					}
				}
				temp=temp.getNext();
			}
		}
		return listed;
	}
	
	public void clear() {
		first.setNext(null);
		size=0;
	}
	
	public String listRepeatedOwnersByFilter(String filter){
		String listed="";
		return listed;
	}
	
	public int size() {
		return size;
	}
}
