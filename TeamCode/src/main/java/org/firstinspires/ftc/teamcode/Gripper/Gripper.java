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
        gripperAngleServo = new SimpleServo(constHardwareMap, "angle", 180 , 225, AngleUnit.DEGREES);

        catcherGamepad = constCatcherGamepad;
        angleGamepad = constAngleGamepad;

    }

    public void turnToCollect() {
        gripperAngleServo.turnToAngle(215);
    }

    public void turnToOuttake() {
        gripperAngleServo.turnToAngle(225);
    }

    public double getAngle() {
        return gripperAngleServo.getAngle();
    }

    public void spinForward() {
        gripperCatcherServo.set(1);
    }

    public void spinBackward() {
        gripperCatcherServo.set(-1);
    }

    public void stopCatcher(){
        if (catcherGamepad.x){

            gripperCatcherServo.set(0);
        }
    }

//    public void collectAngleRightstickY() {
//        if (gripperGamepad.right_stick_y < 0) {
//            turnToCollect();
//        }
//    }

//    public void outtakeAngleRightstickY() {
//        if (gripperGamepad.right_stick_y > 0) {
//            turnToOuttake();
//        }
//    }

    public void turnAForward() {
        if (catcherGamepad.a) {
            spinForward();
        }
        else{
            stopCatcher();
        }
    }

    public void turnBBackwards() {
        if (catcherGamepad.b) {
            spinBackward();
        }
        else {
            stopCatcher();
        }
    }

    public void angleJoystick() {
        if (angleGamepad.right_stick_y < 0){
            gripperAngleServo.setPosition(gripperAngleServo.getPosition() - 0.001);
        }else if(angleGamepad.right_stick_y > 0){
            gripperAngleServo.setPosition(gripperAngleServo.getPosition() + 0.001);
        }
        else{
            gripperAngleServo.rotateBy(0);
        }

    }




    public void buttonControl() {
        turnAForward();
        turnBBackwards();
    }

    public void gripperControl() {
        angleJoystick();
        buttonControl();
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
