package voting.voting;

import voting.systems.Election;
import voting.systems.Jury;

public class Simulate {

	public static void vote(
			 int populationSize,
			 int congressSize,
			 int candidateSize,
			 int issues,
			 int repititions,
			 Election votingSystem){
		

    	Population population = new Population(populationSize,issues);
    	Election candidateSelection = new Jury();
    	candidateSelection.setSize(candidateSize);
    	votingSystem.setSize(congressSize);
		
		double majorityMisses = 0;
		double superMajorityMisses = 0;
		double meanDist = 0;
		
    	for(int j=0;j<repititions;j++){
        	population = new Population(populationSize,issues);
        	Population candidates = candidateSelection.vote(population, population);
        	Population congress = candidateSelection.vote(population, candidates);

        	float dist = 0;
        	for(int i=0;i<issues;i++){
        		if(population.majorityVote(i) != congress.majorityVote(i)){
        			majorityMisses++;
        		}
        		if(population.superMajorityVote(i) != congress.superMajorityVote(i)){
        			superMajorityMisses++;
        		}
        		dist+=(population.meanOpinion(i)-congress.meanOpinion(i))*(population.meanOpinion(i)-congress.meanOpinion(i));
        	}
        	meanDist+=Math.sqrt(dist);
        	
    	}

    	System.out.println("majorityMisses : " + majorityMisses/repititions);
    	System.out.println("superMajorityMisses : " + superMajorityMisses/repititions);
    	System.out.println("meanDist : " + meanDist/repititions);
    	

    	System.out.println("done");
		
	}
}
