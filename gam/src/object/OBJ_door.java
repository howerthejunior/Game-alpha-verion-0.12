package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_door extends Superobject {
    public OBJ_door() {
        name = "door";  // Set the name of the object to "door"
        try {
            // Attempt to read the image file for the door object
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
        } catch (IOException e) {
            // If an IOException occurs, print the stack trace for debugging
            e.printStackTrace();
        }
     //   solidArea.x=5;  solidArea.y=5;
        collision=true;
    }
}
