package model;

import exceptions.NoSuchOwner;

public class OwnerList {

	private Owner first;
	
	private int size;
	
	public OwnerList() {
		first=null;
		size=0;
	}

	public Owner getFirst() {
		return first;
	}

	public void setFirst(Owner first) {
		this.first = first;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public void add(String id, String name, String lastName, String dOB) {
		if(first==null) {
			first=new Owner(id, name, lastName, dOB);
		}else {
			Owner temp=first;
			Owner incoming=new Owner(id, name, lastName, dOB);
			while(temp.getNext()!=null) {
				temp=temp.getNext();
			}
			temp.setNext(incoming);
			incoming.setPrevious(temp);
		}
		size++;
	}
	
	public Owner find(Owner thisOne, String name, String id) throws NoSuchOwner {
		if(thisOne.getName().equals(name) && thisOne.getId().equals(id)) {
			return thisOne;
		}else {
			find(thisOne.getNext(), name, id);
		}
		throw new NoSuchOwner(name);
	}
	
	public void delete(Owner thisOne, String id) throws NoSuchOwner{
		if(thisOne.getId().equals(id)) {
			thisOne.getPrevious().setNext(thisOne.getNext());
			thisOne.getNext().setPrevious(thisOne.getPrevious());
			size--;
		}else {
			delete(thisOne.getNext(), id);
		}
		throw new NoSuchOwner(id);
	}

	public String owners(String filter) {
		String listed="";
		if(filter!=null) {
			return "aja";
		}else {
			return listNoFilter(first, listed);
		}
	}
	
	public String listNoFilter(Owner actual, String listed) {
		if(actual.getNext()==null) {
			listed+=actual.toString();
		}else {
			listed+=actual.toString()+"\n";
			listNoFilter(actual.getNext(), listed);
		}
		return "";
	}
	
//	public void addFirst(String id, String name, String lastName, String dOB) {
//	if(first==null) {
//		first=new Owner(id, name, lastName, dOB);
//	}else {
//		Owner temp=first;
//		Owner inComing=new Owner(id, name, lastName, dOB);
//		inComing.setNext(temp);
//		first=inComing;
//	}
//	size++;
//}

}
