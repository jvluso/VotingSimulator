package voting.polling;

import voting.population.Population;
import voting.systems.Election;
import voting.systems.score.ScoreRanking;

public class ScorePoll implements Poll {

	private Election poll;
	
	@Override
	public String name() {
		return "Score Poll";
	}
	
	public ScorePoll(){
		poll=new ScoreRanking();
	}

	@Override
	public Population poll(Population voters, Population candidates) {
		return poll.vote(voters, candidates);
	}

}
