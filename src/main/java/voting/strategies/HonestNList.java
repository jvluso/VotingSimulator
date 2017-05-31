package voting.strategies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import voting.voting.IdeaComparator;
import voting.voting.Person;
import voting.voting.Population;

public class HonestNList implements Strategy<List<Person>> {

	int n;
	
	public HonestNList(int m){
		n=m;
	}
	
	@Override
	public String name() {
		return "Honest " + n + " Approved";
	}

	@Override
	public Ballot<List<Person>> vote(Person voter, Population candidates) {
		List<Person> preference = new ArrayList<Person>(candidates.getPeople());
		Collections.sort(preference,new IdeaComparator(voter.getOpinions()));
		return new Ballot<List<Person>>(preference.subList(0, n));
	}

}
