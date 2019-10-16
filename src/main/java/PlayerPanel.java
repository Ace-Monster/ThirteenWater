import Client.RankActivity;
import Model.RankList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Font;

import static java.lang.Math.max;

public class PlayerPanel extends JPanel  {
    public PlayerPanel(MyFrame frame) {
        this.frame = frame;
        W = frame.getWidth();
        H = frame.getHeight();
        row = 0;
        col = 0;
        setLayout(null);
        background = new ImageIcon("pictures/tanfang.png").getImage();
        initHistoryList();
        if (rankList != null) {
            System.out.println(1);
            initComponents();
            add(menuPanel);
            add(menu);
            add(backPanel);
            backPanel.setLayout(null);
            backPanel.add(rankPanel);
            rankPanel.setLayout(null);
            rankPanel.add(ranklistHead);
            rankPanel.add(rankScroll);
        }

    }

    void initComponents() {
        ranklistHead = new ImagePanel("pictures/playerIcon.jpg");
        backPanel = new JPanel();
        menu = new JButton();
        rankPanel = new ImagePanel("pictures/menu.png");

        backPanel.setBackground(new Color(96, 96, 96, 120));
        backPanel.setBounds(W / 12, H / 7, 5 * W / 6, 5 * H / 7);

        menu.setText("菜单");
        menu.setFont(new Font("微软雅黑", 1, 30));
        menu.setForeground(Color.WHITE);
        menu.setContentAreaFilled(false);
        menu.setBounds(W - H / 6, 10, H / 7, H / 12);
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (menuPanel.isVisible() == false) {
                    menuPanel.setVisible(true);
                    updateUI();
                    repaint();
                }
            }
        });

        menuPanel = new MenuPanel(frame);
        menuPanel.setVisible(false);
        menuPanel.setBounds(W / 4, H / 4, W / 2, H / 2);

        //ranklistHead.setBounds(5 * W / 12 - 75, (H / 7 - 50) / 2, 150, 50);
        ranklistHead.setBounds((3 * W / 4 - 150) / 2, 45, 150, 50);

        //rankPanel.setBackground(new Color(254, 254, 254, 120));
        rankPanel.setBounds((5 * W / 6 - 3 * W / 4) / 2, 50, 3 * W / 4,  2 * H / 3);

        rankArea.setRows(row);
        rankArea.setColumns(col);
        rankArea.setFont(new Font("微软雅黑", 1, 20));
        rankArea.setBackground(new Color(16, 18, 53));
        rankArea.setEnabled(false);

        rankScroll = new JScrollPane(rankArea);
        //rankScroll.setBorder(null);
        rankScroll.setBounds(100, 100, 3 * W / 4 - 200, 2 * H / 3 - 150);
        rankScroll.getVerticalScrollBar().setValue(0) ;
    }

    void initHistoryList() {
        if (rankList == null) {
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
            rankArea = new JTextArea();
            Font f = new Font("微软雅黑", 1, 20);
            FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(f);
            int cnt = 0;
            for (RankList rank : rankList) {
                row++;
                String sRank = "\tRank " + ++cnt + "\tID:" + rank.getUID() + "\t用户名:" + rank.getName();
                String text = rank.getName();
                int textwidth = fm.stringWidth(text);

                //System.out.println(text + textwidth);

                if (textwidth > 93) sRank += "\t分数:" + rank.getScore() + "\n";
                else sRank += "\t\t分数:" + rank.getScore() + "\n";
                rankArea.append(sRank);
                col = max(col, sRank.length());
            }
        }
    }

    @Override
    public void paintComponent(Graphics graphics) {
        graphics.drawImage(background, 0, 0, W, H, this);
    }

    private ArrayList<RankList> rankList;
    private Image background;
    private int W;
    private int H;
    private JButton menu;
    private MyFrame frame;
    private JPanel backPanel;
    private MenuPanel menuPanel;
    private ImagePanel ranklistHead;
    private ImagePanel rankPanel;
    private JTextArea rankArea;
    private JScrollPane rankScroll;
    private int row;
    private int col;
}
