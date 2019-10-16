package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GamePanel extends JPanel {
    public GamePanel(MyFrame frame) {
        this.frame = frame;
        W = frame.getWidth();
        H = frame.getHeight();
        setLayout(null);
        initComponents();

        scorePanel.setLayout(null);
        add(scorePanel);
        scorePanel.add(scoreImage);
        scorePanel.add(close);
        add(menuPanel);
        add(menu);
        add(scoreboard);
        add(counter);
        add(power);
        add(playerIconButton);
        add(playerIcon);
        add(playerTitle);
        add(playerName);
    }

    private void initComponents() {
        background = new ImageIcon("pictures/game.jpg").getImage();

        menu = new JButton();
        scoreboard = new JButton();
        counter = new ImagePanel("pictures/counter.png");
        power = new ImagePanel("pictures/power.png");
        playerIcon = new ImagePanel("pictures/playerIcon.jpg");
        playerIconButton = new JButton();
        //playerTitle = new JButton();
        playerTitle = new ImagePanel("pictures/huntian.png");
        scorePanel = new JPanel();
        scoreImage = new ImagePanel("pictures/scoreboard.png");
        close = new JButton();
        playerName = new JLabel();

        menu.setText("菜单");
        menu.setFont(new Font("微软雅黑", 1, 30));
        menu.setForeground(Color.WHITE);
        menu.setContentAreaFilled(false);
        menu.setBounds(W - H / 6, 10, H / 7, H / 12);
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (menuPanel.isVisible() == false && scorePanel.isVisible() == false) {
                    menuPanel.setVisible(true);
                    updateUI();
                    repaint();
                }
            }
        });

        scoreboard.setText("计分板");
        scoreboard.setFont(new Font("微软雅黑", 1, 30));
        scoreboard.setForeground(Color.WHITE);
        scoreboard.setContentAreaFilled(false);
        scoreboard.setBounds(W - 2 * H / 6, 10, H / 7, H / 12);
        scoreboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (menuPanel.isVisible() == false && scorePanel.isVisible() == false) {
                    scorePanel.setVisible(true);
                    scoreboard.setEnabled(false);
                    menu.setEnabled(false);
                    updateUI();
                    repaint();
                }
            }
        });

        counter.setBounds(W / 2 - H / 7, H / 2 - H / 5, 2 * H / 7, H / 4);

        power.setBounds(10, 10, H / 8, H / 15);

        //playerName.setText(UserActivity.user.getUsername());
        playerName.setText("Stolf");
        playerName.setFont(new Font("微软雅黑", 1, 25));
        playerName.setForeground(Color.WHITE);
        playerName.setBounds(W / 7 + W / 40, 5 * H / 7 - H / 20 + 3 * H / 20, H / 7, H / 26);
        playerName.setHorizontalAlignment(SwingConstants.CENTER);

        playerIcon.setBounds(W / 7 + W / 40, 5 * H / 7 - H / 20, H / 7, H / 7);

        //playerTitle.setText("赌怪");
        //playerTitle.setFont(new Font("微软雅黑", 1, 25));
        //playerTitle.setForeground(Color.RED);
        //playerTitle.setBackground(new Color(0, 153, 255));
        //playerTitle.setBounds(W / 7 + W / 40, 5 * H / 7 - H / 20 + 3 * H / 20 + H / 25, H / 7, H / 26);
        playerTitle.setBounds(W / 7 + W / 40, 5 * H / 7 - H / 20 + 3 * H / 20 + H / 40, H / 7, H / 8);

        playerIconButton.setBounds(W / 7 + W / 40, 5 * H / 7 - H / 20, H / 7, H / 7);
        playerIconButton.setContentAreaFilled(false);
        playerIconButton.setBorderPainted(false);
        playerIconButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (menuPanel.isVisible() == false && scorePanel.isVisible() == false) {
                    frame.getContentPane().removeAll();
                    frame.setContentPane(new PlayerPanel(frame));
                    frame.setVisible(true);
                }
            }
        });

        menuPanel = new MenuPanel(frame);
        menuPanel.setVisible(false);
        menuPanel.setBounds(W / 4, H / 4, W / 2, H / 2);

        scorePanel.setVisible(false);
        scorePanel.setBounds(2 * W / 9, H / 9, 5 * W / 9, 5 * H / 9);

        scoreImage.setBounds(0, 0, 5 * W / 9, 5 * H / 9);

        close.setText("关闭");
        close.setFont(new Font("微软雅黑", 1, 20));
        close.setBackground(new Color(55, 155, 211));
        close.setForeground(Color.WHITE);
        close.setBackground(Color.RED);
        close.setBounds(15 * W / 32, 0, W / 18,H / 18);
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scorePanel.setVisible(false);
                scoreboard.setEnabled(true);
                menu.setEnabled(true);
                updateUI();
                repaint();
            }
        });

    }

    @Override
    public void paintComponent(Graphics graphics) {
        graphics.drawImage(background, 0, 0, W, H, this);
    }

    private MyFrame frame;
    private int W;
    private int H;
    private Image background;
    private JButton menu;
    private JButton scoreboard;
    private ImagePanel counter;
    private ImagePanel power;
    private ImagePanel playerIcon;
    //private JButton playerTitle;
    private ImagePanel playerTitle;
    private JLabel playerName;
    private JButton playerIconButton;
    private MenuPanel menuPanel;
    private JPanel scorePanel;
    private ImagePanel scoreImage;
    private JButton close;
}
