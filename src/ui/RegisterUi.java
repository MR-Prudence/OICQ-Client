package ui;

import control.Manager;
import util.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RegisterUi extends JFrame {
    private Integer height = 300;
    private Integer width = 400;

    public RegisterUi() {
        super("QQ注册");
        setResizable(false);
        setBounds((Screen.getScreenWidth() - width) / 2, (Screen.getScreenHeight() - height) / 2, width, height);
        context();
    }

    public void context() {

        Box verticalBox = Box.createVerticalBox();
        verticalBox.add(Box.createVerticalStrut(30));

        JLabel alert = new JLabel();
        alert.setBackground(Color.red);
        verticalBox.add(alert);

        verticalBox.add(Box.createVerticalStrut(20));

        Box nameInput = Box.createHorizontalBox();
        JLabel name = new JLabel("姓     名");
        JTextField nameField = new JTextField("杨周");
        nameInput.add(Box.createHorizontalStrut(100));
        nameInput.add(name);
        nameInput.add(Box.createHorizontalStrut(20));
        nameInput.add(nameField);
        nameInput.add(Box.createHorizontalStrut(100));
        verticalBox.add(nameInput);

        verticalBox.add(Box.createVerticalStrut(20));

        Box passInput = Box.createHorizontalBox();
        JLabel pass = new JLabel("密     码");
        JPasswordField passField = new JPasswordField("123456");
        passInput.add(Box.createHorizontalStrut(100));
        passInput.add(pass);
        passInput.add(Box.createHorizontalStrut(20));
        passInput.add(passField);
        passInput.add(Box.createHorizontalStrut(100));
        verticalBox.add(passInput);

        verticalBox.add(Box.createVerticalStrut(20));

        Box repassInput = Box.createHorizontalBox();
        JLabel repass = new JLabel("确认密码");
        JPasswordField repassField = new JPasswordField("");
        repassInput.add(Box.createHorizontalStrut(100));
        repassInput.add(repass);
        repassInput.add(Box.createHorizontalStrut(20));
        repassInput.add(repassField);
        repassInput.add(Box.createHorizontalStrut(100));
        verticalBox.add(repassInput);

        verticalBox.add(Box.createVerticalStrut(30));

        Box button = Box.createHorizontalBox();
        JButton registerB  = new JButton("注册");
        JButton loginB= new JButton("返回");
        button.add(registerB);
        button.add(Box.createHorizontalStrut(70));
        button.add(loginB);
        verticalBox.add(button);

        verticalBox.add(Box.createVerticalStrut(100));
        getContentPane().add(verticalBox);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                String name = nameField.getText();
                Manager manager = new Manager();
                manager.exit(name);
            }
        });

        loginB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FirstUi();
                setVisible(false);
            }
        });

        registerB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alert.setText("");
                String p = passField.getText().trim();
                if(p.length()==0||p.length()>20){
                    alert.setText("密码不合理，长度1～19，不为空格！");
                    return;
                }
                String rp = repassField.getText().trim();
                if(!p.equals(rp)){
                    alert.setText("两次输入密码不同！");
                    return;
                }
                String username = nameField.getText().trim();
                if(username.length()==0||username.length()>20){
                    alert.setText("姓名不合理，长度1～19，不为空格！");
                    return;
                }
                boolean isSuccess = new Manager().register(username,p);
                if(isSuccess){
                    setVisible(false);
                }else {
                    alert.setText("姓名已存在！");
                }
            }
        });
    }
}
