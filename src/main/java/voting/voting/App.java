package voting.voting;

import voting.systems.Jury;
import voting.systems.Plurality;

public class App 
{
	static int populationSize = 99;
	static int congressSize = 1;
	static int candidateSize = 3;
	static int issues = 2;
	static int repititions = 100;
    public static void main( String[] args ) 
    {
    	Simulate.vote(populationSize, congressSize, candidateSize, issues, repititions,
    			new Plurality());
    	
    	Simulate.vote(populationSize, 5, 5, issues, repititions,
    			new Jury());
    	
    }
    
    
}
