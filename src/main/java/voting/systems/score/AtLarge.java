package voting.systems.score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import voting.strategies.Ballot;
import voting.voting.Enumerated;
import voting.voting.MapComparator;
import voting.voting.Person;
import voting.voting.Population;

public class AtLarge extends Score {

	public AtLarge(int s) {
		super(s);
	}
	
	@Override
	protected Population calculate(List<Ballot<Map<Person,Float>>> ballots,Population candidates){


		Map<Person,Float> electionResults = new HashMap<Person,Float>();
		for(Person p:candidates.getPeople()){
			electionResults.put(p, (float) 0);
		}
		
		for(Ballot<Map<Person,Float>> b:ballots){
			for(Person p:b.getVote().keySet()){
				electionResults.put(p, electionResults.get(p)+b.getVote().get(p));
			}
		}
		List<Person> winners = new ArrayList<Person>(candidates.getPeople());
		Collections.sort(candidates.getPeople(),new MapComparator<Person>(electionResults));
		
		return new Population(winners.subList(0, size));
	}
	

    

}
