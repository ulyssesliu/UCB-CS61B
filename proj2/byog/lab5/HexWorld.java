package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    int width;
    int height;
    private final TETile[][] worldMap;

    // constructor of HexWorld
    public HexWorld(){
        width = 0;
        height = 0;
        worldMap = null;
    }
    public HexWorld(int width, int height){
        this.width = width;
        this.height = height;
        worldMap = new TETile[width][height];
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                worldMap[x][y] = Tileset.VOID;
            }
        }
    }
    public HexWorld(int width, int height, TETile defaultTile){
        this.width = width;
        this.height = height;
        worldMap = new TETile[width][height];
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                worldMap[x][y] = defaultTile;
            }
        }
    }



    public static class Hexagon{
        private int Size;

        public Hexagon(){};
        public Hexagon(int Size){
            this.Size = Size;
            TETile tileType = Tileset.LAB5_TEST_TILE;
        }

    }

    // TODO (Done): fill a Hexagon in the worldMap
    public void addHexagon(int size, int xOff, int yOff){
        Hexagon hexagon = new Hexagon(size);

        // check whether args are legal
        if(xOff < 0 || yOff < 0){
            throw new IllegalArgumentException("The offset should be non-negative!");
        }

        // boundary trespass check
        int hex_width = 3*hexagon.Size - 2;
        int hex_height = 2*hexagon.Size;
        if(xOff + hex_width >= this.width || yOff + hex_height >= this.height){
            throw new IllegalStateException("The hexagon trespasses the worldMap boundary! " +
                                            "The size of current worldmap is width: " + this.width
                                            + "; height: " + this.height + ".");
        }

        // fill the tiles
        for (int x = xOff; x < xOff + hex_width; x++) { // columns
            for (int y = yOff; y < yOff + hexagon.Size; y++) { // rows

                if (x > xOff + (hexagon.Size - (y-yOff) - 2) && x < xOff + (2*hexagon.Size + (y-yOff) - 1)) { // fill the Tile
                    worldMap[x][y] = Tileset.LAB5_TEST_TILE; // bottom half
                    worldMap[x][(2*yOff + 2*hexagon.Size - 1) - y] = Tileset.LAB5_TEST_TILE;

                } else { // fill with void
                    // The control flow is to make sure that the current void doesn't cover the previous graphs
                    if(worldMap[x][y].equals(Tileset.VOID)){worldMap[x][y] = Tileset.VOID;}
                    if(worldMap[x][(2*yOff + 2*hexagon.Size - 1) - y].equals(Tileset.VOID)){worldMap[x][(2*yOff + 2*hexagon.Size - 1) - y] = Tileset.VOID;}
                }
            }
        }

    }

    public void addHexagon(int size, int xOff, int yOff, TETile tileType){
        Hexagon hexagon = new Hexagon(size);

        // check whether args are legal
        if(xOff < 0 || yOff < 0){
            throw new IllegalArgumentException("The offset should be non-negative!");
        }

        // boundary trespass check
        int hex_width = 3*hexagon.Size - 2;
        int hex_height = 2*hexagon.Size;
        if(xOff + hex_width >= this.width || yOff + hex_height >= this.height){
            throw new IllegalStateException("The hexagon trespasses the worldMap boundary! " +
                    "The size of current worldmap is width: " + this.width
                    + "; height: " + this.height + ".");
        }

        // fill the tiles
        for (int x = xOff; x < xOff + hex_width; x++) { // columns
            for (int y = yOff; y < yOff + hexagon.Size; y++) { // rows

                if (x > xOff + (hexagon.Size - (y-yOff) - 2) && x < xOff + (2*hexagon.Size + (y-yOff) - 1)) { // fill the Tile
                    worldMap[x][y] = tileType; // bottom half
                    worldMap[x][(2*yOff + 2*hexagon.Size - 1) - y] = tileType;

                } else { // fill with void
                    // The control flow is to make sure that the current void doesn't cover the previous graphs
                    if(worldMap[x][y].equals(Tileset.VOID)){worldMap[x][y] = Tileset.VOID;}
                    if(worldMap[x][(2*yOff + 2*hexagon.Size - 1) - y].equals(Tileset.VOID)){worldMap[x][(2*yOff + 2*hexagon.Size - 1) - y] = Tileset.VOID;}
                }
            }
        }

    }

    // TODO (done): render the current worldMap
    public void renderWorld(TERenderer renderer){
        if(worldMap == null){
            throw new IllegalStateException("The worldMap is Null!");
        }
        // render the Hexagon in the world
        renderer.renderFrame(this.worldMap);
    }
}
