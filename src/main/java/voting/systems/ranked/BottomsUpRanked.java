package voting.systems.ranked;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import voting.population.Person;
import voting.population.Population;
import voting.strategies.Ballot;
import voting.voting.MapComparator;

public class BottomsUpRanked extends Ranked {

	public BottomsUpRanked(int s) {
		super(s);
	}

	@Override
	public String name() {
		return "BUR using strategy " + voteStrategy.name();
	}

	@Override
	protected Population calculate(List<Ballot<List<Person>>> ballots, Population candidates) {
		
		List<Person> winners = new LinkedList<Person>();

		List<Person> remainingCandidates = candidates.getPeople();

		Map<Person,Integer> electionResults = new HashMap<Person,Integer>();
		
		
		while(remainingCandidates.size()>size-winners.size()&&winners.size()<size){

			electionResults = new HashMap<Person,Integer>();
			for(Ballot<List<Person>> b:ballots){
				Person topVote = topVote(b,remainingCandidates);
				if(electionResults.containsKey(topVote)){
					electionResults.put(topVote,electionResults.get(topVote)+1);
				}else{
					electionResults.put(topVote,1);
				}
			}
			
			Person lead = Collections.max(remainingCandidates,new MapComparator<Person>(electionResults));
			float threshold = ballots.size()/(size-winners.size()-1);
			if(electionResults.get(lead)>threshold){
				winners.add(lead);
				List<Ballot<List<Person>>> counted = new LinkedList<Ballot<List<Person>>>();
				for(Ballot<List<Person>>b:ballots){
					if(topVote(b,remainingCandidates)==lead){
						counted.add(b);
					}
				}
				ballots.removeAll(counted);
				remainingCandidates.remove(lead);
			}else{
				Person elim = Collections.min(remainingCandidates,new MapComparator<Person>(electionResults));
				remainingCandidates.remove(elim);
			}
		}
		
		return new Population(winners);
		
	}
	

	Person topVote(Ballot<List<Person>> b,List<Person> remainingCandidates){
		for(Person p:b.getVote()){
			if(remainingCandidates.contains(p)){
				return p;
			}
		}
		return null;
	}

}
