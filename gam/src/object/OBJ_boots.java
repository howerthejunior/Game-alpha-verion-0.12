package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_boots extends Superobject{
    public OBJ_boots(){
        name="boots";
        try {
            image= ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
