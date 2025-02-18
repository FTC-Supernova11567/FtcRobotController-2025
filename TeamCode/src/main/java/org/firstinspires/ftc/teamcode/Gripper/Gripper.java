package org.firstinspires.ftc.teamcode.Gripper;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


public class Gripper {
    private final ServoEx catcherServo;
    private final ServoEx angleServo;

    public Gripper(HardwareMap constHardwareMap) {
        catcherServo = new SimpleServo(constHardwareMap, "catcher", 0 , 1, AngleUnit.DEGREES);
        angleServo = new SimpleServo(constHardwareMap, "angle", -1 , 1, AngleUnit.DEGREES);

        catcherServo.turnToAngle(0);
        angleServo.turnToAngle(0);


    }

    public void moveHook(double angle){catcherServo.turnToAngle(angle);}

    public void turnToAngle(double angle){
        angleServo.turnToAngle(angle);}
    public void moveByAngle(double angleChange){
        angleServo.rotateByAngle(angleChange);
    }

    public double getPosition() {
        return angleServo.getPosition();
    }
    public double getAngle() {
        return angleServo.getAngle();
    }

}
