package voting.systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import voting.population.Person;
import voting.population.Population;

public class CandidateSelection implements Election {
	
	
	public CandidateSelection(){
	}

	public Population vote(Population voters, Population candidates) {
		int size = candidates.getPeople().size();
		List<Person> pool = new ArrayList<Person>(candidates.getPeople());
		Collections.shuffle(pool);
		return new Population(pool.subList(0, size));
	}

	@Override
	public String name() {
		return "Candidate Selection reordering";
	}


}
