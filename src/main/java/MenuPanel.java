import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MenuPanel extends JPanel  {
    public MenuPanel(MyFrame frame) {
        this.frame = frame;
        inLobby = false;
        background = new ImageIcon("pictures\\menu.png").getImage();
        setLayout(null);
        initComponent();
        add(menuContent);
        add(closeMenu);
        add(exitGame);
    }

    public MenuPanel(JButton b1, JButton b2, JButton b3) {
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        inLobby = true;
        background = new ImageIcon("pictures/menu.png").getImage();
        setLayout(null);
        initComponent();
        add(menuContent);
        add(closeMenu);
        add(exitGame);
    }

    void initComponent() {
        menuContent = new ImagePanel("pictures/menuContent.png");
        menuContent.setBounds(100, 100, 600, 200);

        closeMenu = new JButton();
        exitGame = new JButton();

        if (inLobby)
            exitGame.setText("注销账户");
        else
            exitGame.setText("返回大厅");
        exitGame.setFont(new Font("微软雅黑", 1, 30));
        exitGame.setForeground(Color.WHITE);
        exitGame.setBackground(Color.RED);
        exitGame.setBounds(510, 345, 200, 50);
        exitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inLobby)
                    System.exit(0);
                else {
                    frame.getContentPane().removeAll();
                    frame.setContentPane(new GameLobbyPanel(frame));
                    frame.setVisible(true);
                }
            }
        });

        closeMenu.setText("关闭菜单");
        closeMenu.setFont(new Font("微软雅黑", 1, 30));
        closeMenu.setForeground(Color.WHITE);
        closeMenu.setBackground(new Color(0, 153, 255));
        closeMenu.setBounds(100, 345, 200, 50);
        closeMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                if (inLobby) {
                    b1.setEnabled(true);
                    b2.setEnabled(true);
                    b3.setEnabled(true);
                }
            }
        });
    }

    @Override
    public void paintComponent(Graphics graphics) {
        graphics.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }

    private Image background;
    private ImagePanel menuContent;
    private JButton closeMenu;
    private JButton exitGame;
    private boolean inLobby;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private MyFrame frame;
}
