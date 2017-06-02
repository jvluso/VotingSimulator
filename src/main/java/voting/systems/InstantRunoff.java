package voting.systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import voting.strategies.Ballot;
import voting.strategies.HonestNList;
import voting.strategies.Strategy;
import voting.voting.IdeaComparator;
import voting.voting.MapComparator;
import voting.voting.Person;
import voting.voting.Population;

public class InstantRunoff implements Election {
	
	int size;
	
	public InstantRunoff(){
		size=1;
	}
	
	
	
	
	

	public Population vote(Population voters, Population candidates) {
		int poolSize = candidates.getPeople().size();
		if(size <= poolSize){
			List<Ballot<List<Person>>> ballots = new LinkedList<Ballot<List<Person>>>();
			

			Strategy<List<Person>> strategy = new HonestNList(candidates.getPeople().size());

			for(Person p:voters.getPeople()){
				ballots.add(p.vote(candidates,strategy));
			}
			
			
			int threshold = voters.getPeople().size()/(1+size);
			
			Map<Person,Integer> electionResults = new HashMap<Person,Integer>();

			for(Ballot<List<Person>> b:ballots){
				if(electionResults.containsKey(b.getVote().get(0))){
					electionResults.put(b.getVote().get(0),electionResults.get(b.getVote().get(0))+1);
				}else{
					electionResults.put(b.getVote().get(0),1);
				}
			}
			List<Person> remainingCandidates = candidates.getPeople();
			while(!electionResults.containsKey(Collections.min(remainingCandidates,new MapComparator<Person>(electionResults)))||
					electionResults.get(Collections.min(remainingCandidates,new MapComparator<Person>(electionResults)))<threshold){

				remainingCandidates.remove(Collections.min(remainingCandidates,new MapComparator<Person>(electionResults)));
				electionResults = new HashMap<Person,Integer>();
				
				for(Ballot<List<Person>> b:ballots){
					Person topVote = topVote(b,remainingCandidates);
					if(electionResults.containsKey(topVote)){
						electionResults.put(topVote,electionResults.get(topVote)+1);
					}else{
						electionResults.put(topVote,1);
					}
				}
				
			}
			
			
			
			return new Population(remainingCandidates.subList(0, size));
		}else{
			return null;
		}
	}

	Person topVote(Ballot<List<Person>> b,List<Person> remainingCandidates){
		for(Person p:b.getVote()){
			if(remainingCandidates.contains(p)){
				return p;
			}
		}
		return null;
	}


	@Override
	public String name() {
		return size + " winner IRV using strategy honest";
	}


}
