package objects;

import javax.imageio.ImageIO;

public class Key_Object extends SuperObject {
    public Key_Object() {
        name = "Key";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/key.png"));
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
