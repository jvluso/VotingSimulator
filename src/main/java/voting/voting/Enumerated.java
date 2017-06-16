package voting.voting;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Enumerated<T> implements Iterator<List<T>> {

	private int s;
	private List<T> l;
	private Enumerated<T> inside;
	private Iterator<T> i;
	private T currentT;
	
	
	public Enumerated(int size,List<T> list){
		l=list;
		s=size;
		i=l.iterator();
		if(s>1 && i.hasNext()){
			itterate();
		}
	}
	
	@Override
	public boolean hasNext() {
		if(s>1){
			if(inside.hasNext()){
				return true;
			}else{
				return l.indexOf(currentT)+s<l.size();
			}
		}else{
			return i.hasNext();
		}
	}

	@Override
	public List<T> next() {
		if(s>1){
			if(!inside.hasNext()){
				itterate();
			}
			List<T> cons = inside.next();
			cons.add(currentT);
			return cons;
		}else{
			List<T> cons = new LinkedList<T>();
			cons.add(i.next());
			return cons;
		}
	}

	private void itterate(){
		currentT=i.next();
		inside=new Enumerated<T>(s-1,l.subList(l.indexOf(currentT)+1, l.size()));
		
	}


}
