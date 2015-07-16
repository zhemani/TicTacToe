//Class to create a DictEntry object
public class DictEntry {
//Create instance variable for config and score 
	private String configX;
	private int scoreX;
	
	public DictEntry(String config, int score) {
		configX = config;
		scoreX = score;
	}
//Getter method for config
	public String getConfig() {
		return configX;
	}
//Getter method for score
	public int getScore( ) {
		return scoreX;
	}	
}//End of class
