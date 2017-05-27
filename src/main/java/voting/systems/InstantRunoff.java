package voting.systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import voting.voting.IdeaComparator;
import voting.voting.MapComparator;
import voting.voting.Person;
import voting.voting.Population;

public class InstantRunoff implements Election {
	
	int size;
	
	public InstantRunoff(){
		size=1;
		
	}
	
	
	
	
	private Ballot vote(Person p,Population candidates){
		List<Person> preference = new ArrayList<Person>(candidates.getPeople());
		
		preference.sort(new IdeaComparator(p.getOpinions()));
		Collections.reverse(preference);
		return new Ballot(preference);
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
				ballots.add(vote(p,candidates));
			}
			
			
			int threshold = voters.getPeople().size()/(1+size);
			
			Map<Person,Integer> electionResults = new HashMap<Person,Integer>();

			for(Ballot b:ballots){
				if(electionResults.containsKey(b.getVotes().get(0))){
					electionResults.put(b.getVotes().get(0),electionResults.get(b.getVotes().get(0))+1);
				}else{
					electionResults.put(b.getVotes().get(0),1);
				}
			}
			List<Person> remainingCandidates = candidates.getPeople();
			while(electionResults.get(Collections.min(remainingCandidates,new MapComparator<Person>(electionResults)))<threshold){

				remainingCandidates.remove(Collections.min(remainingCandidates,new MapComparator<Person>(electionResults)));
				electionResults = new HashMap<Person,Integer>();
				
				for(Ballot b:ballots){
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

	Person topVote(Ballot b,List<Person> remainingCandidates){
		for(Person p:b.getVotes()){
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
