import java.util.ArrayList;
import java.util.Iterator;

public class Packer {
/**This class contains the control flow for sorting the items in to the packs.*/

    private ArrayList<Item> ItemList;
    private String sortOrd;
    private int maxPieces;
    private float maxWeight;

    private enum Sort{
        NATURAL, //Sort the data as it comes in
        SHORT_LONG, //Sort the data from shortest to longest
        LONG_SHORT //Sort the data from longest to shortest.

    }

    public Packer(String fileName){ //Constructor
        Reader rdr = new Reader(fileName); //Read in data from the .txt file
        getData(rdr);

    }

    private void getData(Reader rdr){
        //Retrieves the data found by  the reader class.
        this.ItemList = rdr.getItemList();
        this.sortOrd = rdr.getSortOrder();
        this.maxPieces = rdr.getMaxPieces();
        this.maxWeight = rdr.getMaxWeight();

    }

    public void printItems(){
        /**Print out the items that were read in from the file 
         * This method is used for debugging.*/
        Iterator<Item> it = ItemList.iterator();

        while(it.hasNext()){
            Item item = it.next();

            System.out.println("ID = " + item.getId());
            System.out.println("Len = " + item.getLen());
            System.out.println("Quantity = " + item.getQty());
            System.out.println("Weight = " + item.getWeight());
        }
    }

    public void pack(){
    }
    

}