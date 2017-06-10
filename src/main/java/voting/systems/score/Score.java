package voting.systems.score;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import voting.strategies.ApprovalFiftyPercentScore;
import voting.strategies.Ballot;
import voting.strategies.HonestScore;
import voting.strategies.Strategy;
import voting.systems.Election;
import voting.voting.Person;
import voting.voting.Population;

public abstract class Score implements Election {
	
	int size;
	float delta = (float) 0.5;
	
	Strategy<Map<Person,Float>> voteStrategy;
	
	public Score(int s){
		size=s;
		voteStrategy=new ApprovalFiftyPercentScore();
		
	}
	
	
	
	

	public Population vote(Population voters, Population candidates) {
		int poolSize = candidates.getPeople().size();
		if(size <= poolSize){
			List<Ballot<Map<Person,Float>>> ballots = new LinkedList<Ballot<Map<Person,Float>>>();

			for(Person p:voters.getPeople()){
				ballots.add(p.vote(candidates,voteStrategy));
			}
			
			return calculate(ballots,candidates);
		}else{
			return null;
		}
	}


	protected abstract Population calculate(List<Ballot<Map<Person,Float>>> ballots,Population candidates);


	@Override
	public String name() {
		return size + " winner Reweighted Score using strategy" + voteStrategy.name();
	}


}
