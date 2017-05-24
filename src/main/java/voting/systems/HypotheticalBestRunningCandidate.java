package voting.systems;

import java.util.List;

import voting.voting.IdeaComparator;
import voting.voting.Person;
import voting.voting.Population;

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
