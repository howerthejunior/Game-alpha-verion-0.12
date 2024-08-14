package main;

import object.*;


public class AssetSetter {
    Gamepanel gp;
    public  AssetSetter(Gamepanel gp) {
        this.gp=gp;}
    public void setObject(){
        // where to place on map
        gp.obj[0]=new OBJ_door();
        gp.obj[0].worldX=36*gp.tileSize;
        gp.obj[0].worldY=36*gp.tileSize;
        gp.obj[1]=new OBJ_door();
        gp.obj[1].worldX=43*gp.tileSize;
        gp.obj[1].worldY=11*gp.tileSize;
        gp.obj[2]=new OBJ_door();
        gp.obj[2].worldX=88*gp.tileSize;
        gp.obj[2].worldY=17*gp.tileSize;
        gp.obj[3]=new OBJ_door();
        gp.obj[3].worldX=85*gp.tileSize;
        gp.obj[3].worldY=2*gp.tileSize;
        gp.obj[4]=new OBJ_door();
        gp.obj[4].worldX=81*gp.tileSize;
        gp.obj[4].worldY=40*gp.tileSize;
        gp.obj[5]=new OBJ_door();
        gp.obj[5].worldX=68*gp.tileSize;
        gp.obj[5].worldY=1*gp.tileSize;

        gp.obj[6]=new OBJ_key();
        gp.obj[6].worldX=42*gp.tileSize;
         gp.obj[6].worldY=72*gp.tileSize;
        gp.obj[7]=new OBJ_key();
        gp.obj[7].worldX=71*gp.tileSize;
        gp.obj[7].worldY=14*gp.tileSize;
        gp.obj[8]=new OBJ_key();
        gp.obj[8].worldX=42*gp.tileSize;
        gp.obj[8].worldY=9*gp.tileSize;
        gp.obj[9]=new OBJ_key();
        gp.obj[9].worldX=22*gp.tileSize;
        gp.obj[9].worldY=13*gp.tileSize;
        gp.obj[10]=new OBJ_key();
        gp.obj[10].worldX=9*gp.tileSize;
        gp.obj[10].worldY=53*gp.tileSize;
        gp.obj[11]=new OBJ_key();
        gp.obj[11].worldX=98*gp.tileSize;
        gp.obj[11].worldY=1*gp.tileSize;






        gp.obj[16]=new OBJ_chest();
        gp.obj[16].worldX=87*gp.tileSize;
        gp.obj[16].worldY=1*gp.tileSize;


        gp.obj[12]=new OBJ_boots();
        gp.obj[12].worldX=43*gp.tileSize;
        gp.obj[12].worldY=9*gp.tileSize;
        gp.obj[13]=new OBJ_boots();
        gp.obj[13].worldX=85*gp.tileSize;
        gp.obj[13].worldY=32*gp.tileSize;
        gp.obj[14]=new OBJ_boots();
        gp.obj[14].worldX=22*gp.tileSize;
        gp.obj[14].worldY=35*gp.tileSize;
        gp.obj[15]=new OBJ_boots();
        gp.obj[15].worldX=82*gp.tileSize;
        gp.obj[15].worldY=94*gp.tileSize;


    }
}
