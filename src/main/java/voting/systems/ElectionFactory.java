package voting.systems;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;

import voting.systems.ranked.Plurality;
import voting.systems.ranked.SingleTransferable;
import voting.systems.score.AtLarge;
import voting.systems.score.SequentialScore;

public class ElectionFactory {

	public static List<Election> getElections() throws FileNotFoundException{

        Gson gson = new Gson();

        Reader reader = new FileReader("src/main/resources/Elections.json");

    	List<ElectionConfiguration> electionConfigs = Arrays.asList(gson.fromJson(reader, ElectionConfiguration[].class));

        Reader defreader = new FileReader("src/main/resources/ElectionDefaults.json");
        
        ElectionConfiguration def = gson.fromJson(defreader, ElectionConfiguration.class);
    	
        List<Election> elections = new LinkedList<Election>();
        
        for(ElectionConfiguration ec:electionConfigs){
        	elections.add(getInstance(ec,def));
        }
        
		return elections;
	}
	
	public static Election getInstance(ElectionConfiguration config,ElectionConfiguration def){
		config.withDefaults(def);
		switch(config.type){
		case AtLarge:
			return new AtLarge(config.size);
		case HypotheticalBestRunningCandidate:
			return new HypotheticalBestRunningCandidate();
		case HypotheticalDirectDemocracy:
			return new HypotheticalDirectDemocracy();
		case HypotheticalPerfectSingleWinner:
			return new HypotheticalPerfectSingleWinner();
		case Jury:
			return new Jury(config.size);
		case Plurality:
			return new Plurality();
		case SequentialScore:
			return new SequentialScore(config.size);
		case SingleTransferable:
			return new SingleTransferable(config.size);
		case WithCandidates:
			return new WithCandidates(config.candidatePool,ElectionFactory.getInstance(config.system,def));
		case WithDistricts:
			return new WithDistricts(config.districts,ElectionFactory.getInstance(config.system,def));
		default:
			return null;
		
		}
	}
}
