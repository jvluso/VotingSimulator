package voting.strategies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import voting.population.Person;
import voting.population.Population;
import voting.voting.IdeaComparator;

public class ApprovalRandomThreshold implements Strategy<Map<Person,Float>> {

	
	public ApprovalRandomThreshold(){
	}
	
	@Override
	public String name() {
		return "ApprovalRandomThreshold";
	}

	@Override
	public Ballot<Map<Person,Float>> vote(Person voter, Population candidates) {
		List<Person> preference = new ArrayList<Person>(candidates.getPeople());
		IdeaComparator comp = new IdeaComparator(voter.getOpinions());
		float max = voter.dist(Collections.max(preference,comp).getOpinions());
		float min = voter.dist(Collections.min(preference,comp).getOpinions());
		Map<Person,Float> vote = new HashMap<Person,Float>();
		float threshold = new Random().nextFloat()*(max-min)+min;
		for(Person c:preference){
			if(voter.dist(c.getOpinions())<threshold){
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
