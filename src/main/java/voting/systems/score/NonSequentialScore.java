package voting.systems.score;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import voting.population.Person;
import voting.population.Population;
import voting.strategies.Ballot;
import voting.voting.Enumerated;

public class NonSequentialScore extends Score {

	public NonSequentialScore(int s) {
		super(s);
	}
	
	@Override
	protected Population calculate(List<Ballot<Map<Person,Float>>> ballots,Population candidates){

		float max=0;
		List<Person> lead = null;
		
		Iterator<List<Person>> it = new Enumerated<Person>(size,candidates.getPeople());
		
		while(it.hasNext()){
			List<Person> w = it.next();
			float score = 0;
			for(Ballot<Map<Person,Float>> b: ballots){
				float choices = 0;
				for(Person p:w){
					choices+=b.getVote().get(p);
				}
				for(int i=0;i<choices;i++){
					score+=delta/(delta+i);
				}
			}
			if(score>max){
				max=score;
				lead=w;
			}

		}
		
		return new Population(lead);
	}
	

    

}
