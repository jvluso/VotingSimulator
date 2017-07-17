package voting.strategies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import voting.population.Person;
import voting.population.Population;
import voting.voting.IdeaComparator;

public class DishonestBulletScore implements Strategy<Map<Person,Float>> {

	
	public DishonestBulletScore(){
	}
	
	@Override
	public String name() {
		return "DishonestBulletScore";
	}

	@Override
	public Ballot<Map<Person,Float>> vote(Person voter, Population candidates) {
		List<Person> preference = new LinkedList<Person>(candidates.getPeople());
		Person topTwo1 = preference.get(0);
		preference.remove(0);
		Person topTwo2 = preference.get(0);
		preference.remove(0);
		Comparator<Person> comp = new IdeaComparator(voter.getOpinions());
		Map<Person,Float> vote = new HashMap<Person,Float>();
		Person bullet;
		
		if(comp.compare(topTwo1, topTwo2) > 0){
			bullet=topTwo2;
		}else{
			bullet=topTwo1;
		}
		
		for(Person c:candidates.getPeople()){
			if(c==bullet){
				vote.put(c, (float) 1);
			}else{
				vote.put(c, (float) 0);
			}
		}
		return new Ballot<Map<Person,Float>>(vote);
	}

	@Override
	public Ballot<Map<Person, Float>> vote(Person voter, Population candidates,
			List<Ballot<Map<Person, Float>>> information) {
		return vote(voter,candidates);
	}

}
