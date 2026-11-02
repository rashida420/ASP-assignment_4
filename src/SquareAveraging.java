public class SquareAveraging {

    private static void fillSquare(BufferedImage image, int x, int y, int size, int color) {

        int endX = Math.min(x + size, image.getWidth());
        int endY = Math.min(y + size, image.getHeight());

        for (int j = y; j < endY; j++) {
            for (int i = x; i < endX; i++) {
                image.setRGB(i, j, color);
            }
        }
    }
}