package voting.systems;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import voting.strategies.Ballot;
import voting.strategies.HonestScore;
import voting.strategies.Strategy;
import voting.voting.Person;
import voting.voting.Population;

public class Score implements Election {
	
	int size;
	
	Strategy<Map<Person,Float>> voteStrategy;
	
	public Score(int s){
		size=s;
		voteStrategy=new HonestScore();
		
	}
	
	
	
	

	public Population vote(Population voters, Population candidates) {
		int poolSize = candidates.getPeople().size();
		if(size <= poolSize){
			List<Ballot<Map<Person,Float>>> ballots = new LinkedList<Ballot<Map<Person,Float>>>();

			for(Person p:voters.getPeople()){
				ballots.add(p.vote(candidates,voteStrategy));
			}
			
			
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
					weight = (float) (0.5/(0.5+weight));
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
		}else{
			return null;
		}
	}




	@Override
	public String name() {
		return size + " winner Reweighted Score using strategy" + voteStrategy.name();
	}


}
