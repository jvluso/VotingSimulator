package voting.systems;

public class ElectionConfiguration {

	Integer districts;
	Integer candidatePool;
	Integer size;
	ElectionConfiguration system;
	Float delta;
	ElectionType type;
	
	public ElectionConfiguration(){
		
	}
	
	public void withDefaults(ElectionConfiguration def){
		if(districts == null){
			districts = def.districts;
		}
		if(candidatePool == null){
			candidatePool = def.candidatePool;
		}
		if(size == null){
			size = def.size;
		}
		if(delta == null){
			delta = def.delta;
		}
	}
}
