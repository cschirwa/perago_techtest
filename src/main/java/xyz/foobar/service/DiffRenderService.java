package xyz.foobar.service;

import xyz.foobar.DiffException;
import xyz.foobar.DiffRenderer;
import xyz.foobar.entity.Diff;
import xyz.foobar.entity.InnerNode;

public class DiffRenderService implements DiffRenderer{

	@Override
	public String render(Diff<?> diff) throws DiffException {
		
		InnerNode node = diff.getRoot();
		System.out.println("1 " + node.getStatus().getDescription() + ": " + node.getValue());
		for(int i=0;i<node.getInnerNodes().size();i++) {
			System.out.println(
					"1." + (i+1) + " " +
					node.getInnerNodes().get(i).getStatus().getDescription()
					+ ": " + node.getInnerNodes().get(i).getName() + " as "
					+ node.getLeafNodes().get(i).getModified());
//			System.out.println("Leaf Original: " + node.getLeafNodes().get(i).getOriginal());
			
		}
		
		return null;
	}

	
}
