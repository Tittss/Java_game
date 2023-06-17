package Main;

import Objects.Boots;
import Objects.Chest;
import Objects.Door;
import Objects.Key;

public class AssetSetter {

    private final GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        gp.object[0] = new Key();
        gp.object[0].worldX = 23 * gp.tileSize;
        gp.object[0].worldY = 8 * gp.tileSize;

        gp.object[1] = new Key();
        gp.object[1].worldX = 23 * gp.tileSize;
        gp.object[1].worldY = 40 * gp.tileSize;

        gp.object[2] = new Key();
        gp.object[2].worldX = 38 * gp.tileSize;
        gp.object[2].worldY = 8 * gp.tileSize;

        gp.object[3] = new Door();
        gp.object[3].worldX = 10 * gp.tileSize;
        gp.object[3].worldY = 11 * gp.tileSize;

        gp.object[4] = new Door();
        gp.object[4].worldX = 8 * gp.tileSize;
        gp.object[4].worldY = 28 * gp.tileSize;

        gp.object[5] =new Door();
        gp.object[5].worldX = 12 * gp.tileSize;
        gp.object[5].worldY = 22 * gp.tileSize;

        gp.object[6] =new Chest();
        gp.object[6].worldX = 10 * gp.tileSize;
        gp.object[6].worldY = 7 * gp.tileSize;

        gp.object[7] =new Boots();
        gp.object[7].worldX = 37 * gp.tileSize;
        gp.object[7].worldY = 42 * gp.tileSize;
    }
}
