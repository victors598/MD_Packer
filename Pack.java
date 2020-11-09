import java.util.ArrayList;

public class Pack {
    /**This is a class that implements the Pack model for this system. */

    private static int nextId = 0;
    private int packId;
    private int space; //Tracks the number of pieces that can still fit in the pack.
    private float weightLeft; //Tracks the number of weight that can still fit into the pack.
    private float maxWeight; //Stores the absolute maximum weight
    private int packSize;
    private ArrayList<Item> Items;

    public Pack(int maxPieces, float maxWeight){ //Constructor.

        //Give each pack its own unique ID
        this.packId = nextId;
        nextId++;

        //Set the limits of each pack.
        this.space = maxPieces;
        this.weightLeft = maxWeight;

        //Stores the value of the limit
        this.maxWeight = maxWeight;

        //Initializes the length of the pack to zero
        packSize = 0;

        Items = new ArrayList<Item>();
    }


    public int add(Item item){
        /**
         * A method that adds items to a pack. If the pack has enough space and the weight limit is not reached then
         * it will add the item to the pack and return 0 (i.e. 0 = successfully added item and there is space left).
         * If the pack does not have enough space, it adds enough of an item until it is full and then returns -1,
         * indicating that it added some of the item but not all of it, and a new pack needs to be made.
         */

        float totalWeight = item.getQty() * item.getWeight();

        boolean enoughSpace = item.getQty() <= this.space;
        boolean enoughWeight = totalWeight <= this.weightLeft;

        int returnVal; //Outputs whether a new pack is needed or not.

        if(enoughSpace && enoughWeight){
            Items.add(new Item(item.getId(), item.getLen(), item.getQty(), item.getWeight())); // Clone the item into the list.
            this.space -= item.getQty();
            this.weightLeft -= totalWeight;
            returnVal = 0;

        } else {
            //Find the quantity of items the pack can handle based on weight
            int qtyPerWeight = (int)(weightLeft / item.getWeight());

            //Get the minimum amount of items that could be added to the pack before it becomes full.
            int toAdd = Math.min(this.space, qtyPerWeight);
            returnVal = -1;

            if(toAdd == 0){ //If there is nothing to add, then don't add anything.
                return returnVal;
            }

            //Add some quantity of the items to the pack, and then update the item to show that some of the item
            //has been put into the pack.
            Items.add(new Item(item.getId(), item.getLen(), toAdd, item.getWeight())); // Clone the item into the list.
            this.space -= toAdd;
            this.weightLeft -= toAdd*item.getWeight();
            item.remove(toAdd);

        }

        updateSize(item);

        return returnVal;
    }

    
    private void updateSize(Item item){
        //Updates the size of the pack each time an item is added that is longer than the current size of the pack.
        if(item.getLen() > this.packSize){
            packSize = item.getLen();
        }
    }


    //=======================================Getters and Setters=====================================//
    public int remainingSpace(){
        return this.space;
    }

    public float remainingWeight(){
        return this.weightLeft;
    }

    public int getPackId(){
        return this.packId;
    }

    public ArrayList<Item> getItems(){
        return this.Items;
    }

    public int getPackSize(){
        return this.packSize;
    }

    public float getPackWeight(){
        return maxWeight-weightLeft;
    }
}