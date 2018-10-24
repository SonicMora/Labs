package model;

import exceptions.NoSuchPet;

public class PetList {

	private Pet first;
	
	private int size;
	
	public PetList() {
		first=null;
		size=0;
	}

	public Pet getFirst() {
		return first;
	}

	public void setFirst(Pet first) {
		this.first = first;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public void add(String name, String dOB, String gender, String type) {
		if(first==null) {
			first=new Pet(name, dOB, gender, type);
		}else {
			Pet temp=first;
			Pet incoming=new Pet(name, dOB, gender, type);
			while(temp.getNext()!=null) {
				temp=temp.getNext();
			}
			temp.setNext(incoming);
		}
		size++;
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
			size--;
		}else if(thisOne.getNext().getName().equals(name)) {
			thisOne.setNext(thisOne.getNext().getNext());
			size--;
		}else {
			delete(thisOne.getNext(), name);
		}
		throw new NoSuchPet(name);
	}
	
}
