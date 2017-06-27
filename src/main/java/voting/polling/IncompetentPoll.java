package voting.polling;

import voting.population.Population;

public class IncompetentPoll implements Poll {

	@Override
	public String name() {
		return "Incompetent Poll";
	}
	
	public IncompetentPoll(){
		
	}

	@Override
	public Population poll(Population voters, Population candidates) {
		return candidates;
	}

}
