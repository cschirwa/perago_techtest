package xyz.foobar.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import xyz.foobar.DiffException;
import xyz.foobar.entity.Diff;
import xyz.foobar.test.Person;

class DiffRenderServiceTest {

	private DiffService diffService = new DiffService();
	
	private DiffRenderService diffRenderService = new DiffRenderService();
	
	@Test
	void testRender() throws DiffException, IllegalArgumentException, IllegalAccessException {
		
		Person p1 = new Person();
		p1.setFirstName("Rory");
		p1.setSurname("McIlroy");
		
		Person p2 = new Person();
		p2.setFirstName("Michael");
		p2.setSurname("Thompson");
		
		Diff<?> diff = diffService.calculate(p1, p2);
		System.out.println(diffRenderService.render(diff));
		String expectedDiffString = "1 Update: Person\n" + 
				"1.1 Update: firstName as Michael\n" + 
				"1.2 Update: surname as Thompson".trim();
		String actualDiffString = (diffRenderService.render(diff)).trim();
		assertTrue(expectedDiffString.equalsIgnoreCase(actualDiffString));
		
	}
	
	@Test
	void testRenderNullDiff() throws DiffException, IllegalArgumentException, IllegalAccessException {
		
		assertThrows(DiffException.class, () -> diffRenderService.render(null) );
		
	}

}
