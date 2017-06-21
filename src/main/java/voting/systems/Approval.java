package voting.systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import voting.population.Person;
import voting.population.Population;
import voting.strategies.Ballot;
import voting.strategies.HonestNList;
import voting.strategies.Strategy;
import voting.voting.MapComparator;

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
				ballots.add(p.vote(candidates,voteStrategy));
			}
			
			//each person lists all the candidates they approve of
			
			
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
			
			//count the number of votes each candidate received
			
			List<Person> winners = new ArrayList<Person>(candidates.getPeople());
			Collections.sort(winners,new MapComparator<Person>(electionResults));
			
			//the candidates with the most votes win
			
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
