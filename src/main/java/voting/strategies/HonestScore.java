package voting.strategies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import voting.voting.IdeaComparator;
import voting.voting.Person;
import voting.voting.Population;

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

}
