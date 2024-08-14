package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_key extends Superobject {
    public OBJ_key(){
     name="key";
     try {
    image= ImageIO.read(getClass().getResourceAsStream("/objects/Key.png"));
     }catch (IOException e){
         e.printStackTrace();
     }
    }
}
