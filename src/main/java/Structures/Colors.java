package Structures;

import java.awt.*;

public class Colors {

    public static int BaseColor =0x10113d;
//    public static int MatchColor = 0x4a6fa5; // blue one
    public static int MatchColor = 0x232B5E
    ;

    public static Color getBaseColor(){
        return new Color(BaseColor);
    }
    public static Color getMatchColor(){
        return new Color(MatchColor);
    }


}
