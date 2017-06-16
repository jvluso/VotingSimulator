package voting.systems.score;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import voting.strategies.Ballot;
import voting.voting.Person;
import voting.voting.Population;

public class SequentialScore extends Score {

	public SequentialScore(int s) {
		super(s);
	}
	
	@Override
	protected Population calculate(List<Ballot<Map<Person,Float>>> ballots,Population candidates){

		List<Person> winners = new LinkedList<Person>();
		
		for(int i=0;i<size;i++){

			Map<Person,Float> electionResults = new HashMap<Person,Float>();
			for(Ballot<Map<Person,Float>> b:ballots){
				float weight = 0;
				for(Person p:winners){
					if(b.getVote().containsKey(p)){
						weight+=b.getVote().get(p);
					}
				}
				weight = delta/(delta+weight);
				for(Person p:b.getVote().keySet()){
					if(electionResults.containsKey(p)){
						electionResults.put(p,electionResults.get(p)+b.getVote().get(p)*weight);
					}else{
						electionResults.put(p,b.getVote().get(p)*weight);
					}
				}
				
			}
			winners.add(Collections.max(candidates.getPeople(),
					(a,b) -> electionResults.get(a).compareTo(electionResults.get(b))));
		}
		
		return new Population(winners);
	}

}
