package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class Base64Converter {
    public static String convertToBase64(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", bos);
        byte[] imgData = bos.toByteArray();

        return Base64.getEncoder().encodeToString(imgData);
    }
}
