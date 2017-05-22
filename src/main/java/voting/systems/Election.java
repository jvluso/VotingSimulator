package voting.systems;



import voting.voting.Population;

public interface Election {
	
	public Population vote(Population voters,Population candidates) throws NotEnoughCandidates;
	
	public void setSize(int s);
	
}
