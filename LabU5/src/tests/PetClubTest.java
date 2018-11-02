package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import model.*;
import exceptions.*; 

class PetClubTest {
	private PetClub pC;
	
	public void stage1() {
		pC=new PetClub();
	}
	
	@Test
	void testFind() {
		stage1();
		try {
			Owner entrada=new Owner(331321223, "Este Nuevo", "13/04/2002");
			pC.add(331321223, "Este", "Nuevo", "13/04/2002");
			assertEquals(entrada.toString(), pC.findById(331321223).toString());
		} catch (Exception e) {
		
		}
	}
	
	@Test
	void testFindException() {
		stage1();
		boolean exception=false;		try {
			pC.findById(1);
		} catch (NoSuchOwner e) {
			exception=true;
		}catch(EmptyList e) {
			
		}
		assertTrue(exception);
	}
	
	
	@Test
	void testAdd() {
		stage1();
		int x=pC.size();
		try {
			pC.add(331321223, "Este", "Nuevo", "13/04/2002");
			assertEquals(x+1, pC.size());
		} catch (Exception e) {
		
		}
	}
	
	
	@Test
	void testAddException() {
		stage1();
		boolean exception=false;
		try {
			pC.add(1143876043, "Carlos", "Baute", "12/09/2000");
		} catch (ExistentOwner e) {
			exception=true;
		}
		assertTrue(exception);
	}
	
	@Test
	void testDelete() {
		stage1();
		try {
			pC.add(331321223, "Este", "Nuevo", "13/04/2002");
			int x=pC.size();
			pC.delete(331321223);
			assertEquals(x-1, pC.size());
		}catch(Exception e){
			
		}
	}
	
	
	@Test
	void testDeleteException() {
		stage1();
		boolean exception=false;
		try {
			pC.delete(1);
		} catch (NoSuchOwner e) {
			exception=true;
		}catch(EmptyList e) {
			
		}
		assertTrue(exception);
	}
	
	
	@Test
	void testFindByName() {
		stage1();
		try {
			Owner aja=new Owner(331321223, "Este Nuevo", "13/04/2002");
			pC.add(331321223, "Este", "Nuevo", "13/04/2002");
			pC.findByName("este nuevo");
			assertEquals(aja.toString(), pC.findByName("este nuevo").toString());
		} catch (ExistentOwner | NoSuchOwner | EmptyList e) {
			e.printStackTrace();
		}		
	}
	
	
	@Test
	void testFindByNameException() {
		stage1();
		boolean exception=false;
		try {
			pC.findByName("EsteNombre NoExiste");
		} catch (NoSuchOwner e) {
			exception=true;
		} catch (EmptyList e) {
			
		}
		assertTrue(exception);
	}
	
	
	
}