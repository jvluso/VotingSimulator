package voting.strategies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import voting.population.Person;
import voting.population.Population;
import voting.voting.IdeaComparator;

public class HonestList implements Strategy<List<Person>> {

	
	public HonestList(){
	}
	
	@Override
	public String name() {
		return "Honest List";
	}

	@Override
	public Ballot<List<Person>> vote(Person voter, Population candidates) {
		List<Person> preference = new ArrayList<Person>(candidates.getPeople());
		Collections.sort(preference,new IdeaComparator(voter.getOpinions()));
		return new Ballot<List<Person>>(preference);
	}

	@Override
	public Ballot<List<Person>> vote(Person voter, Population candidates, List<Ballot<List<Person>>> information) {
		return vote(voter,candidates);
	}

}
