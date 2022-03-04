package ui;

import control.Manager;
import util.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FirstUi extends JFrame {
    private Integer height = 300;
    private Integer width = 400;

    public FirstUi() {
        super("QQ登陆");
        setResizable(false);
        setBounds((Screen.getScreenWidth() - width) / 2, (Screen.getScreenHeight() - height) / 2, width, height);
        context();
    }

    public static void main(String[] args) {
        new FirstUi();
    }

    public void context() {

        Box verticalBox = Box.createVerticalBox();
        verticalBox.add(Box.createVerticalStrut(60));

        JLabel alert = new JLabel();
        alert.setBackground(Color.red);
        verticalBox.add(alert);

        verticalBox.add(Box.createVerticalStrut(20));

        Box nameInput = Box.createHorizontalBox();
        JLabel name = new JLabel("姓名");
        JTextField nameField = new JTextField("杨周");
        nameInput.add(Box.createHorizontalStrut(100));
        nameInput.add(name);
        nameInput.add(Box.createHorizontalStrut(20));
        nameInput.add(nameField);
        nameInput.add(Box.createHorizontalStrut(100));
        verticalBox.add(nameInput);

        verticalBox.add(Box.createVerticalStrut(20));

        Box passInput = Box.createHorizontalBox();
        JLabel pass = new JLabel("密码");
        JPasswordField passField = new JPasswordField("123456");
        passInput.add(Box.createHorizontalStrut(100));
        passInput.add(pass);
        passInput.add(Box.createHorizontalStrut(20));
        passInput.add(passField);
        passInput.add(Box.createHorizontalStrut(100));
        verticalBox.add(passInput);

        verticalBox.add(Box.createVerticalStrut(40));

        Box button = Box.createHorizontalBox();
        JButton loginB = new JButton("登陆");
        JButton registerB = new JButton("注册");
        button.add(loginB);
        button.add(Box.createHorizontalStrut(70));
        button.add(registerB);
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
                alert.setText("");
                String name = nameField.getText();
                String password = new String(passField.getPassword());
                Manager manager = new Manager();
                boolean isSuccess = manager.login(name,password);
                if(isSuccess){
                    setVisible(false);
                }else {
                    alert.setText("姓名或密码错误！");
                }
            }
        });

        registerB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterUi();
                setVisible(false);
            }
        });
    }
}
