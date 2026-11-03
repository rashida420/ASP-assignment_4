import java.awt.GraphicsEnvironment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JComponent;

public class Visualization extends JComponent{
    private final BufferedImage image;

    private Visualization(BufferedImage image){
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }

    public void repaintSquare(int squareX, int squareY, int size) {
        double scaleX = (double) getWidth() / image.getWidth();
        double scaleY = (double) getHeight() / image.getHeight();

        repaint((int) (squareX * scaleX), (int) (squareY * scaleY),
                (int) (size * scaleX) + 2, (int) (size * scaleY) + 2);
    }

    public static Visualization show(BufferedImage image) {
        Visualization view = new Visualization(image);

        JFrame window = new JFrame("Square averaging");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setUndecorated(true);
        window.add(view);

        window.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        });

        GraphicsDevice device = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice();

        device.setFullScreenWindow(window);
        window.requestFocus();
        return view;
    }
}




