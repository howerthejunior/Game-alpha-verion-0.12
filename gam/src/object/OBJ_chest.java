package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_chest extends Superobject{
    public OBJ_chest(){
        name="chest";
        try {
            image= ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
