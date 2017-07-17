package voting.strategies;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import voting.population.Person;

public class StrategyFactory {

	
	public static List<Strategy<List<Person>>> getRankedStrategies() throws FileNotFoundException{

        Gson gson = new Gson();

        Reader reader = new FileReader("src/main/resources/Strategies.json");

        List<StrategyConfiguration> strategyConfigs = Arrays.asList(gson.fromJson(reader, StrategyConfiguration[].class));

        Reader defreader = new FileReader("src/main/resources/StrategyDefaults.json");
        
        StrategyConfiguration def = gson.fromJson(defreader, StrategyConfiguration.class);
    	
        List<Strategy<List<Person>>> strategies = new LinkedList<Strategy<List<Person>>>();
        
        for(StrategyConfiguration config:strategyConfigs){
        	Strategy<List<Person>> strat = getRankedInstance(config,def);
        	if(strat != null){
            	strategies.add(strat);
        	}
        }
        
		return strategies;
	}
	

	public static Strategy<List<Person>> getRankedInstance(StrategyConfiguration config,StrategyConfiguration def){
		config.withDefaults(def);
		switch(config.rankedType){
		case HonestList:
			return new HonestList();
		case HonestNList:
			return new HonestNList(config.ballotSize);
		case DishonestTop2List:
			return new DishonestTop2List();
		default:
			return null;
		
		}
	}
	

	public static List<Strategy<Map<Person,Float>>> getScoreStrategies() throws FileNotFoundException{

        Gson gson = new Gson();

        Reader reader = new FileReader("src/main/resources/Strategies.json");

        List<StrategyConfiguration> strategyConfigs = Arrays.asList(gson.fromJson(reader, StrategyConfiguration[].class));

        Reader defreader = new FileReader("src/main/resources/StrategyDefaults.json");
        
        StrategyConfiguration def = gson.fromJson(defreader, StrategyConfiguration.class);
    	
        List<Strategy<Map<Person,Float>>> strategies = new LinkedList<Strategy<Map<Person,Float>>>();
        
        for(StrategyConfiguration config:strategyConfigs){
        	Strategy<Map<Person,Float>> strat = getScoreInstance(config,def);
        	if(strat != null){
            	strategies.add(strat);
        	}
        }
        
		return strategies;
	}
	

	public static Strategy<Map<Person,Float>> getScoreInstance(StrategyConfiguration config,StrategyConfiguration def){
		config.withDefaults(def);
		switch(config.scoreType){
		case HonestFiftyPercentApproval:
			return new ApprovalFiftyPercentScore();
		case HonestRandomThreshold:
			return new ApprovalRandomThreshold();
		case HonestScore:
			return new HonestScore();
		default:
			return null;
		
		}
	}
}
