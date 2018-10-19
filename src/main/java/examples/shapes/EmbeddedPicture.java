package examples.shapes;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmbeddedPicture extends BaseShape {
    BufferedImage img = null;

    public EmbeddedPicture(String fileName){
        try {
            img = ImageIO.read(new File(fileName));
        } catch (IOException e) {
        }
    }

    public EmbeddedPicture(byte[] byteArray) {
        try {
            img = ImageIO.read(new ByteArrayInputStream(byteArray));
        } catch(IOException e) {
            e.printStackTrace();
        }
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

    @Override
    public String toString() {
        String output = "<EmbeddedPicture></EmbeddedPicture";
        byte[] imageInByte = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(img, "jpg", baos);
            baos.flush();
            imageInByte = baos.toByteArray();
            baos.close();

            output = "<EmbeddedPicture::bytes="+ java.util.Base64.getEncoder().encodeToString(imageInByte) + "::EmbeddedPicture>";
        } catch(IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    public static EmbeddedPicture deserialize(Stream stream) throws ShapeException{
        EmbeddedPicture embeddedPicture = null;
        String string = (String)stream.collect(Collectors.joining(""));

        String regX = "<EmbeddedPicture::bytes=(\\S+)::EmbeddedPicture>";
        Pattern p = Pattern.compile(regX);
        Matcher m = p.matcher(string);
        if(m.find()){
            String bytes = m.group(1);
            byte[] byteArray = java.util.Base64.getDecoder().decode(bytes);
            //System.out.println("EmbeddedPicture bytes=>" + bytes);
            embeddedPicture = new EmbeddedPicture(byteArray);
        }
        return embeddedPicture;
    }

    @Override
    public double getArea() throws ShapeException {
        return 0;
    }
}