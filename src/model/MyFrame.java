package model;

import javax.swing.*;

public class MyFrame {
    private JTextArea area = new JTextArea();
    private JLabel newsLabel = new JLabel();

    public MyFrame(JTextArea area, JLabel newsLabel) {
        this.area = area;
        this.newsLabel = newsLabel;
    }

    public JTextArea getArea() {
        return area;
    }

    public void setArea(JTextArea area) {
        this.area = area;
    }

    public JLabel getNewsLabel() {
        return newsLabel;
    }

    public void setNewsLabel(JLabel newsLabel) {
        this.newsLabel = newsLabel;
    }
}
