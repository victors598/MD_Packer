import java.util.ArrayList;

public class Pack {
    /**This is a class that implements the Pack model for this system. */

    private static int nextId = 0;
    private int packId;
    private int space; //Tracks the number of pieces that can still fit in the pack.
    private float weightLeft; //Tracks the number of weight that can still fit into the pack.
    private ArrayList<Item> Items;

    public Pack(int maxPieces, float maxWeight){ //Constructor.

        //Give each pack its own unique ID
        this.packId = nextId;
        nextId++;

        //Set the limits of each pack.
        this.space = maxPieces;
        this.weightLeft = maxWeight;

        Items = new ArrayList<Item>();
    }


    public int add(Item item){
        //First you need the space to actually put the stuff into the pack, then you need to know
        //if you won't go over the limit.

        float totalWeight = item.getQty() * item.getWeight();

        boolean enoughSpace = item.getQty() < this.space;
        boolean enoughWeight = totalWeight < this.weightLeft;

        if(enoughSpace && enoughWeight){
            Items.add(item);
            this.space -= item.getQty();
            this.weightLeft -= totalWeight;

            return 0; //return how many items were added.

        } else {
            //Find the quantity of items the pack can handle based on weight
            int qtyPerWeight = (int)(weightLeft / item.getWeight());

            //Get the minimum amount of items that could be added.
            int toAdd = Math.min(this.space, qtyPerWeight);

            Items.add(item);
            this.space -= toAdd;
            this.weightLeft -= toAdd*item.getWeight();
            item.remove(toAdd);

            return -1;
        }
        //Four scenarios:
        //1 = enough space and enough weight
        //2 = enough space and not enough weight
        //3 = not enough space and enough weight
        //4 = not enough space and weight 
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
}