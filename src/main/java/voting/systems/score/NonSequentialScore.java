package voting.systems.score;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import voting.strategies.Ballot;
import voting.voting.MapComparator;
import voting.voting.Person;
import voting.voting.Population;

public class NonSequentialScore extends Score {

	public NonSequentialScore(int s) {
		super(s);
	}
	
	@Override
	protected Population calculate(List<Ballot<Map<Person,Float>>> ballots,Population candidates){

		List<List<Person>> winners = enumerate(size,candidates.getPeople());
		Map<List<Person>,Float> representativeness = new HashMap<List<Person>,Float>();
		
		for(List<Person> w:winners){
			float choices = 0;
			float score = 0;
			for(Ballot<Map<Person,Float>> b: ballots){
				for(Person p:w){
					choices+=b.getVote().get(p);
				}
			}
			for(int i=0;i<choices;i++){
				score+=delta/(delta+i);
			}
			representativeness.put(w, score);

		}
		
		return new Population(Collections.max(winners,new MapComparator<List<Person>>(representativeness)));
	}
	

	private static List<List<Person>> enumerate(int s,List<Person> candidates){
		List<List<Person>> lists = new LinkedList<List<Person>>();
		
		if(s<=0){
			lists.add(new LinkedList<Person>());
			return lists;
		}else{
			
			for(Person c:candidates){
				List<Person> removed = new LinkedList<Person>(candidates.subList(0,candidates.indexOf(c)));
				for(List<Person> l:enumerate(s-1,removed)){
					l.add(c);
					lists.add(l);
				}
			}

			return lists;
		}
		
	}
    

}
