package xyz.foobar.dummy;

import java.util.HashSet;
import java.util.Set;

import xyz.foobar.DiffException;
import xyz.foobar.entity.Diff;
import xyz.foobar.service.DiffRenderService;
import xyz.foobar.service.DiffService;

public class App {

	private static DiffService diffService = new DiffService(); 
	private static DiffRenderService diffRendererService = new DiffRenderService();
	
	public static void main(String[] args) throws DiffException, IllegalArgumentException, IllegalAccessException {
		Pet goat = new Pet();
		goat.setType("goat");
		goat.setName("Billy");
		String nickName = "Cal";
		Set<String> nicknames = new HashSet<>();
		nicknames.add(nickName);
		
		Person p1 = new Person();
		p1.setFirstName("Calvin");
		p1.setSurname("Chirwa");
		p1.setNickNames(nicknames);
		p1.setPet(goat);
		
		Person p2 = new Person();
		p2.setFirstName("Charles");
		p2.setSurname("Barkley");
		p2.setPet(goat);
//		p2.setFriend(p1);
		p2.setNickNames(nicknames);
		
		Diff<?> diff = diffService.calculate(p1, p2);
		
//		diffService.apply(p1, diff);

		diffRendererService.render(diff);
		
	}
}
