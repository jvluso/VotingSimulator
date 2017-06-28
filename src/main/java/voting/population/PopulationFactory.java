package voting.population;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import com.google.gson.Gson;

public class PopulationFactory {
	

	
	public static PopulationType getPopulation() throws FileNotFoundException{
		Gson gson = new Gson();

        Reader reader = new FileReader("src/main/resources/Population.json");

    	return gson.fromJson(reader, PopulationType.class);
	}
}
