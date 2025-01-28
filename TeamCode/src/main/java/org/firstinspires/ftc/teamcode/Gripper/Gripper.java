package org.firstinspires.ftc.teamcode.Gripper;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


public class Gripper {
    //    private final ServoEx gripperCatcherServo;
    private final CRServo gripperCatcherServo;
    private final ServoEx gripperAngleServo;
    private Gamepad catcherGamepad;
    private Gamepad angleGamepad;

    public Gripper(HardwareMap constHardwareMap, Gamepad constCatcherGamepad, Gamepad constAngleGamepad) {
        gripperCatcherServo = new CRServo(constHardwareMap, "catcher");
        gripperAngleServo = new SimpleServo(constHardwareMap, "angle", 222 , 223, AngleUnit.DEGREES);

        catcherGamepad = constCatcherGamepad;
        angleGamepad = constAngleGamepad;

    }

//    public void turnToCollect() {
//        gripperAngleServo.turnToAngle(215);
//    }
//
//    public void turnToOuttake() {
//        gripperAngleServo.turnToAngle(225);
//    }

    public double getAngle() {
        return gripperAngleServo.getAngle();
    }

    public void catcher() {
       if (catcherGamepad.right_trigger != 0) {
           gripperCatcherServo.set(1);
       }
       if (catcherGamepad.left_trigger != 0) {
           gripperCatcherServo.set(-1);
       }
       if (catcherGamepad.left_trigger == 0 && catcherGamepad.right_trigger == 0){
        gripperCatcherServo.set(0);
       }
    }


    //public void collectAngleRightstickY() {
    //    if (gripperGamepad.right_stick_y < 0) {
    //        turnToCollect();
    //    }
    //}

   // public void outtakeAngleRightstickY() {
   //     if (gripperGamepad.right_stick_y > 0) {
   //         turnToOuttake();
   //     }
   //}

    //public void turnAForward() {
    //    if (catcherGamepad.right_trigger != 0) {
    //        catcher();
    //    }
    //    if(catcherGamepad.right_trigger == 0){
    //        stopCatcher();
    //    }
    //}

    //public void turnBBackwards() {

    public void angleJoystick() {
        if (angleGamepad.right_stick_y < 0){
            gripperAngleServo.setPosition(gripperAngleServo.getPosition() - 0.0025);
        }else if(angleGamepad.right_stick_y > 0){
            gripperAngleServo.setPosition(gripperAngleServo.getPosition() + 0.0025);
        }
        else if (angleGamepad.right_stick_y == 0){
            gripperAngleServo.rotateBy(0);
        }

    }




    //public void buttonControl() {
    //    turnAForward();
    //    turnBBackwards();
    //}

    public void gripperControl() {
       angleJoystick();
       catcher();
    }


//    public String getDirection() {
//        String direction;
//        if (gripperCatcherServo.getAngle() == 360) {
//            direction = "catching";
//        } else if (gripperCatcherServo.getAngle() == 0) {
//            direction = "release";
//        } else {
//            direction = "hasn't started yet";
//        }
//        return direction;
//    }
}
