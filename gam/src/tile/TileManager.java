package tile;

import main.Gamepanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TileManager {
    Gamepanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;
    ArrayList<String> FileNames = new ArrayList<>();
    ArrayList<String> collisionstatus = new ArrayList<>();


    public TileManager(Gamepanel gp) {
        this.gp = gp;
       // InputStream is=getClass().getResourceAsStream("/maps/tiledata.txt");
       // BufferedReader br = new BufferedReader(new InputStreamReader(is));
        tile = new Tile[12]; // Adjust this size if you have more or fewer tiles
        getTileImage();
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        loadMap("/maps/world1.txt");
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/brick.png"));
            tile[0].collision = true;

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/land.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tile[5].collision = true;

            // Initialize additional tiles as needed, following the pattern above

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filepath) {
        try {
            InputStream is = getClass().getResourceAsStream(filepath);
            if (is == null) {
                throw new IOException("Map file not found: " + filepath);
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;

            while (row < gp.maxWorldRow) {
                String line = br.readLine();
                if (line == null || line.trim().isEmpty()) {
                    continue;
                }

                String[] numbers = line.trim().split(" ");
                for (col = 0; col < numbers.length && col < gp.maxWorldCol; col++) {
                    try {
                        int num = Integer.parseInt(numbers[col]);
                        if (num < tile.length) {
                            mapTileNum[col][row] = num;
                        } else {
                            System.err.println("Tile index out of bounds at row " + row + " col " + col);
                            mapTileNum[col][row] = 0; // Default to 0 if index is out of bounds
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing number: " + numbers[col] + " at row " + row + " col " + col);
                        mapTileNum[col][row] = 0; // Default to 0 if parsing fails
                    }
                }
                row++;
            }
            br.close();
        } catch (IOException e) {
            System.err.println("Error loading map: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            if (tileNum >= 0 && tileNum < tile.length && tile[tileNum] != null) {
                int worldX = worldCol * gp.tileSize;
                int worldY = worldRow * gp.tileSize;
                int screenX = worldX - gp.player.wordlx + gp.player.screenX;
                int screenY = worldY - gp.player.worldy + gp.player.screenY;

                if (worldX + gp.tileSize > gp.player.wordlx - gp.player.screenX &&
                        worldX - gp.tileSize < gp.player.wordlx + gp.player.screenX &&
                        worldY + gp.tileSize > gp.player.worldy - gp.player.screenY &&
                        worldY - gp.tileSize < gp.player.worldy + gp.player.screenY) {

                    g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }
            }

            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
