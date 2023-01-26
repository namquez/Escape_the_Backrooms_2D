package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;

    }
    public void checkTile(Entity entity) {

        int entityLeftMapX = entity.mapX + entity.solidArea.x;
        int entityRightMapX = entity.mapX + entity.solidArea.x + entity.solidArea.width;
        int entityTopMapY = entity.mapY + entity.solidArea.y;
        int entityBottomMapY = entity.mapY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftMapX/gp.tileSize;
        int entityRightCol = entityRightMapX/gp.tileSize;
        int entityTopRow = entityTopMapY/gp.tileSize;
        int entityBottomRow = entityBottomMapY/gp.tileSize;

        int tileNum1, tileNum2;

        if (entity.direction == "up") {
            entityTopRow = (entityTopMapY - entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision ) {
                entity.collisionOn = true;
            }

        } else if (entity.direction == "left") {
            entityLeftCol = (entityLeftMapX - entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision ) {
                entity.collisionOn = true;
            }

        } else if (entity.direction == "down") {
            entityBottomRow = (entityBottomMapY + entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
            if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision ) {
                entity.collisionOn = true;
            }

        } else if (entity.direction == "right") {
            entityRightCol = (entityRightMapX + entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
            if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision ) {
                entity.collisionOn = true;
            }

        }

    }
    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for(int i = 0; i < gp.obj.length; i ++) {
            if(gp.obj[i] != null) {

                // get player's solid area position
                entity.solidArea.x = entity.mapX + entity.solidArea.x;
                entity.solidArea.y = entity.mapY + entity.solidArea.y;

                // get the object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].mapX;
                gp.obj[i].solidArea.y = gp.obj[i].mapY;

                if (entity.direction == "up") {
                    entity.solidArea.y -= entity.speed;
                    if(entity.solidArea.intersects(gp.obj[i].solidArea)){ // checks if the two areas touching
                        if(player) {
                            index = i;
                        }
                    }


                } else if (entity.direction == "left"){
                    entity.solidArea.x -= entity.speed;
                    if(entity.solidArea.intersects(gp.obj[i].solidArea)){ // checks if the two areas touching
                        if(player) {
                            index = i;
                        }
                    }

                } else if (entity.direction == "down"){
                    entity.solidArea.y += entity.speed;
                    if(entity.solidArea.intersects(gp.obj[i].solidArea)){ // checks if the two areas touching
                        if(player) {
                            index = i;
                        }
                    }

                } else if (entity.direction == "right"){
                    entity.solidArea.x += entity.speed;
                    if(entity.solidArea.intersects(gp.obj[i].solidArea)){ // checks if the two areas touching
                        if(player) {
                            index = i;
                        }
                    }

                }

                // reset areas to correct check location next loop
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }

        return index;


    }
    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;

        for(int i = 0; i < target.length; i ++) {
            if(target[i] != null) {

                // get player's solid area position
                entity.solidArea.x = entity.mapX + entity.solidArea.x;
                entity.solidArea.y = entity.mapY + entity.solidArea.y;

                // get the object's solid area position
                target[i].solidArea.x = target[i].mapX;
                target[i].solidArea.y = target[i].mapY;

                if (entity.direction == "up") {
                    entity.solidArea.y -= entity.speed;
                    if(entity.solidArea.intersects(target[i].solidArea)){ // checks if the two areas touching
                        index = i;
                    }


                } else if (entity.direction == "left"){
                    entity.solidArea.x -= entity.speed;
                    if(entity.solidArea.intersects(target[i].solidArea)){ // checks if the two areas touching
                        index = i;
                    }

                } else if (entity.direction == "down"){
                    entity.solidArea.y += entity.speed;
                    if(entity.solidArea.intersects(target[i].solidArea)){ // checks if the two areas touching
                        index = i;
                    }

                } else if (entity.direction == "right"){
                    entity.solidArea.x += entity.speed;
                    if(entity.solidArea.intersects(target[i].solidArea)){ // checks if the two areas touching
                        index = i;
                    }

                }

                // reset areas to correct check location next loop
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }

        return index;
    }
    public void checkPlayer(Entity entity) {
        // get player's solid area position
        entity.solidArea.x = entity.mapX + entity.solidArea.x;
        entity.solidArea.y = entity.mapY + entity.solidArea.y;

        gp.player.solidArea.x = gp.player.mapX;
        gp.player.solidArea.y = gp.player.mapY;

        if (entity.direction == "up") {
            entity.solidArea.y -= entity.speed;
            if(entity.solidArea.intersects(gp.player.solidArea)){ // checks if the two areas touching

            }


        } else if (entity.direction == "left"){
            entity.solidArea.x -= entity.speed;
            if(entity.solidArea.intersects(gp.player.solidArea)){ // checks if the two areas touching
            }

        } else if (entity.direction == "down"){
            entity.solidArea.y += entity.speed;
            if(entity.solidArea.intersects(gp.player.solidArea)){ // checks if the two areas touching
            }

        } else if (entity.direction == "right"){
            entity.solidArea.x += entity.speed;
            if(entity.solidArea.intersects(gp.player.solidArea)){ // checks if the two areas touching
            }

        }

        // reset areas to correct check location next loop
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

    }

}
