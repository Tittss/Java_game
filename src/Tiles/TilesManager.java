package Tiles;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TilesManager {

    private final GamePanel gp;
    public Tiles[] tiles;
    public int mapTileNum[][];
    public TilesManager(GamePanel gp) {
        this.gp = gp;

        tiles = new Tiles[10];
        mapTileNum  = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/map/world01.txt");
    }

    public void getTileImage() {

        try {
            tiles [0] = new Tiles();
            tiles [0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tiles [1] = new Tiles();
            tiles [1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tiles [1].collision = true;

            tiles [2] = new Tiles();
            tiles [2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tiles [2].collision = true;

            tiles [3] = new Tiles();
            tiles [3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tiles [4] = new Tiles();
            tiles [4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tiles [4].collision = true;

            tiles [5] = new Tiles();
            tiles [5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while(col < gp.maxWorldCol) {

                    String number[] = line.split(" ");
                    int num = Integer.parseInt(number[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        }  catch (Exception e) {

        }
    }

    public void draw(Graphics2D g2) {

        int col = 0;
        int row = 0;


        while(col < gp.maxWorldCol && row < gp.maxWorldRow) {

            int tileNum = mapTileNum[col][row];

            int worldX = col * gp.tileSize;
            int worldY = row * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tiles[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            col++;

            if(col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }
}
