package byog.Core;

import byog.TileEngine.TETile;
import java.util.Random;


public class Room {
    private static int MAX_WIDTH = 10;
    private static int MAX_HEIGHT = 10;

    final int width;
    final int height;
    final int xDoorPos; // position of doors on the Room
    final int yDoorPos;

    public Room(){ // the default offset is (0,0), the default size is also 0;
        this.width = 0;
        this.height = 0;
        System.out.println("Just initialized the width and height, which are: " + this.width + " , " + this.height + ".");
        this.xDoorPos = 0;
        this.yDoorPos = 0;
        System.out.println("Just initialized the xDoorPos and yDoorPos, which are: " + this.xDoorPos + " , " + this.yDoorPos + ".\n");
        this.checkParaValidity();
    }
    public Room(int width, int height, int xDoorPos, int yDoorPos){
        this.width = width;
        this.height = height;
        System.out.println("Just initialized the width and height, which are: " + this.width + " , " + this.height + ".");
        this.xDoorPos = xDoorPos;
        this.yDoorPos = yDoorPos;
        System.out.println("Just initialized the xDoorPos and yDoorPos, which are: " + this.xDoorPos + " , " + this.yDoorPos + ".\n");
        this.checkParaValidity();
    }
    public Room(Random random){
        this.width = 1 + RandomUtils.uniform(random, MAX_WIDTH);
        this.height = 1 +  RandomUtils.uniform(random, MAX_HEIGHT);
        System.out.println("Just initialized the width and height, which are: " + this.width + " , " + this.height + ".");
        // TODO: randomly generate the position of doors at margin.
        xDoorPos = RandomUtils.uniform(random, this.width);
        if(xDoorPos == 0 || xDoorPos == this.width-1){
            yDoorPos = RandomUtils.uniform(random, this.height);
        }else{
            yDoorPos = (this.height-1)*RandomUtils.uniform(random, 2);
        }
        System.out.println("Just initialized the xDoorPos and yDoorPos, which are: " + this.xDoorPos + " , " + this.yDoorPos + ".\n");
        this.checkParaValidity();
    }
    public Room(Random random, int maxWidth, int maxHeight){
        this.width = 1 + RandomUtils.uniform(random, maxWidth);
        this.height = 1 +  RandomUtils.uniform(random, maxHeight);
        System.out.println("Just initialized the width and height, which are: " + this.width + " , " + this.height + ".");
        // TODO: randomly generate the position of doors at margin.
        xDoorPos = RandomUtils.uniform(random, this.width);
        if(xDoorPos == 0 || xDoorPos == this.width-1){
            yDoorPos = RandomUtils.uniform(random, this.height);
        }else{
            yDoorPos = (this.height-1)*RandomUtils.uniform(random, 2);
        }
        System.out.println("Just initialized the xDoorPos and yDoorPos, which are: " + this.xDoorPos + " , " + this.yDoorPos + ".\n");
        this.checkParaValidity();
    }
    public void printInfo(){
        System.out.println("width: " + this.width + " height: " + this.height + " xDoorPos: " + this.xDoorPos + " yDoorPos； " + this.yDoorPos);
    }
    private void checkParaValidity(){
        if(this.width < 0 || this.height < 0){
            if( (this.xDoorPos < 0 || this.xDoorPos > MAX_WIDTH) || (this.yDoorPos < 0 || this.yDoorPos > MAX_HEIGHT) ){
                this.printInfo();
                throw new IllegalStateException("The sizes and offsets should not be negative or out of boundary!");
            }else{
                this.printInfo();
                throw new IllegalStateException("The sizes should not be negative1");
            }
        } else if(this.width > MAX_WIDTH || this.height > MAX_HEIGHT){
            this.printInfo();
            throw new IllegalStateException("The sizes are out of boundary!");
        }
    }


}
