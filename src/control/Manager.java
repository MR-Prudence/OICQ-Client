package control;

import model.MyFrame;
import network.Client;
import ui.FriendUi;
import ui.SessionUi;
import util.Parser;
import util.Protocol;

import javax.swing.*;
import java.util.*;
import java.util.List;

public class Manager {

    public static HashMap<String, MyFrame> friends = new HashMap<>();
    private static JFrame frame;
    private Protocol protocol = new Protocol();

    public void exit(String name) {
        try {
            Client.write(protocol.exitProtocol(name));
        } catch (Exception e) {
            //处理服务器未启动的异常
        } finally {
            System.exit(0);
        }
    }

    public boolean login(String name, String password) {
        Client.write(protocol.loginProtocol(name,password));
        String read = Client.read();
        if ("false".equals(read)) {
            return false;
        } else {
            Parser parser = new Parser(read);
            List<String[]> strings = new ArrayList(parser.myParser());
            Iterator<String[]> iterator = strings.iterator();
            boolean isFriend = false;
            int i = -1;
            while (iterator.hasNext()) {
                i++;
                String[] next = iterator.next();
                if (next.length == 1 && "friend".equals(next[0])) {
                    isFriend = true;
                    continue;
                }
                if (isFriend) {
                    i--;
                    this.friends.put(next[0], new MyFrame(new JTextArea(),new JLabel()));//添加好友聊天内容框和消息提示框
                }
            }
            //此为未离线时未接收的数据
            strings = strings.subList(0, i);
            new FriendUi(name);
            if (strings.size() != 1) {
                strings.remove(0);
                doAccept(strings);
            }
            Client.StartThread();
            return true;
        }
    }

    public boolean register(String name, String password) {
        Client.write(protocol.registerProtocol(name,password));
        String read = Client.read();
        System.out.println(read);
        if ("false".equals(read)) {
            return false;
        }
        new FriendUi(name);
        Client.StartThread();
        return true;
    }

    public  void doAccept(List<String[]> strings) {
        Iterator<String[]> iterator = strings.iterator();
        while (iterator.hasNext()) {
            String[] next = iterator.next();
            String context = next[0];
            String name = next[1];
            JTextArea area = friends.get(name).getArea();
            if (area != null) {
                area.append(name + ":" + context + "\n");
                area.paintImmediately(area.getBounds());
                flushNewsLabel(name, "新消息");
            } else {
                if (friends.containsKey(name)) {
                    friends.put(name,new MyFrame(new JTextArea(context),new JLabel()));
                }
            }
        }
    }

    public void submit(String name, String targetName, String text) {
        Client.write(protocol.submitProtocol(name,text,targetName));
    }

    public void startSession(String name, String friendName) {
        JTextArea area = null;
        if (friends.containsKey(friendName)) {
            area = friends.get(friendName).getArea();
        } else {
            area = new JTextArea();
            friends.put(name,new MyFrame(area,new JLabel()));
        }
        flushNewsLabel(friendName, "");
        new SessionUi(name, friendName, area);
    }

    private void flushNewsLabel(String friendName, String context) {
        JLabel newsLabel = friends.get(friendName).getNewsLabel();
        if (newsLabel != null) {
            newsLabel.setText(context);
            newsLabel.paintImmediately(newsLabel.getBounds());
        }
    }

    public void putSystemAlert(JLabel label, JFrame frame) {
        this.frame = frame;
        label.setText("在线！");
    }

    public void reAppear() {
        this.frame.setVisible(true);
    }

    public void flushSystemAlert(List<String[]> list) {
        Iterator<String[]> iterator = list.iterator();
        while (iterator.hasNext()) {
            String[] next = iterator.next();
            String context = next[0];
            String name = next[1];
            if (!friends.containsKey(name)) {
                friends.put(name, new MyFrame(new JTextArea("\n"),new JLabel()));
            } else {
                friends.remove(name);
            }
            JFrame frame = this.frame;
            frame.setVisible(false);
            String title = frame.getTitle();
            int start = title.indexOf("[") + 1;
            int end = title.lastIndexOf("]");
            new FriendUi(title.substring(start, end));
        }
    }

    public void addFriend(String name, String targetName) {
        friends.put(targetName,new MyFrame(new JTextArea(),new JLabel()));
        Client.write(protocol.addFriendProtocol(name,targetName));
        new FriendUi(name);
    }

    public void delFriend(String name, String targetName) {
        //此处应该把已经打开的聊天框一起关闭，暂未实现，将导致添加新朋友失败，和无法朋友之间同步信息
        friends.remove(targetName);
        Client.write(protocol.delFriendProtocol(name,targetName));
        new FriendUi(name);
    }
}
