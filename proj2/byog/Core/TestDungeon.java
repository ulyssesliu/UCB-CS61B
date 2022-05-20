package byog.Core;
import byog.TileEngine.TERenderer;

import java.util.Random;


public class TestDungeon {
    public static void main(String[] args) {
        int WORLD_WIDTH = 50;
        int WORLD_HEIGHT = 50;
        Random random = new Random();

        // initilize a renderer
        TERenderer renderer = new TERenderer();
        renderer.initialize(WORLD_WIDTH,WORLD_HEIGHT);

        // instantiate a dungeon and a room, then add the room to the dungeon.
        Dungeon dungeon = new Dungeon(WORLD_WIDTH,WORLD_HEIGHT);
        Room r1 = new Room(random);
        dungeon.addRoom(r1, random);

        dungeon.renderDungeon(renderer);
    }
}
