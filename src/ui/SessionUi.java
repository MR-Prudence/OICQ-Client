package ui;

import control.Manager;
import util.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SessionUi extends JFrame {
    private Integer height = 500;
    private Integer width = 400;
    private String name;
    private String targetName;
    private JTextArea area;

    public SessionUi(String name, String targetName,JTextArea area) {
        super("QQ聊天[" + targetName + "]");
        this.name = name;
        this.targetName = targetName;
        this.area = area;
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds((Screen.getScreenWidth() - width) / 2, (Screen.getScreenHeight() - height) / 2, width, height);
        context();
    }


    public void context() {
        area.setEnabled(false);
        this.add(area, BorderLayout.CENTER);

        JTextField submitText = new JTextField();
        Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add(submitText);
        horizontalBox.add(Box.createHorizontalStrut(10));
        JButton submit = new JButton("发送");
        horizontalBox.add(submit);
        Box verticalBox = Box.createVerticalBox();
        verticalBox.add(Box.createVerticalStrut(20));
        verticalBox.add(horizontalBox);
        verticalBox.add(Box.createVerticalStrut(20));
        this.add(verticalBox, BorderLayout.SOUTH);
        setVisible(true);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = submitText.getText();
                submitText.setText("");
                area.append(name + " : " + text + "\n");
                area.paintImmediately(area.getBounds());
                new Manager().submit(name,targetName,text);
            }
        });
    }
}
