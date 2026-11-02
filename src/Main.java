import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (checkArguments(args) == false) {
            return;
        }

        int size = squareSize(args[1]);
        if (size == -1) {
            return;
        }
        BufferedImage image = loadImage(args[0]);
        if (image == null) {
            return;
        
}
        System.out.println("Image width: " + image.getWidth());
        System.out.println("Image height: " + image.getHeight());
    }





    private static boolean checkArguments(String[] args) {
        if (args.length == 3) {
            return true;
        }
        reportError("The program requires exactly 3 arguments.");
        return false;
    }

    private static int squareSize(String value) {
        try {
            int size = Integer.parseInt(value);

            if (size > 0) {
                return size;
            }
            reportError("size must be greater than zero.");
        }
        catch (NumberFormatException e) {
            reportError("size must be a valid integer.");
        }
        return -1;
    }
    private static void reportError(String message) {
        System.err.println("Error: " + message);
        System.err.println("Usage: java -cp out Main <file.jpg> <square_size> <S|M>");
    }

    private static BufferedImage loadImage(String filename) {
    try {
        File file = new File(filename);
        BufferedImage image = ImageIO.read(file);

        if (image == null) {
            reportError("File not found");
            return null;
        }
        return image;
    }
    catch (IOException e) {
        reportError("Could not read the image file.");
        return null;
    }
}
}
