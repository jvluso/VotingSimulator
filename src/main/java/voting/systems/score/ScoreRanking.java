package voting.systems.score;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import voting.population.Person;
import voting.population.Population;
import voting.strategies.ApprovalFiftyPercentScore;
import voting.strategies.Ballot;
import voting.voting.MapComparator;

public class ScoreRanking extends Score {

	public ScoreRanking() {
		super(0);
	}
	
	@Override
	protected Population calculate(List<Ballot<Map<Person,Float>>> ballots,Population candidates){

		List<Person> winners = candidates.getPeople();
		Map<Person,Float> electionResults = new HashMap<Person,Float>();
		for(Ballot<Map<Person,Float>> b:ballots){
			for(Person p:b.getVote().keySet()){
				if(electionResults.containsKey(p)){
					electionResults.put(p,electionResults.get(p)+b.getVote().get(p));
				}else{
					electionResults.put(p,b.getVote().get(p));
				}
			}
			
		}
		Collections.sort(winners, new MapComparator<Person>(electionResults));
		
		
		return new Population(winners);
	}
	


	@Override
	public String name() {
		return size + " winner At Large using strategy " + voteStrategy.name();
	}

}
