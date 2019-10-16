import Client.UserActivity;
import Model.User;

import javax.jws.soap.SOAPBinding;
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPanel extends JPanel {
    public RegisterPanel(MyFrame frame) {
        this.frame = frame;
        W = frame.getWidth();
        H = frame.getHeight();
        setLayout(null);
        initComponents();

        add(backPanel);
        backPanel.setLayout(null);
        add(title);
        backPanel.add(goBack);
        backPanel.add(userLabel);
        backPanel.add(pwdLabel);
        backPanel.add(confirmPwdLabel);
        backPanel.add(user);
        backPanel.add(pwd);
        backPanel.add(confirmPwd);
        backPanel.add(register);
        add(registerSuccess);
        registerSuccess.setLayout(null);
        registerSuccess.add(successLabel);
        registerSuccess.add(successConfirm);
    }

    private void initComponents() {
        background = new ImageIcon("pictures/yard.jpg").getImage();

        title = new JLabel();
        goBack = new JButton();
        backPanel = new JPanel();
        userLabel = new JLabel();
        pwdLabel = new JLabel();
        confirmPwdLabel = new JLabel();
        user = new JTextField();
        pwd = new JPasswordField();
        confirmPwd = new JPasswordField();
        register = new JButton();
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

        userLabel.setText("用户名");
        userLabel.setFont(new Font("微软雅黑", 1, 20));
        userLabel.setForeground(Color.WHITE);
        userLabel.setBounds(W / 28, H / 16, W / 24, H / 18);

        pwdLabel.setText("密   码");
        pwdLabel.setFont(new Font("微软雅黑", 1, 20));
        pwdLabel.setForeground(Color.WHITE);
        pwdLabel.setBounds(W / 28, 2 * H / 16, W / 24, H / 18);

        confirmPwdLabel.setText("确认密码");
        confirmPwdLabel.setFont(new Font("微软雅黑", 1, 20));
        confirmPwdLabel.setForeground(Color.WHITE);
        confirmPwdLabel.setBounds(W / 33, 3 * H / 16, W / 20, H / 18);

        user.setFont(new Font("微软雅黑", 1, 20));
        user.setBounds(W / 12, 23 * H / 308, W / 6, H / 30);

        pwd.setFont(new Font("微软雅黑", 1, 20));
        pwd.setBounds(W / 12, 85 * H / 616, W / 6, H / 30);

        confirmPwd.setFont(new Font("微软雅黑", 1, 20));
        confirmPwd.setBounds(W / 12, 61 * H / 308, W / 6, H / 30);

        register.setText("注册");
        register.setBackground(Color.WHITE);
        register.setFont(new Font("微软雅黑", 1, 20));
        register.setBounds((W / 3 - 2 * W / 15) / 2,  H / 4 + H / 64, 2 * W / 15, H / 24);
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                success = UserActivity.register(new User(user.getText(), pwd.getText()));
                backPanel.setVisible(false);
                registerSuccess.setVisible(true);
                if (success)
                    successLabel.setText("注册成功");
                else
                    successLabel.setText("注册失败");
                updateUI();
                repaint();
            }
        });

        registerSuccess = new ImagePanel("pictures/registerSuccess.png");
        registerSuccess.setBounds((W - W / 3) / 2, 4 * H / 7, W / 3, 3 * H / 8);
        registerSuccess.setVisible(false);

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
                frame.getContentPane().removeAll();
                frame.setContentPane(new TitlePanel(frame));
                frame.setVisible(true);
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
    private JLabel userLabel;
    private JLabel pwdLabel;
    private JLabel confirmPwdLabel;
    private JTextField user;
    private JPasswordField pwd;
    private JPasswordField confirmPwd;
    private JButton register;
    private ImagePanel registerSuccess;
    private JLabel successLabel;
    private JButton successConfirm;
    private boolean success;
}
