package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Dungeon {
    int width;
    int height;
    boolean isConnected;
    private TETile[][] dungeonMap;

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
        return false;
    }

    // TODO: Add a new room to the dungeonMap (after it passes the test.)
    public void addRoom(Room newRoom){

    }

    // TODO: connect the discrete distributed Rooms in dungeonMap.
    private void connect(){

    }
}
