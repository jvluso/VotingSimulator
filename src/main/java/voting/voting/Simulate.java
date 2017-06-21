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

	public static void comparison(
			 PopulationType type,
			 int repititions,
			 Election votingSystem1,
			 Election votingSystem2){
		

    	Population population;
    	Election candidateSelection = new CandidateSelection();

    	ElectionsResults r1 = new ElectionsResults(votingSystem1);
    	ElectionsResults r2 = new ElectionsResults(votingSystem2);
    	ElectionsResults s1 = new ElectionsResults(votingSystem1);
    	ElectionsResults s2 = new ElectionsResults(votingSystem2);
		
		Random random = new Random();
    	for(int j=0;j<repititions;j++){
    		if(random.nextInt()%100==0){
    			System.out.println(j);
    		}
        	population = new Population(type);
        	Population candidates = candidateSelection.vote(population, population);

        	Population congress1=votingSystem1.vote(population, candidates);
        	Population congress2=votingSystem2.vote(population, candidates);

        	r1.addResult(population, candidates);
        	r2.addResult(population, candidates);
        	if(r1.getMajorityMisses()!=r2.getMajorityMisses()){
        		System.out.println("rep : " + j);
        		System.out.println("congress1 : " + congress1.getPeople());
        		System.out.println("congress2 : " + congress2.getPeople());
        		System.out.println("candidates : " + candidates.getPeople());
        		System.out.println("population : " + population.getPeople());
            	s1.addResult(population, candidates);
            	s2.addResult(population, candidates);
            	j=repititions;
        	}
        	
    	}

    	ElectionsResults.csvHead("\t");
    	r1.csvReport("\t");
    	r2.csvReport("\t");
    	s1.csvReport("\t");
    	s2.csvReport("\t");
    	
		
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
