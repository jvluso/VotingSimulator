package voting.systems;

import java.util.List;

import voting.population.Person;
import voting.population.Population;
import voting.voting.IdeaComparator;

public class HypotheticalBestRunningCandidate implements Election {
	
	public HypotheticalBestRunningCandidate(){
	}

	public Population vote(Population voters, Population candidates) {
		List<Person> people = candidates.getPeople();
		people.sort(new IdeaComparator(voters.meanOpinion()));
		return new Population(people.subList(0, 1));
	}

	@Override
	public String name() {
		return "HypotheticalBestRunningCandidate";
	}


}
