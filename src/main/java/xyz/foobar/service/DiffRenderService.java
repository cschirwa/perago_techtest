package xyz.foobar.service;

import xyz.foobar.DiffException;
import xyz.foobar.DiffRenderer;
import xyz.foobar.entity.Diff;
import xyz.foobar.entity.InnerNode;

public class DiffRenderService implements DiffRenderer{

	@Override
	public String render(Diff<?> diff) throws DiffException {
		
		InnerNode node = diff.getRoot();
		StringBuilder response = new StringBuilder();
		response.append("1 " + node.getStatus().getDescription() + ": " + node.getValue() + "\n");
		for(int i=0;i<node.getInnerNodes().size();i++) {
			if(!node.getLeafNodes().isEmpty()) {
				response.append("1." + (i+1) + " " +
								node.getInnerNodes().get(i).getStatus().getDescription() + ": " + 
								node.getInnerNodes().get(i).getValue() + " as " + 
								node.getLeafNodes().get(i).getModified() + "\n");
				
			} 
		}
		return response.toString();
	}
}
