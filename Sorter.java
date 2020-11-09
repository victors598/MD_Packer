import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Sorter {
//Originally used this class to handle packing, however, I decided to move it into the Packer class.
    private ArrayList<Item> ItemList;
    private ArrayList<Pack> PackList;
    private Sort sortOrd;
    private int maxPieces;
    private float maxWeight;

    private enum Sort{
        NATURAL, //Sort the data as it comes in
        SHORT_TO_LONG, //Sort the data from shortest to longest
        LONG_TO_SHORT //Sort the data from longest to shortest.
    
    }

    public Sorter(Reader rdr){
        ItemList = new ArrayList<Item>();
        PackList = new ArrayList<Pack>();
        getData(rdr);
        sort();
        pack();
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
    

    private void sort(){
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

    private void pack(){
        Iterator<Item> it = ItemList.iterator();
        Pack pck = createPack();

        while(it.hasNext()){
            Item item = it.next();
            
            while(pck.add(item) == -1){
                pck = createPack();
            }
        }
        
    }

}