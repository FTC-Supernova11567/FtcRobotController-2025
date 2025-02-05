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
        gripperAngleServo = new SimpleServo(constHardwareMap, "angle", 33 , 48, AngleUnit.DEGREES);

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

    public double getPosition() {
        return gripperAngleServo.getPosition();
    }
    public double getAngle() {
        return gripperAngleServo.getAngle();
    }

    public void spinForward(){
        gripperCatcherServo.set(1);
    }

    public void spinBackward(){
        gripperCatcherServo.set(-1);
    }

    public void stopSpinning(){
        gripperCatcherServo.set(0);
    }

    public void catcher() {
        if (catcherGamepad.right_trigger != 0) {
            spinForward();
        }
        if (catcherGamepad.left_trigger != 0) {
            spinBackward();
        }
        if (catcherGamepad.left_trigger == 0 && catcherGamepad.right_trigger == 0) {
            stopSpinning();
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

    public void changeAngle(double angle){
        gripperAngleServo.turnToAngle(angle);
    }
    public void angleJoystick() {
        if (angleGamepad.x){
            changeAngle(40); // Best angle to drop game piece in lower basket and high basket - front robot
        }
        else if(angleGamepad.a){
            changeAngle(35.5); // Best angle to drop game piece in high basket - behind robot, and collect from ground
        }
        else if (angleGamepad.right_stick_y < 0){
            gripperAngleServo.rotateByAngle(-0.3);
        }
        else if(angleGamepad.right_stick_y > 0){
            gripperAngleServo.rotateByAngle(0.3);
        }
        else if (angleGamepad.right_stick_y == 0){
            gripperAngleServo.rotateByAngle(0);
        }



    }
    public void angleRoatateUp(){
        gripperAngleServo.rotateBy(0.03);
    }

    public void angleRoataeDown(){
        gripperAngleServo.rotateBy(-0.03);
    }

    public void angleRoatateStop(){
        gripperAngleServo.rotateBy(0.0);
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
