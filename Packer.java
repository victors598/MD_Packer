import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class Packer {
/**This class contains the control flow for sorting the items in to the packs.*/

    private String fileName;
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

        this.fileName = fileName;
        ItemList = new ArrayList<Item>();

    }

    public void read(){
        /**Reads in the data from the .txt file and appends the items into an item list.*/
        
        try {
            File myObj = new File(this.fileName);
            Scanner myReader = new Scanner(myObj);

            String data = myReader.nextLine(); //Read in the header
            parse(data); //Store the data in the header.
            System.out.println(data); //Print out the header.
    
            while (myReader.hasNextLine()) { //Need to create a condition where it rejects a line if it is blank.
                data = myReader.nextLine();
                ItemList.add(new Item(data)); //Add the data from a file into a list.
                
            }
            myReader.close(); //Close the file.
    
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    private void parse(String line){
        /**Stores the requirements defined in the header of the file.*/

        String[] reqs = line.split(",", 3); //Get the 3 requirements.

        this.sortOrd = reqs[0];
        this.maxPieces = Integer.parseInt(reqs[1]);
        this.maxWeight = Float.parseFloat(reqs[2]);

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
    

    //===============================================Getters and Setters=======================================================//
    public String getSortOrder(){
        return this.sortOrd;
    }
    
    public int getMaxPieces(){
        return this.maxPieces;
    }

    public float getMaxWeight(){
        return this.maxWeight;
    }

}