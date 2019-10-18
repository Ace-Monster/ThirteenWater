package UI;

import Client.UserActivity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLobbyPanel extends JPanel {
    public GameLobbyPanel(MyFrame frame) {
        this.frame = frame;
        W = frame.getWidth();
        H = frame.getHeight();
        setLayout(null);
        initComponents();

        add(menuPanel);
        add(waifu);
        add(playerIconButton);
        add(playerIcon);
        add(quickMatch);
        add(arenaMatch);
        add(friendMatch);
        add(rankList);
        add(cardTable);
        add(menu);
        add(playerName);
        add(playerTitle);

    }

    private void initComponents() {
        background = new ImageIcon("pictures/lobby.jpg").getImage();
        waifu = new ImagePanel("pictures/waifuX.png");
        playerIcon = new ImagePanel("pictures/playerIcon.jpg");
        quickMatch = new JButton();
        arenaMatch = new JButton();
        friendMatch = new JButton();
        rankList = new JButton();
        cardTable = new JButton();
        menu = new JButton();
        playerName = new JLabel();
        //playerTitle = new JButton();
        playerTitle = new ImagePanel("pictures/huntian.png");
        playerIconButton = new JButton();

        waifu.setBounds(W / 50 + H / 6, H / 50 + H / 5, 3 * W / 10, 7 * H / 8);
        playerIcon.setBounds(W / 40, H / 50, H / 6, H / 6);

        playerIconButton.setBounds(W / 40, H / 50, H / 6, H / 6);
        playerIconButton.setContentAreaFilled(false);
        playerIconButton.setBorderPainted(false);
        playerIconButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (menuPanel.isVisible() == false) {
                    frame.getContentPane().removeAll();
                    frame.setContentPane(new PlayerPanel(frame, true));
                    frame.setVisible(true);
                }
            }
        });

        playerName.setText(UserActivity.user.getUsername());
        //playerName.setText("Stolf");
        playerName.setFont(new Font("微软雅黑", 1, 20));
        playerName.setForeground(Color.WHITE);
        playerName.setBounds(2 * W / 50 + H / 6, H / 45, W / 10, H / 20);
        playerName.setHorizontalAlignment(SwingConstants.CENTER);

        //playerTitle.setText("赌怪");
        //playerTitle.setFont(new Font("微软雅黑", 1, 40));
        //playerTitle.setForeground(Color.RED);
        //playerTitle.setBackground(new Color(0, 153, 255));
        //playerTitle.setBounds(2 * W / 50 + H / 6, H / 45 + H / 15, W / 8, H / 15);
        playerTitle.setBounds(2 * W / 50 + H / 6 + 20, H / 15, H / 7, H / 8);

        quickMatch.setText("快速匹配");
        quickMatch.setFont(new Font("微软雅黑", 1, 50));
        quickMatch.setForeground(Color.WHITE);
        quickMatch.setBackground(new Color(128, 0, 128));
        quickMatch.setBounds(W / 2,H / 9 + H / 50 + H / 5, 2 * W / 7, H / 8);
        quickMatch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (menuPanel.isVisible() == false) {
                    frame.getContentPane().removeAll();
                    frame.setContentPane(new GamePanel(frame));
                    frame.setVisible(true);
                }
            }
        });

        arenaMatch.setText("竞技匹配");
        arenaMatch.setFont(new Font("微软雅黑", 1, 50));
        arenaMatch.setForeground(Color.WHITE);
        arenaMatch.setBackground(new Color(255, 102, 0));
        arenaMatch.setBounds(W / 2,H / 9 + H / 50 + H / 5 + H / 8 + H / 30, 2 * W / 7, H / 8);
        arenaMatch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (menuPanel.isVisible() == false) {
                    frame.getContentPane().removeAll();
                    frame.setContentPane(new GamePanel(frame));
                    frame.setVisible(true);
                }
            }
        });

        friendMatch.setText("好友对战");
        friendMatch.setFont(new Font("微软雅黑", 1, 50));
        friendMatch.setForeground(Color.WHITE);
        friendMatch.setBackground(new Color(51, 204, 0));
        friendMatch.setBounds(W / 2,H / 9 + H / 50 + H / 5 + 2 * H / 8 + 2 * H / 30, 2 * W / 7, H / 8);
        friendMatch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (menuPanel.isVisible() == false) {
                    frame.getContentPane().removeAll();
                    frame.setContentPane(new GamePanel(frame));
                    frame.setVisible(true);
                }
            }
        });

        menu.setText("菜单");
        menu.setFont(new Font("微软雅黑", 1, 30));
        menu.setForeground(Color.WHITE);
        menu.setContentAreaFilled(false);
        menu.setBounds(W - H / 6, 10, H / 7, H / 12);
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (menuPanel.isVisible() == false) {
                    quickMatch.setEnabled(false);
                    arenaMatch.setEnabled(false);
                    friendMatch.setEnabled(false);
                    menuPanel.setVisible(true);
                    updateUI();
                    repaint();
                }
            }
        });

        cardTable.setText("牌谱");
        cardTable.setFont(new Font("微软雅黑", 1, 30));
        cardTable.setForeground(Color.WHITE);
        cardTable.setContentAreaFilled(false);
        cardTable.setBounds(W - 2 * H / 6, 10, H / 7, H / 12);
        cardTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.setContentPane(new CardTablePanel(frame));
                frame.setVisible(true);
            }
        });

        rankList.setText("排行榜");
        rankList.setFont(new Font("微软雅黑", 1, 30));
        rankList.setForeground(Color.WHITE);
        rankList.setContentAreaFilled(false);
        rankList.setBounds(W - 3 * H / 6, 10, H / 7, H / 12);
        rankList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.setContentPane(new RankListPanel(frame));
                frame.setVisible(true);
            }
        });

        menuPanel = new MenuPanel(quickMatch, arenaMatch, friendMatch, frame);
        menuPanel.setVisible(false);
        menuPanel.setBounds(W / 4, H / 4, W / 2, H / 2);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        graphics.drawImage(background, 0, 0, W, H, this);
    }

    private MyFrame frame;
    private int W;
    private int H;
    private Image background;
    private JButton quickMatch;
    private JButton arenaMatch;
    private JButton friendMatch;
    private JButton rankList;
    private JButton cardTable;
    private JButton menu;
    private ImagePanel waifu;
    private ImagePanel playerIcon;
    private JLabel playerName;
    //private JButton playerTitle;
    private ImagePanel playerTitle;
    private JButton playerIconButton;
    private MenuPanel menuPanel;
}
