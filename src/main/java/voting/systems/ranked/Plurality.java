package voting.systems.ranked;

import java.util.HashMap;
import java.util.List;
import voting.population.Person;
import voting.population.Population;
import voting.strategies.Ballot;
import voting.voting.MapComparator;

public class Plurality extends Ranked {

	public Plurality() {
		super(1);
	}

	@Override
	public String name() {
		return "Plurality";
	}

	@Override
	protected Population calculate(List<Ballot<List<Person>>> ballots, Population candidates) {
		
		HashMap<Person,Integer> electionResults = new HashMap<Person,Integer>();
		
		for(Ballot<List<Person>> b:ballots){
			Person topVote = topVote(b,candidates.getPeople());
			if(electionResults.containsKey(topVote)){
				electionResults.put(topVote,electionResults.get(topVote)+
                                            1);
			}else{
				electionResults.put(topVote,1);
			}
		}
		
		List<Person> winners = candidates.getPeople();
		winners.sort(new MapComparator<Person>(electionResults));
		return new Population(winners.subList(winners.size()-1, winners.size()));
		
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
