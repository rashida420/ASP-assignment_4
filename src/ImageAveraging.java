import java.awt.image.BufferedImage;

public class ImageAveraging {
    private static void averageRows(BufferedImage image, int size, int startY, int endY, Visualization view) {
        int width = image.getWidth();

        for (int y = startY; y < endY; y += size){
            for (int x = 0; x < width; x += size){
                SquareAveraging.averageSquare(image, x, y, size);
                view.repaintSquare(x, y, size);
                try {
                    Thread.sleep(100);
                } 
                catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
}

    public static void singleThreaded(BufferedImage image, int size, Visualization view) {
        averageRows(image, size, 0, image.getHeight(), view);
    }

    public static void multiThread(BufferedImage image, int size, Visualization view) {

        int firstRow = 0;
        int squareRows = (image.getHeight() + size - 1) / size;
        int numberOfThreads = Math.min(Runtime.getRuntime().availableProcessors(), squareRows);

        int rowsPerThread = squareRows / numberOfThreads;
        int extra = squareRows % numberOfThreads;
        Thread[] threads = new Thread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            int rows = rowsPerThread;
            if (i < extra) {
                rows++;
            }
            int startY = firstRow * size;
            int endY = Math.min((firstRow + rows) * size, image.getHeight());

            threads[i] = new Thread(() ->
                averageRows(image, size, startY, endY, view)
            );
            threads[i].start();
            firstRow += rows;
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }


        
    }
}

