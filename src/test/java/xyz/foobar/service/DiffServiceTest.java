package xyz.foobar.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import xyz.foobar.DiffException;
import xyz.foobar.entity.Diff;
import xyz.foobar.entity.NodeStatus;
import xyz.foobar.test.Person;
import xyz.foobar.test.Pet;

class DiffServiceTest {

	private DiffService diffService = new DiffService();
	
	@Test
	void testApply() throws DiffException, IllegalArgumentException, IllegalAccessException {
		
		Person p1 = new Person();
		p1.setFirstName("Justin");
		p1.setSurname("Thomas");
		
		Person p2 = new Person();
		p2.setFirstName("Ricky");
		p2.setSurname("Fowler");
		
		Diff<?> diff = diffService.calculate(p1, p2);
		assertEquals(true, p2.equals(diffService.apply(p1, diff)));
		
	}
	@Test
	void testApplyNegative() throws DiffException, IllegalArgumentException, IllegalAccessException {
		
		Person p1 = new Person();
		p1.setFirstName("Justin");
		p1.setSurname("Thomas");
		Person p2 = new Person();
		p2.setFirstName("Ricky");
		p2.setSurname("Fowler");
		
		Pet pet = new Pet();
		pet.setName("Rover");
		pet.setType("dog");
		
		Diff<?> diff = diffService.calculate(p1, p2);
		
		assertThrows(DiffException.class, () -> diffService.apply(pet, diff));
		
	}

	@Test
	void testCalculateNulls() throws DiffException, IllegalArgumentException, IllegalAccessException {
		Person p1 = null;
		Person p2 = null;
		assertThrows(DiffException.class, () -> diffService.calculate(p1, p2));
	}
	
	@Test
	void testCalculateDifferentTypes() throws DiffException, IllegalArgumentException, IllegalAccessException {
		Person p1 = new Person();
		Pet p2 = new Pet();
		assertThrows(DiffException.class, () -> diffService.calculate(p1, p2));
	}
	
	@Test
	void testCalculateEqualObjects() throws DiffException, IllegalArgumentException, IllegalAccessException {
		
		Person p1 = new Person();
		p1.setFirstName("John");
		p1.setSurname("Barnes");
		
		Person p2 = new Person();
		p2.setFirstName("John");
		p2.setSurname("Barnes");
		
		assertNull(diffService.calculate(p1, p2));
	}
	
	@Test
	void testCalculateCreation() throws DiffException, IllegalArgumentException, IllegalAccessException {
		Person p1 = null;
		
		Person p2 = new Person();
		p2.setFirstName("John");
		p2.setSurname("Barnes");
		assertEquals(NodeStatus.CREATED, diffService.calculate(p1, p2).getRoot().getStatus());
		assertEquals("Person", diffService.calculate(p1, p2).getRoot().getValue());
	}
	
	@Test
	void testCalculateDelete() throws DiffException, IllegalArgumentException, IllegalAccessException {
		
		Person p1 = new Person();
		p1.setFirstName("John");
		p1.setSurname("Barnes");
		Person p2 = null;
		assertEquals(NodeStatus.DELETED, diffService.calculate(p1, p2).getRoot().getStatus());
		assertEquals("Person", diffService.calculate(p1, p2).getRoot().getValue());
	}
	
	@Test
	void testCalculateUpdate() throws DiffException, IllegalArgumentException, IllegalAccessException {
		Person p1 = new Person();
		p1.setFirstName("Rick");
		p1.setSurname("James");
		
		Person p2 = new Person();
		p2.setFirstName("John");
		p2.setSurname("Barnes");
		assertEquals(NodeStatus.UPDATED, diffService.calculate(p1, p2).getRoot().getStatus());
		assertEquals("Person", diffService.calculate(p1, p2).getRoot().getValue());
	}
	
}
