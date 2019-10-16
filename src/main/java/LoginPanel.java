import Client.UserActivity;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    public LoginPanel(MyFrame frame) {
        this.frame = frame;
        W = frame.getWidth();
        H = frame.getHeight();
        setLayout(null);
        initComponents();

        add(backPanel);
        backPanel.setLayout(null);
        add(title);
        backPanel.add(goBack);
        backPanel.add(login);
        backPanel.add(userLab);
        backPanel.add(pwdLab);
        backPanel.add(autoPwd);
        backPanel.add(autoLogin);
        backPanel.add(user);
        backPanel.add(pwd);
        add(loginSuccess);
        loginSuccess.setLayout(null);
        loginSuccess.add(successLabel);
        loginSuccess.add(successConfirm);
    }

    private void initComponents() {
        background = new ImageIcon("pictures/yard.jpg").getImage();

        title = new JLabel();
        goBack = new JButton();
        backPanel = new JPanel();
        login = new JButton();
        userLab = new JLabel();
        pwdLab = new JLabel();
        autoPwd = new JCheckBox();
        autoLogin = new JCheckBox();
        user = new JTextField();
        pwd = new JPasswordField();
        successLabel = new JLabel();
        successConfirm = new JButton();

        title.setText("福建十三水");
        title.setForeground(new Color(248, 152, 25));
        title.setFont(LoadFont.titleFont());
        title.setBounds((W - W / 3) / 2, 2 * H / 7, W / 3, H / 7);

        goBack.setText("<");
        goBack.setFont(new Font("微软雅黑", 1, 20));
        goBack.setBackground(new Color(55, 155, 211));
        goBack.setForeground(Color.WHITE);
        goBack.setBounds(0, 0, W / 24,H / 18);
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.setContentPane(new TitlePanel(frame));
                frame.setVisible(true);
            }
        });

        backPanel.setBackground(new Color(96, 96, 96, 120));
        backPanel.setBounds((W - W / 3) / 2, 4 * H / 7, W / 3, 3 * H / 8);

        login.setText("登录");
        login.setBackground(Color.WHITE);
        login.setFont(new Font("微软雅黑", 1, 20));
        login.setBounds((W / 3 - 2 * W / 15) / 2,  H / 4 + H / 64, 2 * W / 15, H / 24);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //UserActivity.user = UserActivity.login(new User(user.getText(), pwd.getText()));
                backPanel.setVisible(false);
                loginSuccess.setVisible(true);
                if (UserActivity.user == null)
                    successLabel.setText("登录失败");
                else
                    successLabel.setText("登录成功");
                updateUI();
                repaint();
            }
        });

        userLab.setText("用户名");
        userLab.setFont(new Font("微软雅黑", 1, 20));
        userLab.setForeground(Color.WHITE);
        userLab.setBounds(H / 24, W / 28, W / 24, H / 18);

        pwdLab.setText("密   码");
        pwdLab.setFont(new Font("微软雅黑", 1, 20));
        pwdLab.setForeground(Color.WHITE);
        pwdLab.setBounds(H / 24, W / 14, W / 24, H / 18);

        user.setFont(new Font("微软雅黑", 1, 20));
        user.setBounds(W / 12, 23 * H / 308, W / 6, H / 30);

        pwd.setFont(new Font("微软雅黑", 1, 20));
        pwd.setBounds(W / 12, 89 * H / 616, W / 6, H / 30);

        autoPwd.setText("记住密码");
        //autoPwd.setForeground(new Color(0, 0, 0, 0));
        autoPwd.setFont(new Font("微软雅黑", 0, 15));
        autoPwd.setBounds(W / 12, 16 * H / 77, W / 12, H / 36);

        autoLogin.setText("自动登录");
        //autoLogin.setForeground(new Color(0, 0, 0, 0));
        autoLogin.setFont(new Font("微软雅黑", 0, 15));
        autoLogin.setBounds(W / 6, 16 * H / 77, W / 12, H / 36);

        loginSuccess = new ImagePanel("pictures/registerSuccess.png");
        loginSuccess.setBounds((W - W / 3) / 2, 4 * H / 7, W / 3, 3 * H / 8);
        loginSuccess.setVisible(false);

        successLabel.setForeground(Color.WHITE);
        successLabel.setFont(new Font("微软雅黑", 1, 40));
        successLabel.setBounds(W / 9, H / 12, W / 6, H / 8);

        successConfirm.setText("确认");
        successConfirm.setBackground(Color.WHITE);
        successConfirm.setFont(new Font("微软雅黑", 1, 30));
        successConfirm.setBounds(W / 12, H / 4, W / 6, H / 20);
        successConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (UserActivity.user == null) {
                    frame.getContentPane().removeAll();
                    //frame.setContentPane(new TitlePanel(frame));
                    frame.setContentPane(new GameLobbyPanel(frame));
                    frame.setVisible(true);
                }
                else {
                    frame.getContentPane().removeAll();
                    frame.setContentPane(new GameLobbyPanel(frame));
                    frame.setVisible(true);
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
    private JLabel title;
    private JButton goBack;
    private JPanel backPanel;
    private JButton login;
    private JLabel userLab;
    private JLabel pwdLab;
    private JCheckBox autoPwd;
    private JCheckBox autoLogin;
    private JTextField user;
    private JPasswordField pwd;
    private ImagePanel loginSuccess;
    private JLabel successLabel;
    private JButton successConfirm;
}
