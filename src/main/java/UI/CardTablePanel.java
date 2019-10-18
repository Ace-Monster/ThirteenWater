package UI;

import Client.HistoryActivity;
import Client.UserActivity;
import Model.History;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CardTablePanel extends JPanel {
    public CardTablePanel(MyFrame frame) {
        this.frame = frame;
        W = frame.getWidth();
        H = frame.getHeight();
        setLayout(null);
        initComponents();

        add(menu);
        add(backPanel);
        backPanel.setLayout(null);
        backPanel.add(searchLabel);
        backPanel.add(searchPanel);
        backPanel.add(searchText);
        backPanel.add(confirmSearch);
    }
    void initComponents() {
        background = new ImageIcon("pictures/lobby.jpg").getImage();

        menu = new JButton();
        backPanel = new JPanel();
        searchPanel = new JPanel();
        searchLabel = new JLabel();
        searchText = new JTextField();
        confirmSearch = new JButton();

        menu = new JButton();
        menu.setText("关闭");
        menu.setFont(new Font("微软雅黑", 1, 30));
        menu.setForeground(Color.RED);
        menu.setContentAreaFilled(false);
        menu.setBounds(W - H / 6, 10, H / 7, H / 12);
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.setContentPane(new GameLobbyPanel(frame));
                frame.setVisible(true);
            }
        });

        backPanel.setBackground(new Color(96, 96, 96, 120));
        backPanel.setBounds(W / 12, H / 7, 5 * W / 6, 5 * H / 7);

        searchLabel.setText("查询往期对战结果：");
        searchLabel.setForeground(Color.WHITE);
        searchLabel.setFont(new Font("微软雅黑", 1, 30));
        searchLabel.setBounds(50, 10, W / 5, H / 10);

        searchText.setFont(new Font("微软雅黑", 1, 30));
        searchText.setBounds( W / 5, 35, W / 2, H / 20);

        searchPanel.setBackground(new Color(254, 254, 254, 120));
        searchPanel.setBounds((5 * W / 6 - 3 * W / 4) / 2, H / 7, 3 * W / 4,  H / 2);

        confirmSearch.setText("确认");
        confirmSearch.setBackground(Color.WHITE);
        confirmSearch.setFont(new Font("微软雅黑", 1, 30));
        confirmSearch.setBounds(W / 5 + W / 2 + W / 35, 35, W / 15, H / 20);
        confirmSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchPanel.removeAll();
                History history = HistoryActivity.getHistory(UserActivity.user, Integer.valueOf(searchText.getText()).intValue());
                if (history != null) {
                    ArrayList<History.Detail> details = history.getDetails();
                    for (History.Detail d : details) {
                        String s = new String();
                        s += "{用户ID:" + d.UID + "} {用户名:" + d.name + "} {牌型:" + d.card + "} {得分:" + d.score + '}';
                        JButton b = new JButton(s);
                        b.setFont(new Font("微软雅黑", 1, 23));
                        b.setForeground(Color.WHITE);
                        b.setBackground(new Color(55, 155, 211));
                        //b.setContentAreaFilled(false);
                        b.setPreferredSize(new Dimension(3 * W / 4 - 10, H / 8 - 5));
                        searchPanel.add(b);
                    }
                }
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
    private JPanel backPanel;
    private JPanel searchPanel;
    private JLabel searchLabel;
    private JTextField searchText;
    private JButton confirmSearch;
}
