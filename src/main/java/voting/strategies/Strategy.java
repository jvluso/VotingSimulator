package voting.strategies;

import java.util.List;

import voting.population.Person;
import voting.population.Population;

public interface Strategy<T> {
	
	public String name();

	public Ballot<T> vote(Person voter,Population candidates);
	

	public Ballot<T> vote(Person voter,Population candidates,List<Ballot<T>> information);

}
