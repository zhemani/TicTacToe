import java.util.LinkedList;
//Dictionary class to use a hash-table with separate chaining
public class Dictionary implements DictionaryADT {
// Declaring instance variables for a object of linked lists type of DictEntry
	//Instance variables for total and size as they are used within this class
	private LinkedList<DictEntry>[] dictArray;
	private int total;
	private int size;

	public Dictionary (int size) {
		total = 0;
		this.size = size;
	    dictArray = new LinkedList[size];
	    for (int i = 0; i < dictArray.length; i++) 
	    	//Ensuring there are linkedList element positions within the array
	       dictArray[i] = new LinkedList<DictEntry>(); 
	}
    
	//Hashfunction method to produce the key required to store within the hash table
	private int hashFunction (String abc, int a) {
		int hashKeyValue = (int)abc.charAt(0);
		for (int i = 1; i < abc.length(); i++) {
			//Hash key formula. int a is a value I can pick
			hashKeyValue = (hashKeyValue*a + (int)abc.charAt(i)) % size;
		}
		return hashKeyValue;
	}
	
	//Insert method to put DictEntry pairs into array 
	public int insert(DictEntry pair) throws DictionaryException {
		//Statements to get the key for the configuration 
		String hash  = pair.getConfig();
		int key = hashFunction(hash,33);
		//If the location determined by the key is empty then storing the pair in that location
	   if (dictArray[key].isEmpty()) {
		dictArray[key].add(pair);
		total++;
		return 0;
	   }
	   else {
		   //If it is not empty then increasing the size of the linked list in occurdance to that location in the array 
		 int i =1;
		 while(dictArray[key].size() != i && !pair.getConfig().equals(dictArray[key].get(i-1).getConfig())){
		 i++;
		 }
		 //If that configuration is already in the array, throw this
		 if (pair.getConfig().equals(dictArray[key].get(i-1).getConfig())) {
			 throw new DictionaryException("This configuration already exists");
		 }    
		 else {
			 //If not in the array then add it to the end of the list
			 dictArray[key].add(pair);
			 total++;
			 return 1;		 
	     }
	   }
	}
				

	public void remove(String config) throws DictionaryException {
		//Compute the key using the hashfunction 
		int key = hashFunction(config,33);
		//Ensure that the location with the key in the array is not empty- Something to remove
		if (!dictArray[key].isEmpty()) {
			int i = 1;
		    while(dictArray[key].size() != i && !config.equals(dictArray[key].get(i-1).getConfig())) {
			i++;
		    }
		    //Matches to the key and config and then removes using a linked list operation
		    if(config.equals(dictArray[key].get(i-1).getConfig())) {
			  dictArray[key].remove(i-1);
			  total--;
		    }
		    //Exceptions are thrown if the config isn't in the array to remove
		    else { throw new DictionaryException("Configuration does not exist"); }
		}
		else { throw new DictionaryException("Configuration does not exist"); }
	}
		
	//Find method to return the score of the config or -1 if not found
	public int find(String config) {
		//Compute the key of the config
		int key = hashFunction(config,33);
		//Ensure the location with the key is not empty 
		if(!dictArray[key].isEmpty()) {
			int i = 1;
			while(dictArray[key].size() != i && !config.equals(dictArray[key].get(i-1).getConfig())) {
				i++;
			}
			//Matches the config at the given key
			if(config.equals(dictArray[key].get(i-1).getConfig())) {
				//Returns the associated score
				return dictArray[key].get(i-1).getScore();
			}
			//If the config isn't found it returns -1
			else { return -1; }
		}
		else { return -1; }
	}

	//Returns the total amount of elements in the hash-table. Total is incremented or decremented throughout 
	public int numElements() {
		return total;
    }
}//End of class




		
	
			


