package UI;

import Client.HistoryActivity;
import Client.UserActivity;
import Model.History;
import Model.RankList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Font;

import static java.lang.Math.max;

public class PlayerPanel extends JPanel  {
    public PlayerPanel(MyFrame frame, boolean inLobby) {
        this.inLobby = inLobby;
        this.frame = frame;
        W = frame.getWidth();
        H = frame.getHeight();
        setLayout(null);

        background = new ImageIcon("pictures/tanfang.png").getImage();
        initHistoryList();
        if (historyList != null) {
            initComponents();
        }
    }

    void initComponents() {
        menu = new JButton();
        add(menu);
        menu.setText("关闭");
        menu.setFont(new Font("微软雅黑", 1, 30));
        menu.setForeground(Color.RED);
        menu.setContentAreaFilled(false);
        menu.setBounds(W - H / 6, 10, H / 7, H / 12);
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                if (inLobby)
                    frame.setContentPane(new GameLobbyPanel(frame));
                else
                    frame.setContentPane(new GamePanel(frame));
                frame.setVisible(true);
            }
        });

        backPanel = new ImagePanel("pictures/menu.png");
        backPanel.setBounds(100, 50, 1400, 800);
        backPanel.setLayout(null);
        add(backPanel);

        infoPanel = new JPanel();
        infoPanel.setBounds(100, 110, 1200, 650);
        infoPanel.setBackground(new Color(96, 96, 96, 120));
        infoPanel.setLayout(null);
        backPanel.add(infoPanel);

        playerIcon = new ImagePanel("pictures/playerIcon.jpg");
        playerIcon.setBounds(25, 25, 150, 150);
        infoPanel.add(playerIcon);

        playerTitle = new ImagePanel("pictures/huntian.png");
        playerTitle.setBounds(25, 200, 150, 150);
        infoPanel.add(playerTitle);

        scrollPanel.setBackground(new Color(96, 96, 96, 120));

        infoScroll = new JScrollPane(scrollPanel);
        infoScroll.setBounds(200, 0, 1000, 700);
        infoPanel.add(infoScroll);
        infoScroll.getVerticalScrollBar().setUnitIncrement(20);

    }

    void initHistoryList() {
        scrollPanel = new JPanel();
        scrollPanel.setLayout(new GridLayout(100, 1, 5, 5));
        historyList = HistoryActivity.getHistoryList(UserActivity.user);
        if (historyList == null) {
            JLabel label = new JLabel();
            label.setText("获取历史对局失败");
            label.setForeground(Color.WHITE);
            label.setFont(new Font("微软雅黑", 1, 30));
            label.setBounds((W - 200) / 2, (H - 50) / 2, 300, 50);
            JButton button = new JButton();
            button.setText("确认");
            button.setBackground(Color.WHITE);
            button.setFont(new Font("微软雅黑", 1, 30));
            button.setBounds((W - 200) / 2 + 50, (H - 50) / 2 + 70, W / 15, H / 20);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.getContentPane().removeAll();
                    frame.setContentPane(new GameLobbyPanel(frame));
                    frame.setVisible(true);
                }
            });
            add(label);
            add(button);
        }
        else {
            for (History h : historyList) {
                String s = new String();
                s += "{战局ID:" + h.getHID() + "} {牌型:" + h.getCard() + "} {得分:" + h.getScore() + '}';
                JButton b = new JButton(s);
                b.setFont(new Font("微软雅黑", 1, 23));
                b.setForeground(Color.WHITE);
                b.setBackground(new Color(55, 155, 211));
                //b.setContentAreaFilled(false);
                b.setPreferredSize(new Dimension(995, 70));
                scrollPanel.add(b);
            }
        }
    }

    @Override
    public void paintComponent(Graphics graphics) {
        graphics.drawImage(background, 0, 0, W, H, this);
    }

    private Image background;
    private int W;
    private int H;
    private JButton menu;
    private MyFrame frame;
    private ArrayList<History> historyList;
    private ImagePanel backPanel;
    private JPanel infoPanel;
    private ImagePanel playerIcon;
    private ImagePanel playerTitle;
    private JScrollPane infoScroll;
    private JPanel scrollPanel;
    private boolean inLobby;
}
