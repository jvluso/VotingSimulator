package voting.strategies;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import voting.population.Person;
import voting.population.Population;
import voting.voting.IdeaComparator;

public class DishonestTop2List implements Strategy<List<Person>> {

	
	public DishonestTop2List(){
	}
	
	@Override
	public String name() {
		return "Dishonest top 2 List";
	}

	@Override
	public Ballot<List<Person>> vote(Person voter, Population candidates) {
		List<Person> preference = new LinkedList<Person>(candidates.getPeople());
		Person topTwo1 = preference.get(0);
		preference.remove(0);
		Person topTwo2 = preference.get(0);
		preference.remove(0);
		Comparator<Person> comp = new IdeaComparator(voter.getOpinions());
		Collections.sort(preference,comp);
		List<Person> ballot = new LinkedList<Person>();
		
		if(comp.compare(topTwo1, topTwo2) > 0){
			ballot.add(topTwo2);
			ballot.addAll(preference);
			ballot.add(topTwo1);
		}else{
			ballot.add(topTwo1);
			ballot.addAll(preference);
			ballot.add(topTwo2);
			
		}
		
		return new Ballot<List<Person>>(ballot);
	}

	@Override
	public Ballot<List<Person>> vote(Person voter, Population candidates, List<Ballot<List<Person>>> information) {
		return vote(voter,candidates);
	}

}
