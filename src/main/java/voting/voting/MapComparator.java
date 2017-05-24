package voting.voting;

import java.util.Comparator;
import java.util.Map;

public class MapComparator<T> implements Comparator<T> {

	private Map<T,Integer> map;
	
	public MapComparator(Map<T,Integer> m){
		map=m;
	}
	
	@Override
	public int compare(T o1, T o2) {
		if(map.get(o1)==null){
			if(map.get(o2)==null){
				return 0;
			}else{
				return map.get(o2);
			}
		}else{
			if(map.get(o2)==null){
				return map.get(o1);
			}else{
				return map.get(o1).compareTo(map.get(o2));
			}
		}
	}


}
