
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

        int margin = 20;
        int width = getWidth() - 2 * margin;
        int height = getHeight();

        double scale = Math.min(
            (double) width/ image.getWidth(),
            (double) height / image.getHeight()
        );

        int newWidth = (int) (image.getWidth()* scale);
        int newHeight = (int) (image.getHeight() * scale);

        int x = (getWidth() - newWidth) / 2;
        int y = (getHeight() - newHeight) / 2;
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

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