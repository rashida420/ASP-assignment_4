import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class SquareAveraging {
    public static void averageSquare(BufferedImage image, int x, int y, int size) {
        int color = averageColor(image, x, y, size);
        fillSquare(image, x, y, size, color);
}

    private static void fillSquare(BufferedImage image, int x, int y, int size, int color) {
        int endX = Math.min(x + size, image.getWidth());
        int endY = Math.min(y + size, image.getHeight());
        for (int j = y; j < endY; j++) {
            for (int i = x; i < endX; i++) {
                image.setRGB(i, j, color);
            }
        }
    }
    private static int averageColor(BufferedImage image, int x, int y, int size) {
        int endX = Math.min(x + size, image.getWidth());
        int endY = Math.min(y + size, image.getHeight());
        long red = 0, green = 0, blue = 0;
        for (int j = y; j < endY; j++){
            for (int i = x; i < endX; i++){
                int pixel = image.getRGB(i, j);
                red += (pixel >> 16) & 0xFF;
                green += (pixel >> 8) & 0xFF;
                blue += pixel & 0xFF;
            }
        }

        long count = (long) (endX - x) * (endY - y);
        int averageRed= (int)(red / count);
        int averageGreen = (int)(green / count);
        int averageBlue = (int)(blue / count);
        int averageColor = (0xFF << 24) | (averageRed << 16)
        | (averageGreen << 8) | averageBlue;

        return averageColor;
        
    }
}