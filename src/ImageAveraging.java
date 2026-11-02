import java.awt.image.BufferedImage;

public class ImageAveraging {

    private static void averageRows(BufferedImage image, int size, int startY, int endY) {
        int width = image.getWidth();
        for (int y = startY; y < endY; y += size){
        
            for (int x = 0; x < width; x += size){
                SquareAveraging.averageSquare(image, x, y, size);
            }
        }
    }

}   
