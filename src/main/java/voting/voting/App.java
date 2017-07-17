package voting.voting;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import com.google.gson.Gson;

import voting.population.PopulationFactory;
import voting.strategies.StrategyFactory;
import voting.systems.Election;
import voting.systems.ElectionFactory;

public class App 
{
	
    public static void main( String[] args ) throws FileNotFoundException 
    {

        Gson gson = new Gson();

        Reader reader = new FileReader("src/main/resources/Setup.json");

    	Setup setup = gson.fromJson(reader, Setup.class);

    	switch(setup.type){
		case Elections:
	    	Simulate.electionTypes(PopulationFactory.getPopulation(), 
	    						   setup.repetitions,
	    						   ElectionFactory.getElections());
		case Strategies:
			Election election=ElectionFactory.getElections().get(0);
	    	Simulate.strategies(PopulationFactory.getPopulation(), 
	    						   setup.repetitions,
	    						   election,
	    						   StrategyFactory.getRankedStrategies().get(0),
	    						   StrategyFactory.getRankedStrategies().get(1));
		default:
			break;
    	
    	}
    	//Simulate.electionTypes(PopulationFactory.getPopulation(), 
    	//					   PopulationFactory.getRepetitions(),
    	//					   ElectionFactory.getElections());
    	//Simulate.comparison(type, repititions,election1,election2);
    	//Simulate.strategies(populationSize, issues, repititions,election,new HonestScore(),new ApprovalFiftyPercentScore());
    	
    	
    }
    

    
}
