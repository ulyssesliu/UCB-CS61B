package byog.Core;
import byog.TileEngine.TERenderer;

import java.util.Random;


public class TestDungeon {
    public static void main(String[] args) {
        int WORLD_WIDTH = 100;
        int WORLD_HEIGHT = 70;
        Random random = new Random();

        // initialize a renderer
        TERenderer renderer = new TERenderer();
        renderer.initialize(WORLD_WIDTH,WORLD_HEIGHT);

        // instantiate a dungeon and a room, then add the room to the dungeon.
        Dungeon dungeon = new Dungeon(WORLD_WIDTH,WORLD_HEIGHT);

        // addRoom ten times
        for(int i = 0; i < 5; i++){;
            dungeon.addRoom(random);
        }
        dungeon.connect();

        dungeon.renderDungeon(renderer);
    }
}
