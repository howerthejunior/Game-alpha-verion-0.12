package entity;

import main.Gamepanel;
import main.Keyhandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    Gamepanel gp;
    Keyhandler keyH;
    public  final int screenX;
    public final int screenY;
    public int bootsTimer=0;
   public int haskey=0;
   int standcount =0;


    public Player(Gamepanel gp, Keyhandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValue();
        getPlayerImage();
        screenX=gp.screenWidth/2-(gp.tileSize/2);
        screenY=gp.screenHeight/2-(gp.tileSize/2);
        // collision area
        solidArea = new Rectangle();
        solidArea.x=6;
        solidArea.y=14;
        solidAreaDefaultX=solidArea.x;
         solidAreaDefaultY=solidArea.y;
        solidArea.width=30;
        solidArea.height=30;

    }

    public void setDefaultValue() {
        wordlx = gp.tileSize*52;
        worldy = gp.tileSize*91;
        speed = 4;
       direction="down";

    }
    public void getPlayerImage(){
        try{
           up1= ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2= ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1= ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2= ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1= ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2= ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1= ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2= ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
    public void update(){
        if (keyH.upPressed==true|| keyH.downPressed==true|| keyH.rightPressed==true|| keyH.leftPressed==true) {
            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            }

            //check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);
            //ckeck object collision
            int objINDEX = gp.cChecker.checkObject(this, true);
            pickUpObject(objINDEX);

            if (collisionOn == false) {

                switch (direction) {
                    case "up":
                        worldy -= speed;
                        break;
                    case "down":
                        worldy += speed;
                        break;
                    case "left":
                        wordlx -= speed;
                        break;
                    case "right":
                        wordlx += speed;

                        break;
                }
            }
            //change sprite image in how much frame
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;

            }
//            else {
//                standcount++;
//
//                if (standcount==20){
//                    spriteNum=1;
//                    standcount =1;
//                }
//
//            }
        }
            // ...

            if (bootsTimer > 0) {
                bootsTimer--;
                if (bootsTimer == 0) {
                    speed -= 3; // Reset speed to normal
                }
            }

            // ...

    }
    public void pickUpObject(int i){
        if (i!=999){
            String objectName=gp.obj[i].name;
            switch (objectName){

                case "key":
                    gp.playSE(1);
                    haskey++;
                    gp.obj[i]=null;
                   gp.ui.showMessage("You got a key!");
                    break;
                case "door":
                    if(haskey>0){
                        gp.playSE(3);
                        gp.obj[i]=null; // Only remove the door that the player is touching
                        haskey--;
                        System.out.println("key :"+haskey);
                        gp.ui.showMessage("What new mysterious are head ?");
                    }
                    else {
                        gp.ui.showMessage("You need a key !");
                    }
                    break;
                case"boots":
                    gp.playSE(2);
                    speed+=3;
                    bootsTimer = 1000;
                    gp.obj[i]=null;
                    gp.ui.showMessage("Wow ! what a speed .");
                  break;
                case "chest":
              gp.ui.gameFinish=true;
              gp.stopMusic();
              gp.playSE(4);
              break;
            }
        }


    }
    public void draw(Graphics2D g2){

//        g2.setColor(Color.white);
//        g2.fillRect(x,y,gp.tileSize,gp.tileSize);
        BufferedImage image =null;
        switch(direction){

            case "up":
                if(spriteNum==1){
                image=up1;
                }
                if (spriteNum==2){
                    image=up2;
                }

                break;
            case "down":
                if (spriteNum==1){
                    image=down1;
                }
                if (spriteNum==2){
                    image=down2;
                }

                break;
            case "left":
                if (spriteNum==1){
                    image=left1;
                }
                if (spriteNum==2){
                    image=left2;
                }
                break;
            case "right":
                if (spriteNum==1){
                    image=right1;
                }
                if (spriteNum==2){
                    image=right2;
                }
                break;
        }
        g2.drawImage(image,screenX,screenY,gp.tileSize, gp.tileSize,null );

    }

}

