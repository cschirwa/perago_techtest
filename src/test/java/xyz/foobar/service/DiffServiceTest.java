package xyz.foobar.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import xyz.foobar.entity.Diff;
import xyz.foobar.test.Person;
import xyz.foobar.test.Pet;

class DiffServiceTest {

	private DiffService diffService;
	
	@Test
	void testApply() {
		Pet goat = new Pet();
		goat.setType("goat");
		goat.setName("Billy");
		
		Person p1 = new Person();
		p1.setFirstName("Calvin");
		p1.setSurname("Chirwa");
		
		Person p2 = new Person();
		p2.setFirstName("Charles");
		p2.setSurname("Barkley");
		p2.setPet(goat);
		p2.setFriend(p1);
		
//		Diff diff = diffService.calculate(p1, p2);
//		System.out.println();
//		
//		diffService.apply(p1, diff);
		
	}

	@Test
	void testCalculate() {
		fail("Not yet implemented");
	}

}
