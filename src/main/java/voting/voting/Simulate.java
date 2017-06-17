package voting.voting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import voting.population.Population;
import voting.population.PopulationType;
import voting.strategies.Strategy;
import voting.systems.CandidateSelection;
import voting.systems.Election;
import voting.systems.Jury;

public class Simulate {

	public static void electionTypes(
			 PopulationType type,
			 int repititions,
			 List<Election> votingSystems){
		

    	Population population;
    	Election candidateSelection = new CandidateSelection();
		List<ElectionsResults> results = new ArrayList<ElectionsResults>();
    	
		for(Election e:votingSystems){
			results.add(new ElectionsResults(e));
		}
		Random random = new Random();
    	for(int j=0;j<repititions;j++){
    		if(random.nextInt()%100==0){
    			System.out.println(j);
    		}
        	population = new Population(type);
        	Population candidates = candidateSelection.vote(population, population);

        	for(ElectionsResults r:results){
        		r.addResult(population, candidates);
        	}
        	
    	}

    	ElectionsResults.csvHead("\t");
    	for(ElectionsResults r:results){
    		r.csvReport("\t");
    	}
    	
		
	}
	

	public static <T> void strategies(
			 PopulationType type,
			 int repititions,
			 Election votingSystem,
			 Strategy<T> s1,
			 Strategy<T> s2){
		

    	Population population;
    	Election candidateSelection = new CandidateSelection();
		StrategyResults results = new StrategyResults(votingSystem, s1.name() + " " + s2.name());
    	
		
    	for(int j=0;j<repititions;j++){
        	population = new Population(type,s1,s2);
        	
        	
        	Population candidates = candidateSelection.vote(population, population);

        	results.addResult(population, candidates);
        	
    	}

    	StrategyResults.csvHead("\t");
    	results.csvReport("\t");
    	
		
	}
	
}
