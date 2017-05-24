package voting.systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import voting.voting.Person;
import voting.voting.Population;

public class HypotheticalPerfectSingleWinner implements Election {
	
	public HypotheticalPerfectSingleWinner(){
	}

	public Population vote(Population voters, Population candidates) {
		Person perfect = Person.HypotheticalPerson(voters.meanOpinion());
		List<Person> congress = new ArrayList<Person>();
		congress.add(perfect);
		return new Population(congress);
	}

	@Override
	public String name() {
		return "HypotheticalPerfectSingleWinner";
	}

}
