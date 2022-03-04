package util;

import java.awt.*;

public class Screen {

    public static Integer getScreenHeight(){
        return Toolkit.getDefaultToolkit().getScreenSize().height;
    }

    public static Integer getScreenWidth(){
        return Toolkit.getDefaultToolkit().getScreenSize().width;
    }

}
