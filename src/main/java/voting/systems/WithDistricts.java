package voting.systems;

import java.util.LinkedList;
import java.util.List;

import voting.population.Person;
import voting.population.Population;

public class WithDistricts implements Election {
	
	int size;
	Election system;
	
	public WithDistricts(int s,Election e){
		size = s;
		system=e;
	}

	public Population vote(Population voters, Population candidates) {
		
		List<Person> winners = new LinkedList<Person>();
		int people = voters.getPeople().size();
		
		for(int i=0;i<size;i++){
			winners.addAll(system.vote(new Population(voters.getPeople().subList(i*people/size, (i+1)*people/size)),
					new Population(candidates.getPeople().subList(i*people/size, (i+1)*people/size))).getPeople());
		}
		
		
		return new Population(winners);
	}

	@Override
	public String name() {
		return system.name() + " from " + size + " districts";
	}


}
