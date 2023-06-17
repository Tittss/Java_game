package Main;

import Entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }
    public void checkTile(Entity entity) {

        double entityLeftWorldX = entity.worldX + entity.solidArea.x;
        double entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        double entityTopWorldY = entity.worldY + entity.solidArea.y;
        double entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        double entityLeftCol = entityLeftWorldX / gp.tileSize;
        double entityRightCol = entityRightWorldX / gp.tileSize;
        double entityTopRow = entityTopWorldY / gp.tileSize;
        double entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tilesManager.mapTileNum[(int)entityLeftCol][(int)entityTopRow];
                tileNum2 = gp.tilesManager.mapTileNum[(int)entityRightCol][(int)entityTopRow];
                if(gp.tilesManager.tiles[tileNum1].collision == true || gp.tilesManager.tiles[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tilesManager.mapTileNum[(int)entityLeftCol][(int)entityBottomRow];
                tileNum2 = gp.tilesManager.mapTileNum[(int)entityRightCol][(int)entityBottomRow];
                if(gp.tilesManager.tiles[tileNum1].collision == true || gp.tilesManager.tiles[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tilesManager.mapTileNum[(int)entityLeftCol][(int)entityTopRow];
                tileNum2 = gp.tilesManager.mapTileNum[(int)entityLeftCol][(int)entityBottomRow];
                if(gp.tilesManager.tiles[tileNum1].collision == true || gp.tilesManager.tiles[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tilesManager.mapTileNum[(int)entityRightCol][(int)entityTopRow];
                tileNum2 = gp.tilesManager.mapTileNum[(int)entityRightCol][(int)entityBottomRow];
                if(gp.tilesManager.tiles[tileNum1].collision == true || gp.tilesManager.tiles[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public int checkObjects(Entity entity, boolean player) {

        int index = 999;
        for(int i=0; i<gp.object.length; i++) {
            if(gp.object[i] != null) {
                //Get entity's solid area position
                 entity.solidArea.x =  (int)entity.worldX +  entity.solidArea.x;
                 entity.solidArea.y =  (int)entity.worldY +  entity.solidArea.y;
                //Get the object's solid area position
                gp.object[i].solidArea.x = gp.object[i].worldX + gp.object[i].solidArea.x;
                gp.object[i].solidArea.y = gp.object[i].worldY + gp.object[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gp.object[i].solidArea)) {
                            if(gp.object[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.object[i].solidArea)) {
                            if(gp.object[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.object[i].solidArea)) {
                            if(gp.object[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gp.object[i].solidArea)) {
                            if(gp.object[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.object[i].solidArea.x = gp.object[i].solidAreaDefaultX;
                gp.object[i].solidArea.y = gp.object[i].solidAreaDefaultY;

            }
        }
        return index;
    }
}
