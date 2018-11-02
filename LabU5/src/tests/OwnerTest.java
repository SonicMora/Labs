package tests;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import exceptions.*;
import model.Owner;
import model.Pet;

class OwnerTest {

	private Owner owner;
	
	public void stage1() {
		owner=new Owner(1143876043, "Victor Mora", "11/11/1998");
	}
	
	public void stage2() {
		owner=new Owner(1143876043, "Victor Mora", "11/11/1998");
		try{
			owner.add("Ronalda", "10/06/2012", "Hembra", "Gato");
			owner.add("Perla", "8/06/2012", "Hembra", "Perro");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	void testAdd() {
		stage1();
		try {
			owner.add("Ronalda", "10/06/2012", "Hembra", "Gato");
		} catch (ExistentPet e) {
			
		}
		assertEquals(new Pet("Ronalda", "10/06/2012", "Hembra", "Gato").toString(), owner.getFirst().toString());
	}
	
	@Test
	void testAddException() {
		stage2();
		boolean thrown=false;
		try {
			owner.add("Ronalda", "30/09/2010", "Hembra", "Perro");
		}catch(ExistentPet e) {
			thrown=true;
		}
		assertTrue(thrown);
	}
	
	@Test
	void testFindByName() {
		stage2();
		try {
			assertEquals("Ronalda Hembra Gato 10/06/2012", owner.findByName(owner.getFirst(), "ronalda"));
		}catch(Exception e) {
			
		}
	}

	@Test
	void testFindByNameException(){
		stage2();
		boolean thrown=false;
		try {
			owner.findByName(owner.getFirst(), "EsteNoEsta");
		}catch(NoSuchPet e) {
			thrown=true;
		} catch (EmptyList e) {

		}
		assertTrue(thrown);
	}
	
	
	@Test
	void testFindByDOB() {
		stage2();
		Pet x=new Pet("Ronalda", "10/06/2012", "Hembra", "Gato");
		try {
			assertEquals(x.toString(), owner.findByDOB(owner.getFirst(), "10/06/2012"));
		} catch (EmptyList | NoSuchPet e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	void testFindByDOBException() {
		stage2();
		boolean exception=false;
		try {
			owner.findByDOB(owner.getFirst(), "11/08/1999");
		} catch (EmptyList | NoSuchPet e) {
			exception=true;
		}
		assertTrue(exception);
	}
	
}
