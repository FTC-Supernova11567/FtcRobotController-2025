package org.firstinspires.ftc.teamcode.Gripper;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


public class Gripper {
    private final ServoEx gripperCatcherServo;
    private final ServoEx gripperAngleServo;
    private Gamepad gripperGamepad;

    public Gripper(HardwareMap constHardwareMap, Gamepad constGamepad){
        gripperCatcherServo = new SimpleServo(constHardwareMap, "catcher", 0, 360, AngleUnit.DEGREES);

        gripperAngleServo = new SimpleServo(constHardwareMap, "angle", 222, 225,AngleUnit.DEGREES);

        gripperGamepad = constGamepad;
    }

    public void turnToCollect(){
        gripperAngleServo.turnToAngle(222);
    }

    public void turnToOuttake(){
        gripperAngleServo.turnToAngle(225);
    }

    public double getAngle(){
        return gripperAngleServo.getAngle();
    }

    public void spinForward(){
        gripperCatcherServo.turnToAngle(360);
    }

    public void spinBackward(){
        gripperCatcherServo.turnToAngle(0);
    }

    public void collectAngleRightstickY(){
        if(gripperGamepad.right_stick_y < 0){
            turnToCollect();
        }
    }

    public void outtakeAngleRightstickY(){
        if(gripperGamepad.right_stick_y > 0){
            turnToOuttake();
        }
    }

    public void turnAForward() {
        if (gripperGamepad.a) {
            spinForward();
        }
    }

    public void turnBBackwards(){
        if(gripperGamepad.b){
            spinBackward();
        }
    }

    public void angleJoystick(){
        collectAngleRightstickY();
        outtakeAngleRightstickY();
    }


    public void buttonControl() {
        turnAForward();
        turnBBackwards();
    }

    public void gripperControl(){
        angleJoystick();
        buttonControl();
    }


    public String getDirection(){
        String direction;
        if (gripperCatcherServo.getAngle() == 360){
            direction = "catching";
        }
        else if(gripperCatcherServo.getAngle() == 0){
            direction = "release" ;
        }
        else{
            direction = "hasn't started yet";
        }
        return direction;
    }
}
