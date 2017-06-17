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

public class SingleTransferable extends Ranked {

	public SingleTransferable(int s) {
		super(s);
	}

	@Override
	public String name() {
		return size+"winner STV";
	}

	@Override
	protected Population calculate(List<Ballot<List<Person>>> ballots, Population candidates) {
		
		int threshold = ballots.size()/(1+size);
		List<Person> winners = new LinkedList<Person>();

		List<Person> remainingCandidates = candidates.getPeople();
		Map<Ballot<List<Person>>,Float> weightedBallots = new HashMap<Ballot<List<Person>>,Float>();

		for(Ballot<List<Person>> b:ballots){
			weightedBallots.put(b, (float) 1);
		}
		Map<Person,Float> electionResults = new HashMap<Person,Float>();
		
		
		while(winners.size()<size&&winners.size()+remainingCandidates.size()>size){

			electionResults = new HashMap<Person,Float>();
			
			for(Ballot<List<Person>> b:ballots){
				Person topVote = topVote(b,remainingCandidates);
				if(electionResults.containsKey(topVote)){
					electionResults.put(topVote,electionResults.get(topVote)+
                                                weightedBallots.get(b));
				}else{
					electionResults.put(topVote,weightedBallots.get(b));
				}
			}
			boolean cont = true;
			Iterator<Person> it = remainingCandidates.iterator();
			while(cont&&it.hasNext()){
				Person p=it.next();
				if(electionResults.get(p)>=threshold){
					cont=false;
					
					float weight = (electionResults.get(p)-threshold)/electionResults.get(p);

					for(Ballot<List<Person>> b:ballots){
						if(topVote(b,remainingCandidates).equals(p)){
							weightedBallots.put(b, weightedBallots.get(b)*weight);
						}
					}

					remainingCandidates.remove(p);
					winners.add(p);
				}
			}
			

			if(cont){
				remainingCandidates.remove(Collections.min(remainingCandidates,new MapComparator<Person>(electionResults)));
			}
		}
		if(winners.size()<size){
			winners.addAll(remainingCandidates);
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
