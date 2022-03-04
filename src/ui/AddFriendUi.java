package ui;

import control.Manager;
import util.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFriendUi extends JFrame {
    private String name;
    private Integer height = 300;
    private Integer width = 400;

    public AddFriendUi(String name){
        super("添加好友");
        this.name = name;
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds((Screen.getScreenWidth() - width) / 2, (Screen.getScreenHeight() - height) / 2, width, height);
        context();
    }

    private void context() {
        Box verticalBox = Box.createVerticalBox();
        verticalBox.add(Box.createVerticalStrut(60));

        JLabel alert = new JLabel();
        alert.setBackground(Color.red);
        verticalBox.add(alert);

        verticalBox.add(Box.createVerticalStrut(20));

        Box nameInput = Box.createHorizontalBox();
        JLabel n = new JLabel("你看上谁了？");
        JTextField nameField = new JTextField("杨周");
        nameInput.add(Box.createHorizontalStrut(100));
        nameInput.add(n);
        nameInput.add(Box.createHorizontalStrut(20));
        nameInput.add(nameField);
        nameInput.add(Box.createHorizontalStrut(100));
        verticalBox.add(nameInput);

        verticalBox.add(Box.createVerticalStrut(40));

        Box button = Box.createHorizontalBox();
        JButton addB = new JButton("添加");
        JButton exitB = new JButton("取消");
        button.add(addB);
        button.add(Box.createHorizontalStrut(70));
        button.add(exitB);
        verticalBox.add(button);

        verticalBox.add(Box.createVerticalStrut(100));
        getContentPane().add(verticalBox);
        setVisible(true);

        exitB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Manager().reAppear();
                setVisible(false);
            }
        });

        addB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Manager().addFriend(name,nameField.getText());
                setVisible(false);
            }
        });
    }
}
