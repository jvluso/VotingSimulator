package voting.voting;

import java.util.Random;

public class Person {
	
	float[] opinions;
	
	public Person(int issues, Random rand){
		opinions = new float[issues];
		for(int i=0;i<issues;i++){
			opinions[i]=rand.nextFloat() * 2 - 1;
		}
    	float mean = this.dist(new float[issues]);

		for(int i=0;i<issues;i++){
			opinions[i]=opinions[i]/mean;
		}
	}
	
	public float[] getOpinions(){
		return opinions;
	}

	public float dist(float[] p){
		float d=0;
		for(int i=0;i<opinions.length;i++){
			d+=(opinions[i]-p[i])*(opinions[i]-p[i]);
		}
		return (float) Math.sqrt(d);
	}

}
