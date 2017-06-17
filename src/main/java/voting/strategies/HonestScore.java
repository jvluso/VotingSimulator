package voting.strategies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import voting.population.Person;
import voting.population.Population;
import voting.voting.IdeaComparator;

public class HonestScore implements Strategy<Map<Person,Float>> {

	
	public HonestScore(){
	}
	
	@Override
	public String name() {
		return "Honest Score";
	}

	@Override
	public Ballot<Map<Person,Float>> vote(Person voter, Population candidates) {
		List<Person> preference = new ArrayList<Person>(candidates.getPeople());
		IdeaComparator comp = new IdeaComparator(voter.getOpinions());
		float max = voter.dist(Collections.max(preference,comp).getOpinions());
		float min = voter.dist(Collections.min(preference,comp).getOpinions());
		Map<Person,Float> vote = new HashMap<Person,Float>();
		for(Person c:preference){
			vote.put(c, 1-(voter.dist(c.getOpinions())-min)/(max-min));
		}
		return new Ballot<Map<Person,Float>>(vote);
	}

	@Override
	public Ballot<Map<Person, Float>> vote(Person voter, Population candidates,
			List<Ballot<Map<Person, Float>>> information) {
		return vote(voter,candidates);
	}

}
