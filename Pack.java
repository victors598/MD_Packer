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
        //First you need the space to actually put the stuff into the pack, then you need to know
        //if you won't go over the limit.

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

            //Get the minimum amount of items that could be added.
            int toAdd = Math.min(this.space, qtyPerWeight);
            returnVal = -1;

            if(toAdd == 0){
                return returnVal;
            }

            Items.add(new Item(item.getId(), item.getLen(), toAdd, item.getWeight())); // Clone the item into the list.
            this.space -= toAdd;
            this.weightLeft -= toAdd*item.getWeight();
            item.remove(toAdd);

        }

        updateSize(item);

        return returnVal;
    }

    
    private void updateSize(Item item){
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