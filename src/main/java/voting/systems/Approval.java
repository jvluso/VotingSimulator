package voting.systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import voting.voting.IdeaComparator;
import voting.voting.MapComparator;
import voting.voting.Person;
import voting.voting.Population;

public class Approval implements Election {
	
	int size;
	int voteStrategy;
	
	public Approval(int s, int v){
		size=s;
		voteStrategy=v;
		
	}
	
	
	
	
	private Ballot vote(Person p,Population candidates,int strategy){
		List<Person> preference = new ArrayList<Person>(candidates.getPeople());
		Collections.sort(preference,new IdeaComparator(p.getOpinions()));
		return new Ballot(preference.subList(0, strategy));
	}
	
	private class Ballot{
		private List<Person> vote;
		
		public Ballot(List<Person> v){
			vote=v;
		}
		
		public List<Person> getVotes(){
			return vote;
		}
		
	}

	public Population vote(Population voters, Population candidates) {
		int poolSize = candidates.getPeople().size();
		if(size <= poolSize){
			List<Ballot> ballots = new LinkedList<Ballot>();

			for(Person p:voters.getPeople()){
				ballots.add(vote(p,candidates,voteStrategy));
			}
			
			
			Map<Person,Integer> electionResults = new HashMap<Person,Integer>();
			for(Ballot b:ballots){
				for(Person p:b.getVotes()){
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
		return size + " winner Approval using strategy honest " + voteStrategy + " votes";
	}


}
