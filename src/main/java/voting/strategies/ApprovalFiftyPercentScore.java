package voting.strategies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import voting.population.Person;
import voting.population.Population;
import voting.voting.IdeaComparator;

public class ApprovalFiftyPercentScore implements Strategy<Map<Person,Float>> {

	
	public ApprovalFiftyPercentScore(){
	}
	
	@Override
	public String name() {
		return "ApprovalFiftyPercentScore";
	}

	@Override
	public Ballot<Map<Person,Float>> vote(Person voter, Population candidates) {
		List<Person> preference = new ArrayList<Person>(candidates.getPeople());
		IdeaComparator comp = new IdeaComparator(voter.getOpinions());
		float max = voter.dist(Collections.max(preference,comp).getOpinions());
		float min = voter.dist(Collections.min(preference,comp).getOpinions());
		Map<Person,Float> vote = new HashMap<Person,Float>();
		for(Person c:preference){
			if(voter.dist(c.getOpinions())<(max+min)/2){
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
