public class runner {
/*
This is the root class of this program where you can input what file should be used to read in the 
values for items.
The values are then passed to the packing calculator to segment each item into a pack based on the 
requirements for each pack.
The input file should have the requirements listed as the header. 
*/
  public static void main(String[] args) {
	  Packer pckr = new Packer("items.txt");
	  pckr.read();
	  //pckr.printItems();
  }
}

