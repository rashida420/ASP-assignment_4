import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Visualization extends JComponent {

    private final BufferedImage image;
    private final double scale;
    private final int displayWidth;
    private final int displayHeight;

    private Visualization(BufferedImage image) {

        this.image = image;

        Rectangle screen = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getMaximumWindowBounds();

        double maxWidth = screen.width - 20;
        double maxHeight = screen.height - 60;

        this.scale = Math.min(1.0, Math.min(
                maxWidth / image.getWidth(),
                maxHeight / image.getHeight()
        ));

        this.displayWidth = (int) (image.getWidth() * scale);
        this.displayHeight = (int) (image.getHeight() * scale);
        setPreferredSize(new Dimension(displayWidth, displayHeight));
    }

    @Override
        protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = (getWidth() - displayWidth) / 2;
        int y = (getHeight() - displayHeight) / 2;
        g.drawImage(image, x, y, displayWidth, displayHeight, null);
    }

    public void repaintSquare(int squareX, int squareY, int size) {
        int x = (int) (squareX * scale);
        int y = (int) (squareY * scale);

        int side = (int) Math.ceil(size * scale) + 2;

        repaint(x, y, side, side);
    }

    


   

    public static Visualization show(BufferedImage image) {

        Visualization view = new Visualization(image);

        JFrame window = new JFrame("Image Averaging");

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(view);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        return view;
    }
}