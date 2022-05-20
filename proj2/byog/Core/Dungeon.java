package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.ArrayList;
import java.util.Random;

public class Dungeon {
    int width;
    int height;
    boolean isConnected;
    private TETile[][] dungeonMap; //

    private ArrayList<Room> roomList; // List to store added rooms;

    // TODO: constructors
    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.isConnected = false;
        roomList = new ArrayList<Room>();

        dungeonMap = new TETile[width][height];
        // fill the dungeonMap with void block
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                dungeonMap[i][j] = Tileset.VOID;
            }
        }
    }
    
    public void addRoom(Room newRoom, Random random){// TODO(done): Add a new room to the dungeonMap (after it passes the test.)
        int[] offSet = getRandomOffset(random);
        System.out.println("Offsets: xOff: " + offSet[0] + " yOff: " + offSet[1]);
        if(isConflict(newRoom, offSet)){
            return;
        }
        addRoomToMap(newRoom, offSet);
        addRoomToList(newRoom);
    }

    public void renderDungeon(TERenderer renderer){
        if(this.dungeonMap == null){
            throw new IllegalStateException("The worldMap is Null!");
        }
        renderer.renderFrame(this.dungeonMap);
    }

    // TODO: connect the discrete distributed Rooms in dungeonMap.
    private void connect(){
    }

    // TODO: sort the rooms in the list by their xOff
    private void sortRooms(){

    }

    
    private boolean isConflict(Room newRoom, int[] offSet){// TODO (done): Test whether the Room can fit into the given map (2d Tile array)
        return !((this.width > newRoom.width + offSet[0]) && (this.height > newRoom.height + offSet[1]));
    }
    private void addRoomToMap(Room newRoom, int[] offSet){
        // fill the floors
        for(int xOff = 1; xOff < newRoom.width - 1; xOff++){
            for(int yOff = 1; yOff < newRoom.height - 1; yOff++){
                this.dungeonMap[xOff + offSet[0]][yOff + offSet[1]] = Tileset.FLOOR;
            }
        }

        // fill the walls
        // the upper and lower walls
        for(int xOff = 0; xOff < newRoom.width; xOff++){
            if(this.dungeonMap[xOff + offSet[0]][offSet[1]] == Tileset.VOID) // use if-control to merge the rooms (not overlap)
                this.dungeonMap[xOff + offSet[0]][offSet[1]] = Tileset.WALL; // lower wall
            if(this.dungeonMap[xOff + offSet[0]][offSet[1] + newRoom.height - 1] == Tileset.VOID)
                this.dungeonMap[xOff + offSet[0]][offSet[1] + newRoom.height - 1] = Tileset.WALL; // upper wall

        }
        // the left and right walls
        for(int yOff = 1; yOff < newRoom.height - 1; yOff++){
            if(this.dungeonMap[offSet[0]][yOff + offSet[1]] == Tileset.VOID)
                this.dungeonMap[offSet[0]][yOff + offSet[1]] = Tileset.WALL; // left wall
            if(this.dungeonMap[offSet[0] + newRoom.width - 1][yOff + offSet[1]] == Tileset.VOID)
                this.dungeonMap[offSet[0] + newRoom.width - 1][yOff + offSet[1]] = Tileset.WALL; // right wall
        }

        // fill the doors
        if(this.dungeonMap[newRoom.xDoorPos + offSet[0]][newRoom.yDoorPos + offSet[1]] == Tileset.WALL)
            this.dungeonMap[newRoom.xDoorPos + offSet[0]][newRoom.yDoorPos + offSet[1]] = Tileset.UNLOCKED_DOOR;
    }
    private void addRoomToList(Room newRoom){
        this.roomList.add(newRoom);
    }
    
    private int[] getRandomOffset(Random random){ // get random offsets xOff and yOff
        int[] offSet = new int[2];
        offSet[0] = (int)(this.width/5) + RandomUtils.uniform(random, (int)(3*this.width/5));
        offSet[1] = (int)(this.height/5) + RandomUtils.uniform(random, (int)(3*this.height/5));
        return offSet;
    }

}
