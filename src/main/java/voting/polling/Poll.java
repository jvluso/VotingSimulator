package voting.polling;

import voting.population.Population;

public interface Poll {

	public String name();

	public Population poll(Population voters,Population candidates);
	
}
