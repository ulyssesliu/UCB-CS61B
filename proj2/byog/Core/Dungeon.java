package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;

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
        dungeonMap = new TETile[width][height];
        // fill the dungeonMap with void block
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                dungeonMap[i][j] = Tileset.VOID;
            }
        }
    }

    // TODO: Test whether the Room can fit into the given map (2d Tile array)
    public boolean isConflict(Room newRoom){
        return !((this.width > newRoom.width + newRoom.xDoorPos) && (this.height > newRoom.height + newRoom.yDoorPos));
    }

    // TODO(done): Add a new room to the dungeonMap (after it passes the test.)
    public void addRoom(Room newRoom){
        if(isConflict(newRoom)){
            return;
        }
        addRoomToMap(newRoom);
        addRoomToList(newRoom);
    }

    // TODO: connect the discrete distributed Rooms in dungeonMap.
    private void connect(){

    }

    // TODO: sort the rooms in the list by their xOff
    private void sortRooms(){

    }

    private void addRoomToMap(Room newRoom){
        // fill the floors
        for(int xOff = 1; xOff < newRoom.width - 1; xOff++){
            for(int yOff = 1; yOff < newRoom.height - 1; yOff++){
                this.dungeonMap[xOff + newRoom.xDoorPos][yOff + newRoom.yDoorPos] = Tileset.FLOOR;
            }
        }

        // fill the walls
        // the upper and lower walls
        for(int xOff = 0; xOff < newRoom.width; xOff++){
            this.dungeonMap[xOff + newRoom.xDoorPos][newRoom.yDoorPos] = Tileset.WALL; // lower wall
            this.dungeonMap[xOff + newRoom.xDoorPos][newRoom.yDoorPos + newRoom.height - 1] = Tileset.WALL; // upper wall
        }
        // the left and right walls
        for(int yOff = 1; yOff < newRoom.height - 1; yOff++){
            this.dungeonMap[newRoom.xDoorPos][yOff + newRoom.yDoorPos] = Tileset.WALL; // left wall
            this.dungeonMap[newRoom.xDoorPos + newRoom.width - 1][yOff + newRoom.yDoorPos] = Tileset.WALL; // right wall
        }
    }
    private void addRoomToList(Room newRoom){
        this.roomList.add(newRoom);
    }

}
