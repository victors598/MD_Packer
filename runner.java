import java.util.ArrayList;
import java.util.Iterator;

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
    pckr.sort();
    pckr.pack();
	  printResults(pckr);
  }

  public static void printResults(Packer pckr){
    ArrayList<Pack> pl = pckr.getPackList();

    Iterator<Pack> plit = pl.iterator();

    while(plit.hasNext()){
      Pack pck = plit.next();

      System.out.println("Pack Number: " + pck.getPackId());
      printItems(pck.getItems());

      System.out.println("Pack Length: " + pck.getPackSize() + ", Pack Weight: " + pck.getPackWeight());
      System.out.println("");

    }
    
  }

  public static void printItems(ArrayList<Item> items){
    Iterator<Item> it = items.iterator();

    while(it.hasNext()){
      Item item = it.next();

      System.out.println(item.getId() + "," + item.getLen() + "," + item.getQty() + "," + item.getWeight());

    }

  }
}

