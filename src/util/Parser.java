package util;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public String[] strings;

    public Parser(String str){

        this.strings = str.split(",");
    }

    public List<String[]> myParser(){
        ArrayList<String[]> list = new ArrayList<>();
        for (int i = 0; i < this.strings.length; i++) {
            String[] split = strings[i].split("&");
            list.add(split);
        }
        return list;
    }
}
