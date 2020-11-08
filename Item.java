
public class Item{
/*This is a class that models the items that will be input into the program. */

	private int id;
	private float len; //Length (mm)
	private int qty; 
	private float weight; //(kg, the weight of one item)

	public Item(String fromFile){ //Constructor
		parse(fromFile);

	}

	private void parse(String line){
	/*Parses out the data coming from the line in a file into the appropriate data type and variable*/
		String[] props = line.split(",",4); //Limit of 4 is set because an Item only has 4 pieces of data.
		
		this.id = Integer.parseInt(props[0]); 
		this.len = Float.parseFloat(props[1]);
		this.qty = Integer.parseInt(props[2]);
		this.weight = Float.parseFloat(props[3]);
	}
	
	//========================================================Getters and Setters===================================================//
	public int getId(){
		return this.id;	
	}

	public float getLen(){
		return this.len;	
	}

	public int getQty(){
		return this.qty;	
	}

	public float getWeight(){
		return this.weight;	
	}

}
