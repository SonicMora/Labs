package model;

public class PetClub {

	private OwnerList owners;
	private PetList pets;
	
	public PetClub() {
		owners=new OwnerList();
		pets=new PetList();
	}

	public OwnerList getOwners() {
		return owners;
	}

	public void setOwners(OwnerList owners) {
		this.owners = owners;
	}

	public PetList getPets() {
		return pets;
	}

	public void setPets(PetList pets) {
		this.pets = pets;
	}
		
}
