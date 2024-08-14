package main;

import object.OBJ_key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    Gamepanel gp;
    Font Arial_40,arial_80;
    BufferedImage keyImage;
    public boolean messageOn=false;
    public String message="";
    int messageCounter=0;
    public boolean gameFinish= false;
    double playtime;
    DecimalFormat decimalformat = new DecimalFormat("#0.0");
    public UI(Gamepanel gamepanel) {
        this.gp = gamepanel; // Initialize the gp field
        Arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80 = new Font("Arial", Font.BOLD, 80);

        OBJ_key key =new OBJ_key();
        keyImage=key.image;
    }

    public void showMessage(String text){
   message =text;
    messageOn=true;
    }
    public void draw(Graphics2D g2) {
       if(gameFinish==true){


           String text;
           int textLength;
          int x;
           int y;
           text= "You Found the Treasure";
         textLength=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
           x=gp.screenWidth/2-textLength/2;
           y=gp.screenHeight/2+(gp.tileSize*2);
           g2.drawString(text,x,y);

           text= "Your time is :"+decimalformat.format(playtime)+"!";
           textLength=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
           x=gp.screenWidth/2-textLength/2;
           y=gp.screenHeight/2+(gp.tileSize*4);
           g2.drawString(text,x,y);

           g2.setFont(arial_80);
           g2.setColor(Color.YELLOW);
           text= "CONGRATULATION !";
           textLength=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
           x=gp.screenWidth/2-textLength/2;
           y=gp.screenHeight/2-(gp.tileSize*2);
           g2.drawString(text,x,y);

           gp.gameThread =null;
       }

        else{
        g2.setFont(Arial_40);
        g2.setColor(Color.WHITE);
        g2.drawImage(keyImage,gp.tileSize/2,gp.tileSize/2,gp.tileSize,gp.tileSize,null);
        g2.drawString("=" + gp.player.haskey, 74, 65);

        //TIME
           playtime+=(double)1/60;
       // g2.drawString("Boots Timer: " + gp.player.bootsTimer, 50, 90); // Add this line
           g2.drawString("Time :"+decimalformat.format(playtime),gp.tileSize*11,65);
        if (messageOn==true){
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message,gp.tileSize/2,gp.tileSize*5);
            messageCounter++;
            if (messageCounter>20){
                messageCounter=0;
                messageOn=false;}
            }
        }
    }
}
