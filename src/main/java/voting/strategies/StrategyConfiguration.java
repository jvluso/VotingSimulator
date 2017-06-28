package voting.strategies;

public class StrategyConfiguration {

	RankedStrategyType type;
	Integer ballotSize;
	
	public StrategyConfiguration(){
		
	}
	
	public void withDefaults(StrategyConfiguration def){
		if(type == null){
			type = def.type;
		}
		if(ballotSize == null){
			ballotSize = def.ballotSize;
		}
	}
}
