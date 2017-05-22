package voting.systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import voting.voting.Person;
import voting.voting.Population;

public class Plurality implements Election {
	
	int size;
	
	public Plurality(){
	}
	
	
	private Person vote(Person p,Population candidates){
		float dist = size*2;
		Person vote=null;
		for(Person c:candidates.getPeople()){
			if(c.dist(p) < dist){
				dist = c.dist(p);
				vote = c;
			}
		}
		return vote;
	}

	public Population vote(Population voters, Population candidates) throws NotEnoughCandidates {
		int poolSize = candidates.getPeople().size();
		if(size <= poolSize){
			Map<Person,Integer> electionResults = new HashMap<Person,Integer>();
			for(Person p:voters.getPeople()){
				
				
				Person ballot = vote(p,candidates);
				
				if(electionResults.containsKey(ballot)){
					electionResults.put(ballot,electionResults.get(ballot)+1);
				}else{
					electionResults.put(ballot,1);
				}
				
			}
			
			List<Person> winners = new ArrayList<Person>(candidates.getPeople());
			Collections.sort(winners,(a,b) -> electionResults.get(a).compareTo(electionResults.get(b)));
			
			return new Population(winners.subList(poolSize-size, poolSize));
		}else{
			throw new NotEnoughCandidates();
		}
	}

	public void setSize(int s) {
		size=s;
	}

}
