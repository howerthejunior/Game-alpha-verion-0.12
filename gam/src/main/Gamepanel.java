package main;

import entity.Player;
import object.Superobject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Gamepanel extends JPanel implements Runnable {


    //screen size
    public int originalTileSize =16;
    public int scale = 3;
   public int tileSize = originalTileSize*scale;
public  int maxScreenCol=16;
    public  int maxScreenRow=12;
  public int screenWidth = tileSize*maxScreenCol;//768 pixel
   public int screenHeight = tileSize*maxScreenRow;//576 pixel
    //world settings
    public final int maxWorldCol=100;
    public final int maxWorldRow=100;
    public final int worldWidth=tileSize*maxWorldCol;
    public final int worldHeight=tileSize*maxWorldRow;


    int FPS =30;
    TileManager tileM= new TileManager(this);
    Keyhandler keyH= new Keyhandler();

    Sound music=new Sound();
    Sound se=new Sound();
    public UI ui=new UI(this);
    public CollisionChecker cChecker= new CollisionChecker(this);
    public AssetSetter aSetter=new AssetSetter(this);
    Thread gameThread;

    //entity and object
   public  Player player= new Player(this,keyH);

   public Superobject[] obj =new Superobject[20];
    public Gamepanel() throws IOException {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.blue);
        this.setDoubleBuffered(true);
         this.addKeyListener(keyH);
         this.setFocusable(true);
        for (int i = 0; i < obj.length; i++) {
            obj[i] = null;  // Ensure all elements are null initially
            }

    }

    public void setUpGame()
    {
        aSetter.setObject();
      playMusic(0);
    }
    public void startGameThread()
    {
        gameThread= new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double interval=1000000000/FPS;
        double nextdrawinterval=System.nanoTime()+interval;
      while(gameThread!=null){
            // long currentTime=System.nanoTime();
           //  System.out.println(currentTime);
       // System.out.println("Game loop is working");
          update();
          repaint();
          try {
          double remaintime= nextdrawinterval -System.nanoTime();
              remaintime=remaintime/1000000;
              if(remaintime<0 )  {
                  remaintime=0;
              }
              Thread.sleep((long)remaintime) ;
              nextdrawinterval+=interval;
          } catch (InterruptedException e) {
              throw new RuntimeException(e);
          }
      }


    }
    public void  update() {
      player.update();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //DEBUG
        long drawstart = 0;
        if (keyH.checkdrawtime) {
            drawstart = System.nanoTime();
        }


        //tile
        tileM.draw(g2);
        //object
        if (obj != null) {
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].draw(g2, this);
                }
            }
        }

        //player
        player.draw(g2);
        //UI
        ui.draw(g2);


        if (keyH.checkdrawtime) {
            long drawend = System.nanoTime();
            long passes = drawend - drawstart;
            g2.setColor(Color.WHITE);
            g2.drawString("Draw time: " + passes + " ns", 10, 400);
            System.out.println("Draw time: " + passes + " ns");
        }

        g2.dispose();


    }
    public void playMusic(int i){
       music.setFile(i);
      music.play();
       music.loop();
    }
    public  void stopMusic(){
        music.stop();
    }
    public void playSE(int i){
        se.setFile(i);
        se.play();
    }
}

