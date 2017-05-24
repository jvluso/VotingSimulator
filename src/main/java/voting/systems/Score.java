package voting.systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import voting.voting.IdeaComparator;
import voting.voting.Person;
import voting.voting.Population;

public class Score implements Election {
	
	int size;
	
	public Score(int s){
		size=s;
		
	}
	
	
	
	
	private Ballot vote(Person p,Population candidates){
		List<Person> preference = new ArrayList<Person>(candidates.getPeople());
		IdeaComparator comp = new IdeaComparator(p.getOpinions());
		float max = p.dist(Collections.max(preference,comp).getOpinions());
		float min = p.dist(Collections.min(preference,comp).getOpinions());
		Map<Person,Float> vote = new HashMap<Person,Float>();
		for(Person c:preference){
			vote.put(c, 1-(p.dist(c.getOpinions())-min)/(max-min));
		}
		return new Ballot(vote);
	}
	
	private class Ballot{
		private Map<Person,Float> vote;
		
		public Ballot(Map<Person,Float> v){
			vote=v;
		}
		
		public Map<Person,Float> getVotes(){
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
			
			
			List<Person> winners = new LinkedList<Person>();
			
			for(int i=0;i<size;i++){

				Map<Person,Float> electionResults = new HashMap<Person,Float>();
				for(Ballot b:ballots){
					float weight = 0;
					for(Person p:winners){
						if(b.getVotes().containsKey(p)){
							weight+=b.getVotes().get(p);
						}
					}
					weight = (float) (0.5/(0.5+weight));
					for(Person p:b.getVotes().keySet()){
						if(electionResults.containsKey(p)){
							electionResults.put(p,electionResults.get(p)+b.getVotes().get(p)*weight);
						}else{
							electionResults.put(p,b.getVotes().get(p)*weight);
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
		return size + " winner Reweighted Score using strategy honest";
	}


}
