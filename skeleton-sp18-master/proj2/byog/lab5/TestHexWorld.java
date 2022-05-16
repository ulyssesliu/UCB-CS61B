package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.Tileset;

public class TestHexWorld {
    public static void main(String[] args){
        int WORLD_WIDTH = 50;
        int WORLD_HEIGHT = 50;

        TERenderer renderer = new TERenderer();
        renderer.initialize(WORLD_WIDTH,WORLD_HEIGHT);

        HexWorld wrd = new HexWorld(WORLD_WIDTH, WORLD_HEIGHT);

        // Basic tests for addHexagon()
        wrd.addHexagon(2, 0, 0);
        wrd.addHexagon(3,4,4);
        wrd.addHexagon(5,11,11, Tileset.GRASS);


        wrd.renderWorld(renderer);
    }
}
