package examples.shapes;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.stream.Stream;

public class EmbeddedPicture extends BaseShape {
    BufferedImage img = null;

    public EmbeddedPicture(String fileName){
        try {
            img = ImageIO.read(new File("strawberry.jpg"));
        } catch (IOException e) {
        }
    }
    @Override
    public Stream serialize() {
        return null;
    }

    @Override
    public void render(Graphics2D graphics) throws ShapeException {

        if(renderDetails != null) {
            graphics.translate(renderDetails.getxPosition(),renderDetails.getyPosition());
            graphics.drawImage(img, renderDetails.getxPosition(), renderDetails.getyPosition(), img.getWidth(),img.getHeight() , renderDetails.getFillColor(), null );
            graphics.translate(-renderDetails.getxPosition(),-renderDetails.getyPosition());
        }else{
            graphics.drawImage(img, 0, 0, img.getWidth(),img.getHeight() , Color.WHITE, null );
        }
    }

}
