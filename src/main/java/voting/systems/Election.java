package voting.systems;



import voting.voting.Population;

public interface Election {
	
	public String name();
	
	public Population vote(Population voters,Population candidates);
	
	
}
