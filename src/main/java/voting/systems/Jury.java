package voting.systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import voting.voting.Person;
import voting.voting.Population;

public class Jury implements Election {
	
	int size;
	
	public Jury(){
	}

	public Population vote(Population voters, Population candidates) {
		int poolSize = candidates.getPeople().size();
		if(size <= poolSize){
			List<Person> pool = new ArrayList<Person>(candidates.getPeople());
			Collections.shuffle(pool);
			return new Population(pool.subList(0, size));
		}else{
			return null;
		}
	}

	public void setSize(int s) {
		size=s;
	}

}
