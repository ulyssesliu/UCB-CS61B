package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.ArrayList;
import java.util.Random;

public class Dungeon {
    int width;
    int height;
    private TETile[][] dungeonMap; //

    private ArrayList<Room> roomList; // List to store added rooms;

    // TODO: constructors
    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        roomList = new ArrayList<Room>();

        dungeonMap = new TETile[width][height];
        // fill the dungeonMap with void block
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                dungeonMap[i][j] = Tileset.VOID;
            }
        }
    }
    
    public void addRoom(Random random){
        int[] offSet = getRandomOffset(random);
        Room newRoom = new Room(random, offSet);
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
    public void connect(){
        int listSize = this.roomList.size();
        for(int x = 0; x < listSize-1; x++){
            for(int y = x + 1; y < listSize; y++){
                connectTwoRooms(roomList.get(x),roomList.get(y));
            }
        }
    }

    private void connectTwoRooms(Room r1, Room r2){

        // get the door positions
        int x1, y1, x2, y2;
        if(r1.xDoorPos > r2.xDoorPos){
            x2 = r1.xDoorPos + r1.xOff;
            y2 = r1.yDoorPos + r1.yOff;
            x1 = r2.xDoorPos + r2.xOff;
            y1 = r2.yDoorPos + r2.yOff;
        } else if(r1.xDoorPos < r2.xDoorPos){
            x1 = r1.xDoorPos + r1.xOff;
            y1 = r1.yDoorPos + r1.yOff;
            x2 = r2.xDoorPos + r2.xOff;
            y2 = r2.yDoorPos + r2.yOff;
        } else{
            if(r1.yDoorPos == r2.yDoorPos)
                return;
            else if(r1.yDoorPos > r2.yDoorPos){
                x2 = r1.xDoorPos + r1.xOff;
                y2 = r1.yDoorPos + r1.yOff;
                x1 = r2.xDoorPos + r2.xOff;
                y1 = r2.yDoorPos + r2.yOff;
            }else {
                x1 = r1.xDoorPos + r1.xOff;
                y1 = r1.yDoorPos + r1.yOff;
                x2 = r2.xDoorPos + r2.xOff;
                y2 = r2.yDoorPos + r2.yOff;
            }
        }

        // build the hallway (not change the DOOR tiles)
        if(y2 > y1){ // type 1: right door is higher
            // fill the walls
            for(int x = x1; x <= x2+1; x++){
                for(int y = y1-1; y <= y1+1; y++){
                    if(dungeonMap[x][y] != Tileset.FLOOR || dungeonMap[x][y] != Tileset.UNLOCKED_DOOR)
                        dungeonMap[x][y] = Tileset.WALL;
                }
            }
            for(int x = x2-1; x<= x2+1; x++){
                for(int y = y1+2; y <= y2; y++){
                    if(dungeonMap[x][y] != Tileset.FLOOR || dungeonMap[x][y] != Tileset.UNLOCKED_DOOR)
                        dungeonMap[x][y] = Tileset.WALL;
                }
            }
            // fill the floors
            for(int x=x1; x<=x2; x++){
                if(dungeonMap[x][y1] != Tileset.UNLOCKED_DOOR)
                    dungeonMap[x][y1] = Tileset.FLOOR;
            }
            for(int y=y1+1; y <= y2; y++){
                if(dungeonMap[x2][y] != Tileset.UNLOCKED_DOOR)
                    dungeonMap[x2][y] = Tileset.FLOOR;
            }

        } else if(y2 == y1){ // type 2: two door same level
            // fill the walls
            for(int x = x1; x <= x2; x++){
                for(int y = y1-1; y<= y1+1; y++){
                    if(dungeonMap[x][y] != Tileset.FLOOR || dungeonMap[x][y] != Tileset.UNLOCKED_DOOR)
                        dungeonMap[x][y] = Tileset.WALL;
                }
            }

            // fill the floors
            for(int x = x1; x <= x2; x++){
                if(dungeonMap[x][y1] != Tileset.UNLOCKED_DOOR)
                    dungeonMap[x][y1] = Tileset.FLOOR;
            }


        } else{ // type 3: right door is lower

            // fill the walls
            for(int x = x1-1; x <= x2; x++){
                for(int y = y2-1; y <= y2+1; y++){
                    if(dungeonMap[x][y] != Tileset.FLOOR || dungeonMap[x][y] != Tileset.UNLOCKED_DOOR)
                        dungeonMap[x][y] = Tileset.WALL;
                }
            }
            for(int x = x1-1; x<= x1+1; x++){
                for(int y = y2+2; y <= y1; y++){
                    if(dungeonMap[x][y] != Tileset.FLOOR || dungeonMap[x][y] != Tileset.UNLOCKED_DOOR)
                        dungeonMap[x][y] = Tileset.WALL;
                }
            }

            // fill the floors
            for(int x=x1; x<=x2; x++){
                if(dungeonMap[x][y2] != Tileset.UNLOCKED_DOOR)
                    dungeonMap[x][y2] = Tileset.FLOOR;
            }
            for(int y=y2+1; y <= y1; y++){
                if(dungeonMap[x1][y] != Tileset.UNLOCKED_DOOR)
                    dungeonMap[x1][y] = Tileset.FLOOR;
            }

        }
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
