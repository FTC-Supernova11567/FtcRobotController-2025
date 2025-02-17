package org.firstinspires.ftc.teamcode.Utils;

public class Utils {

    public static double deadbend(double input, double deadbend){
        return Math.abs(input) > deadbend ? input : 0.0;
    }


}
