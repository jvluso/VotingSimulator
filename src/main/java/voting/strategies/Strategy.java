package voting.strategies;

import java.util.List;

import voting.voting.Person;
import voting.voting.Population;

public interface Strategy<T> {
	
	public String name();

	public Ballot<T> vote(Person voter,Population candidates);
	

	public Ballot<T> vote(Person voter,Population candidates,List<Ballot<T>> information);

}
