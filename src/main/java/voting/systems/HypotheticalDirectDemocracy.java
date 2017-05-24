package voting.systems;

import voting.voting.Population;

public class HypotheticalDirectDemocracy implements Election {
	
	
	public HypotheticalDirectDemocracy(){
	}

	public Population vote(Population voters, Population candidates) {
		return voters;
	}

	@Override
	public String name() {
		return "HypotheticalDirectDemocracy";
	}

}
