package voting.polling;

import voting.population.Population;
import voting.systems.Election;
import voting.systems.ranked.InstantRunoffRank;
import voting.systems.score.ScoreRanking;

public class IRVPoll implements Poll {

	private Election poll;
	
	@Override
	public String name() {
		return "Score Poll";
	}
	
	public IRVPoll(){
		poll=new InstantRunoffRank();
	}

	@Override
	public Population poll(Population voters, Population candidates) {
		return poll.vote(voters, candidates);
	}

}
