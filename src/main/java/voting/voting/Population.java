package voting.voting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population {
	
	private List<Person> people;
	
	public Population(int size,int issues){
    	people = new ArrayList<Person>(size);
    	Random rand = new Random();
    	for(int i=0;i<size;i++){
    		people.add(i, new Person(issues,rand));
    	}
		
	}
	
	public Population(List<Person> p){
		people = new ArrayList<Person>(p);
	}
	
	public List<Person> getPeople(){
		return people;
	}

	public int majorityVote(int i){
		int sup =0;
		for(Person p:people){
			if(p.getOpinions()[i]>0){
				sup ++;
			}
			if(p.getOpinions()[i]<0){
				sup --;
			}
		}
		return Integer.signum(sup);
	}
}
