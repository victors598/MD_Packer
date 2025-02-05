import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;

public class Packer {
/**This class contains the control flow for sorting the items in to the packs.*/

    private ArrayList<Item> ItemList;
    private ArrayList<Pack> PackList;
    private Sort sortOrd;
    private int maxPieces;
    private float maxWeight;

    public Packer(String fileName){ //Constructor
        Reader rdr = new Reader(fileName); //Read in data from the .txt file

        ItemList = new ArrayList<Item>();
        PackList = new ArrayList<Pack>();
        getData(rdr);
    }

    private enum Sort{
        NATURAL, //Sort the data as it comes in
        SHORT_TO_LONG, //Sort the data from shortest to longest
        LONG_TO_SHORT //Sort the data from longest to shortest.
    
    }

    private void getData(Reader rdr){
        //Retrieves the data found by  the reader class.
        this.ItemList = rdr.getItemList();
        this.sortOrd = convert(rdr.getSortOrder());
        this.maxPieces = rdr.getMaxPieces();
        this.maxWeight = rdr.getMaxWeight();

    }

    private Sort convert(String so){
        //Converts the string representation of the sorting order in the text file to an ENUM.
        try{
            Sort temp = Sort.valueOf(so.toUpperCase());
            return temp;
        } catch(IllegalArgumentException e){
            System.out.println("An undefined sorting method was given!!!");
            return null;
        }
    }
    

    public void sort(){
        //Sorts the list of items based on the sorting order set in the text file.
        switch(this.sortOrd){
            case NATURAL:
                //No sorting needs to be done
            break;

            case SHORT_TO_LONG:
                //Sort in ascending order, i.e. from shortest to longest
                Collections.sort(ItemList);
            break;

            case LONG_TO_SHORT:
                //Sort in descending order, i.e. from longest to shortest
                Collections.sort(ItemList, Collections.reverseOrder());
            break;

            default:
                //Safety
            break;
        }
    }

    private Pack createPack(){
        //Add a new pack to the list and then return a reference to this new pack.
        PackList.add(new Pack(this.maxPieces, this.maxWeight));
        return  PackList.get(PackList.size() - 1);
    }

    public void pack(){
        /**
         * This is a method to create and add items into packs. The method adds items to a pack until that pack is full.
         * If the pack is full, then new packs are created until it has everything added to it.
         * If a pack is not full it will be reused in the next iteration.
         */
        Iterator<Item> it = ItemList.iterator();
        Pack pck = createPack();

        while(it.hasNext()){
            Item item = it.next();

            while(pck.add(item) == -1){
                pck = createPack();
            }
        }
        
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

    //=============================================Getters and Setters=============================================//
    public ArrayList<Pack> getPackList() {
        return PackList;
    }
    

}