package voting.voting;

import java.util.ArrayList;
import java.util.List;

import voting.strategies.Strategy;
import voting.systems.Election;
import voting.systems.Jury;

public class Simulate {

	public static void electionTypes(
			 int populationSize,
			 int issues,
			 int repititions,
			 List<Election> votingSystems){
		

    	Population population;
    	Election candidateSelection = new Jury(populationSize);
		List<ElectionsResults> results = new ArrayList<ElectionsResults>();
    	
		for(Election e:votingSystems){
			results.add(new ElectionsResults(e));
		}
		
    	for(int j=0;j<repititions;j++){
        	population = new Population(populationSize,issues);
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
	

	public static void strategies(
			 int populationSize,
			 int issues,
			 int repititions,
			 Election votingSystems,
			 Strategy s1,
			 Strategy s2){
		

    	Population population;
    	Election candidateSelection = new Jury(populationSize);
		List<ElectionsResults> results = new ArrayList<ElectionsResults>();
    	
		for(Election e:votingSystems){
			results.add(new ElectionsResults(e));
		}
		
    	for(int j=0;j<repititions;j++){
        	population = new Population(populationSize,issues);
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
	
}
