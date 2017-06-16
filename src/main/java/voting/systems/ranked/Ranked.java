package voting.systems.ranked;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import voting.strategies.ApprovalFiftyPercentScore;
import voting.strategies.Ballot;
import voting.strategies.HonestList;
import voting.strategies.HonestScore;
import voting.strategies.Strategy;
import voting.systems.Election;
import voting.voting.Person;
import voting.voting.Population;

public abstract class Ranked implements Election {
	
	int size;
	
	Strategy<List<Person>> voteStrategy;
	
	public Ranked(int s){
		size=s;
		voteStrategy=new HonestList();
		
	}
	
	
	
	

	public Population vote(Population voters, Population candidates) {
		int poolSize = candidates.getPeople().size();
		if(size <= poolSize){
			List<Ballot<List<Person>>> ballots = new LinkedList<Ballot<List<Person>>>();

			for(Person p:voters.getPeople()){
				ballots.add(p.vote(candidates,voteStrategy));
			}
			
			return calculate(ballots,candidates);
		}else{
			return null;
		}
	}


	protected abstract Population calculate(List<Ballot<List<Person>>> ballots,Population candidates);




}
