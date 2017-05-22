package voting.voting;

import java.util.Arrays;

import voting.systems.Election;
import voting.systems.Jury;
import voting.systems.NotEnoughCandidates;
import voting.systems.Plurality;

public class App 
{
	static int populationSize = 99;
	static int congressSize = 1;
	static int candidateSize = 3;
	static int issues = 2;
	static int repititions = 100;
    public static void main( String[] args ) throws NotEnoughCandidates
    {
    	Population population = new Population(populationSize,issues);
    	Election candidateSelection = new Jury();
    	candidateSelection.setSize(candidateSize);
    	Election votingSystem = new Plurality();
    	votingSystem.setSize(congressSize);
		
		int[] matches = new int[issues+1];
		Arrays.fill(matches, 0);
		
    	for(int i=0;i<repititions;i++){
        	population = new Population(populationSize,issues);
        	Population candidates = candidateSelection.vote(population, population);
    		matches[matchesFromVote(population,candidates,votingSystem)]++;
    	}

    	for(int i=0;i<issues+1;i++){
        	System.out.println(i+ " : " + matches[i]);
    		
    	}
    	System.out.println("done");
    }
    
    private static int matchesFromVote(Population population,Population candidates,Election votingSystem) throws NotEnoughCandidates{
    	int matches = 0;

    	Population congress = votingSystem.vote(population, candidates);

    	for(int i=0;i<issues;i++){
    		if(population.majorityVote(i) == congress.majorityVote(i)){
    			matches++;
    		}
    	}
    	
    	return matches;
    }
}
