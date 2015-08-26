package ie.headway.app.xml;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.Root;

@Root
public class Task {

	@Attribute private String name;
    @ElementListUnion({
        @ElementList(entry="step", inline=true, type=Step.class),
        @ElementList(entry="portable_step", inline=true, type=PortableStep.class)               
    })
	private List<Step> steps;
	
	public Task() {
		
	}
	
	public Task(final String name, List<Step> steps) {
		this.name = name;
		this.steps = steps;
	}
	
	public void addStep(Step step) {
		steps.add(step);
	}
	
	public Step getStep(int index) {
		return steps.get(index);
	}
	
}
