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

    public void anglePlace(double angle){

    }

    public void collectAngle(){
        if(gripperGamepad.right_stick_y < 0){
            gripperAngleServo.turnToAngle(222);
    }
        }

        public void outtakeAngle(){
         if(gripperGamepad.right_stick_y > 0){
                gripperAngleServo.turnToAngle(225);
            }
        }


    public double getAngle(){
        return gripperAngleServo.getAngle();
    }

    public void buttonControl() {
        turnADirction();
        turnBDirction();
    }



    public void turnADirction() {
        if (gripperGamepad.a) {
            gripperCatcherServo.turnToAngle(360);
        }
    }

    public void turnBDirction(){
        if(gripperGamepad.b){
            gripperCatcherServo.turnToAngle(0);
        }
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
