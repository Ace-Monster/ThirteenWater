package UI;

import Client.HistoryActivity;
import Client.UserActivity;
import Model.History;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardTablePanel extends JPanel {
    public CardTablePanel(MyFrame frame) {
        this.frame = frame;
        W = frame.getWidth();
        H = frame.getHeight();
        setLayout(null);
        initComponents();

        add(menu);
        add(menuPanel);
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
                History history = HistoryActivity.getHistory(UserActivity.user, Integer.valueOf(searchText.getText()).intValue());
                if (history != null) {
                    System.out.println(history.getCard());
                    System.out.println(history.getHID());
                    System.out.println(history.getScore());
                    System.out.println(history.getTimeStamp());
                }
                else {
                    System.out.println(1);
                }
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
    private MenuPanel menuPanel;
    private JButton menu;
    private JPanel backPanel;
    private JPanel searchPanel;
    private JLabel searchLabel;
    private JTextField searchText;
    private JButton confirmSearch;
}
