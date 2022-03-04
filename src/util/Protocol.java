package util;

public class Protocol {
    public Protocol() {
    }

    public String protocol(String method,String submitName,String content,String targetName){
        return method + "&" + submitName + "&" + content + "&" + targetName + "&" + "\n";
    }

    public String addFriendProtocol(String submitName,String targetName){
        return "addFriend&" + submitName + "&" + targetName + "&" + "\n";
    }

    public String delFriendProtocol(String submitName,String targetName){
        return "delFriend&" + submitName + "&" + targetName + "&" + "\n";
    }

    public String submitProtocol(String submitName,String content,String targetName){
        return "submit&" + submitName + "&" + content + "&" + targetName + "&\n";
    }

    public String registerProtocol(String name,String password){
        return "register&" + name + "&" + password + "&\n";
    }

    public String loginProtocol(String name,String password){
        return "login&" + name.trim() + "&" + password.trim() + "&\n";
    }
    public String exitProtocol(String name){
        return "exit&" + name + "&\n";
    }
}
