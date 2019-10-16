import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitlePanel extends JPanel {

    public TitlePanel(MyFrame frame) {
        this.frame = frame;
        W = frame.getWidth();
        H = frame.getHeight();
        setLayout(null);
        initComponents();
        //initLayout();

        add(title);
        add(login);
        add(register);
    }

    /*private void initLayout() {
        setLayout(new GridBagLayout());
        for (int i = 0; i < 12; i++) {
            GridBagConstraints g = new GridBagConstraints();
            g.gridx = i;
            g.gridy = 0;
            JButton h = new JButton("   ");
            h.setContentAreaFilled(false);
            h.setBorderPainted(false);
            add(h, g);

            g.gridx = 0;
            g.gridy = i;
            JButton v = new JButton("   ");
            v.setContentAreaFilled(false);
            v.setBorderPainted(false);
            add(v, g);
        }
        loginLayout = new GridBagConstraints();
        registerLayout = new GridBagConstraints();
        titleLayout = new GridBagConstraints();

        titleLayout.gridx = 1;
        titleLayout.gridy = 1;
        titleLayout.gridwidth = 10;
        titleLayout.gridheight = 3;
        titleLayout.fill = GridBagConstraints.BOTH;
        titleLayout.insets = new Insets(0, 0, 5, 0);

        loginLayout.gridx = 3;
        loginLayout.gridy = 9;
        loginLayout.gridwidth = 6;
        loginLayout.gridheight = 3;
        loginLayout.fill = GridBagConstraints.BOTH;
        loginLayout.insets = new Insets(0, 0, 5, 0);

        registerLayout.gridx = 3;
        registerLayout.gridy = 12;
        registerLayout.gridwidth = 6;
        registerLayout.gridheight = 2;
        registerLayout.fill = GridBagConstraints.BOTH;
        registerLayout.insets = new Insets(5, 0, 0, 0);
    }*/

    private void initComponents() {
        background = new ImageIcon("pictures/yard.jpg").getImage();

        title = new JLabel();
        login = new JButton();
        register = new JButton();

        title.setText("福建十三水");
        title.setForeground(new Color(248, 152, 25));
        title.setFont(LoadFont.titleFont());
        title.setBounds((W - W / 3) / 2, 2 * H / 7, W / 3, H / 7);

        login.setText("登录");
        login.setBackground(new Color(55, 155, 211));
        login.setForeground(Color.WHITE);
        login.setFont(new Font("微软雅黑", 1, 20));
        login.setBounds((W - W / 8) / 2, 8 * H / 13, W / 8, H / 12);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.setContentPane(new LoginPanel(frame));
                frame.setVisible(true);
            }
        });

        register.setText("注册");
        register.setBackground(Color.WHITE);
        register.setFont(new Font("微软雅黑", 1, 20));
        register.setBounds((W - W / 8) / 2, 8 * H / 13 + H / 10, W / 8, H / 24);
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.setContentPane(new RegisterPanel(frame));
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
    private JLabel title;
    private JButton login;
    private JButton register;
    private Image background;
    /*private GridBagConstraints loginLayout;
    private GridBagConstraints registerLayout;
    private GridBagConstraints titleLayout;*/

}
