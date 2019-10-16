import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    public ImagePanel (String filename) {
        background = new ImageIcon(filename).getImage();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        graphics.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }

    private Image background;
}
