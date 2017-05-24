package voting.voting;

import java.util.Comparator;

public class IdeaComparator implements Comparator<Person> {

	private float[] opinion;
	
	public IdeaComparator(float[] o){
		opinion=o;
	}
	
	
	@Override
	public int compare(Person o1, Person o2) {
		return (int) Math.signum(o1.dist(opinion)-o2.dist(opinion));
	}

}
