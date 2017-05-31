package voting.systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import voting.strategies.Ballot;
import voting.strategies.HonestNList;
import voting.strategies.Strategy;
import voting.voting.IdeaComparator;
import voting.voting.MapComparator;
import voting.voting.Person;
import voting.voting.Population;

public class Approval implements Election {
	
	int size;
	Strategy<List<Person>> voteStrategy;
	
	public Approval(int s, int v){
		size=s;
		voteStrategy=new HonestNList(v);
		
	}
	
	

	public Population vote(Population voters, Population candidates) {
		int poolSize = candidates.getPeople().size();
		if(size <= poolSize){
			List<Ballot<List<Person>>> ballots = new LinkedList<Ballot<List<Person>>>();

			for(Person p:voters.getPeople()){
				ballots.add(voteStrategy.vote(p,candidates));
			}
			
			
			Map<Person,Integer> electionResults = new HashMap<Person,Integer>();
			for(Ballot<List<Person>> b:ballots){
				for(Person p:b.getVote()){
					if(electionResults.containsKey(p)){
						electionResults.put(p,electionResults.get(p)+1);
					}else{
						electionResults.put(p,1);
					}
				}
				
			}
			
			List<Person> winners = new ArrayList<Person>(candidates.getPeople());
			Collections.sort(winners,new MapComparator<Person>(electionResults));
			
			return new Population(winners.subList(poolSize-size, poolSize));
		}else{
			return null;
		}
	}




	@Override
	public String name() {
		return size + " winner Approval using strategy " + voteStrategy.name();
	}


}
