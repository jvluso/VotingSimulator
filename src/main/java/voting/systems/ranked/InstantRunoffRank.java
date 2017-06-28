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

public class InstantRunoffRank extends Ranked {

	public InstantRunoffRank() {
		super(0);
	}

	@Override
	public String name() {
		return "IRV using strategy " + voteStrategy.name();
	}

	@Override
	protected Population calculate(List<Ballot<List<Person>>> ballots, Population candidates) {
		
		List<Person> winners = new LinkedList<Person>();

		List<Person> remainingCandidates = candidates.getPeople();

		Map<Person,Integer> electionResults = new HashMap<Person,Integer>();
		
		
		while(remainingCandidates.size()>0){

			electionResults = new HashMap<Person,Integer>();
			
			for(Ballot<List<Person>> b:ballots){
				Person topVote = topVote(b,remainingCandidates);
				if(electionResults.containsKey(topVote)){
					electionResults.put(topVote,electionResults.get(topVote)+1);
				}else{
					electionResults.put(topVote,1);
				}
			}
			Person elim = Collections.min(remainingCandidates,new MapComparator<Person>(electionResults));
			winners.add(elim);
			remainingCandidates.remove(elim);
			
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
