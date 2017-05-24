package voting.systems;

import voting.voting.Population;

public class WithCandidates implements Election {
	
	int size;
	Election system;
	
	public WithCandidates(int s,Election e){
		size = s;
		system=e;
	}

	public Population vote(Population voters, Population candidates) {
		return system.vote(voters, new Population(candidates.getPeople().subList(0, size)));
	}

	@Override
	public String name() {
		return system.name() + " from " + size + " candidates";
	}


}
