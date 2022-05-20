package byog.Core;

import byog.TileEngine.TETile;
import java.util.Random;


public class Room {
    private static int MAX_WIDTH = 13;
    private static int MAX_HEIGHT = 13;

    final int width;
    final int height;
    final int xDoorPos; // position of doors on the Room
    final int yDoorPos;

    public Room(){ // the default offset is (0,0), the default size is also 0;
        this.width = 0;
        this.height = 0;
        System.out.println("Just initialized the width and height, which are: " + this.width + " , " + this.height + ".");
        this.xDoorPos = 1;
        this.yDoorPos = 1;
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
        this.width = 5 + RandomUtils.uniform(random, MAX_WIDTH-5);
        this.height = 5 +  RandomUtils.uniform(random, MAX_HEIGHT-5);
        System.out.println("Just initialized the width and height, which are: " + this.width + " , " + this.height + ".");

        // to store valid value for xDoorPos/ yDoorPos (because they can't be reassigned in constructors)
        int xPosBuff = 1;
        int yPosBuff = 1;

        while(xPosBuff == 1 || xPosBuff == this.width - 2){ // to prevent xDoorPos be assigned as 1 or width - 2
            xPosBuff = RandomUtils.uniform(random, this.width);
        }
        // get the xDoorPos
        this.xDoorPos = xPosBuff;
        // get the yDoorPos
        if(this.xDoorPos == 0 || this.xDoorPos == this.width-1){
            while(yPosBuff <= 1 || yPosBuff >= this.height - 2){ // to prevent yDoorPos be assigned as 1 or width - 2
                yPosBuff = RandomUtils.uniform(random, this.height);
            }
        }else{
            yPosBuff = (this.height-1)*RandomUtils.uniform(random, 2);
        }
        this.yDoorPos = yPosBuff;

        System.out.println("Just initialized the xDoorPos and yDoorPos, which are: " + this.xDoorPos + " , " + this.yDoorPos + ".\n");
        this.checkParaValidity();
    }
    public Room(Random random, int maxWidth, int maxHeight){
        this.width = 5 + RandomUtils.uniform(random, maxWidth-5);
        this.height = 5 +  RandomUtils.uniform(random, maxHeight-5);
        System.out.println("Just initialized the width and height, which are: " + this.width + " , " + this.height + ".");

        // to store valid value for xDoorPos/ yDoorPos (because they can't be reassigned in constructors)
        int xPosBuff = 1;
        int yPosBuff = 1;

        while(xPosBuff == 1 || xPosBuff == this.width - 2){ // to prevent xDoorPos be assigned as 1 or width - 2
            xPosBuff = RandomUtils.uniform(random, this.width);
        }
        // get the xDoorPos
        this.xDoorPos = xPosBuff;
        // get the yDoorPos
        if(this.xDoorPos == 0 || this.xDoorPos == this.width-1){
            while(yPosBuff <= 1 || yPosBuff >= this.height - 2){ // to prevent yDoorPos be assigned as 1 or width - 2
                yPosBuff = RandomUtils.uniform(random, this.height);
            }
        }else{
            yPosBuff = (this.height-1)*RandomUtils.uniform(random, 2);
        }
        this.yDoorPos = yPosBuff;

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
        } else if(this.width > (MAX_WIDTH+3) || this.height > (MAX_HEIGHT+3)){
            this.printInfo();
            throw new IllegalStateException("The sizes are out of boundary!");
        }
    }


}
