package ui;

import control.Manager;
import model.MyFrame;
import network.Client;
import util.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class FriendUi extends JFrame {
    private Integer height = 600;
    private Integer width = 200;
    private String name;

    public FriendUi(String name) {
        super("QQ好友[" + name + "]");
        this.name = name;
        setResizable(false);
        setBounds(Screen.getScreenWidth() - width * 2, (Screen.getScreenHeight() - height) / 2, width, height);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Client.write("exit&" + name + "&\n");
                System.exit(0);
            }
        });
        context();
        setVisible(true);
    }

    private void context() {

        JPanel jPanel = new JPanel();


        Manager manager = new Manager();
        HashMap<String, MyFrame> friends = Manager.friends;
        Set<String> strings = friends.keySet();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            String friendName = iterator.next();
            JButton button = new JButton(friendName);
            button.setName(friendName);
            button.setFont(new Font("PingFang SC", Font.PLAIN, 14));
            button.setPreferredSize(new Dimension(180, 40));
            JLabel label = friends.get(friendName).getNewsLabel();
            label.setFont(new Font("PingFang SC", Font.BOLD, 6));
            label.setName(friendName);
//            manager.newsLabel(friendName, label);
            Box horizontalBox = Box.createHorizontalBox();
            horizontalBox.add(Box.createHorizontalStrut(180));
            horizontalBox.add(label);
            horizontalBox.add(Box.createHorizontalStrut(15));

            jPanel.add(Box.createVerticalStrut(10));
            jPanel.add(horizontalBox);
            jPanel.add(button);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Manager().startSession(name, friendName);
                }
            });
        }


        jPanel.setPreferredSize(new Dimension(200, friends.size() * 50));

        this.add(jPanel, BorderLayout.CENTER);

        Box bottomB = Box.createHorizontalBox();
        JButton addFriendB = new JButton("添加好友");
        JButton delFriendB = new JButton("删除好友");
        bottomB.add(Box.createHorizontalStrut(2));
        bottomB.add(addFriendB);
        bottomB.add(Box.createHorizontalStrut(2));
        bottomB.add(delFriendB);
        bottomB.add(Box.createHorizontalStrut(2));

        this.add(bottomB, BorderLayout.SOUTH);

        Box topBox = Box.createHorizontalBox();
        JLabel label = new JLabel("11111");
        manager.putSystemAlert(label,this);
        topBox.add(Box.createHorizontalStrut(2));
        topBox.add(label);
        topBox.add(Box.createHorizontalStrut(2));

        this.add(topBox, BorderLayout.NORTH);

        addFriendB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddFriendUi(name);
                setVisible(false);
            }
        });

        delFriendB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DelFriendUi(name);
                setVisible(false);
            }
        });
    }

}
