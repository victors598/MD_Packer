import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Reader {
    private String fileName;
    private ArrayList<Item> ItemList;
    private String sortOrd;
    private int maxPieces;
    private float maxWeight;

    public Reader(String fileName){ //Constructor.
        this.fileName = fileName;
        ItemList = new ArrayList<Item>();
        read();
    }

    private void parse(String line){
        /**Stores the requirements defined in the header of the file.*/

        String[] reqs = line.split(",", 3); //Get the 3 requirements.

        this.sortOrd = reqs[0];
        this.maxPieces = Integer.parseInt(reqs[1]);
        this.maxWeight = Float.parseFloat(reqs[2]);
    }

    private void read(){
        //Read in data from the file.
        try {
            File myObj = new File(this.fileName);
            Scanner myReader = new Scanner(myObj);

            String data = myReader.nextLine(); //Read in the header
            parse(data); //Store the data in the header.
    
            while (myReader.hasNextLine()) { //Need to create a condition where it rejects a line if it is blank.
                data = myReader.nextLine();

                if(data.equals("")){ //If we encounter a blank line then we have reached the end of the file.
                    break;
                } else {
                    ItemList.add(new Item(data)); //Add the data from a file into a list.
                }
            }
            myReader.close(); //Close the file.
    
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
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

    public ArrayList<Item> getItemList(){
        return this.ItemList;
    }
}