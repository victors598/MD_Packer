
public class Item implements Comparable<Item>{
/*This is a class that models the items that will be input into the program. */

	private int id;
	private int len; //Length (mm)
	private int qty; 
	private float weight; //(kg, the weight of one item)

	public Item(String fromFile){ //Constructor
		parse(fromFile);

	}

	public Item(int id, int len, int qty, float weight){ //Another Constructor, mainly used to clone an exiting object.
		this.id = id;
		this.len = len;
		this.qty = qty;
		this.weight = weight;
	}

	private void parse(String line){
	/*Parses out the data coming from the line in a file into the appropriate data type and variable*/
		String[] props = line.split(",",4); //Limit of 4 is set because an Item only has 4 pieces of data.
		
		this.id = Integer.parseInt(props[0]); 
		this.len = Integer.parseInt(props[1]);
		this.qty = Integer.parseInt(props[2]);
		this.weight = Float.parseFloat(props[3]);
	}

	@Override
	public int compareTo(Item o) {
		/*Implements the comapreTo method so that a list of items can be sorted using the Collections.sort() method.
		Without having to change all of my int to the deprecated Integer class.

		Java's CompareTo Method Explanation: The java.lang.Integer.compareTo() method compares two Integer objects numerically. 
		This method returns the value 0 if this Integer is equal to the argument Integer, a value less than 0 if this Integer 
		is numerically less than the argument Integer and a value greater than 0 if this Integer is numerically greater than the argument Integer.
		
		*****Reference: https://www.tutorialspoint.com/java-integer-compareto-method*****
		
		*/
		if(this.getLen() > o.getLen()){
			//If the length of this item is longer than another items
			return 1;
		} else if(this.getLen() == o.getLen()){
			//If the lengths of the two items are the same.
			return 0;
		} else {
			//If the length of this item is less than the length of the other item.
			return -1;
		}
	}

	public void remove(int toRemove){
		//Remove a given quantity of a particular item.
		this.qty -= toRemove;
	}
	
	//========================================================Getters and Setters===================================================//
	public int getId(){
		return this.id;	
	}

	public int getLen(){
		return this.len;	
	}

	public int getQty(){
		return this.qty;	
	}

	public float getWeight(){
		return this.weight;	
	}

}
