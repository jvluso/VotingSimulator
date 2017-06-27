package voting.strategies;

public class StrategyConfiguration {

	RankedStrategyType type;
	Integer BallotSize;
	
	public StrategyConfiguration(){
		
	}
	
	public void withDefaults(StrategyConfiguration def){
		if(type == null){
			type = def.type;
		}
		if(BallotSize == null){
			BallotSize = def.BallotSize;
		}
	}
}
