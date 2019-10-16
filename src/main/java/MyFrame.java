import javax.swing.*;

public class MyFrame extends JFrame {
    public MyFrame(String title) {
        super(title);
        setLayout(null);
        setSize(1600,900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setContentPane(new TitlePanel(this));
        setVisible(true);
    }
}
