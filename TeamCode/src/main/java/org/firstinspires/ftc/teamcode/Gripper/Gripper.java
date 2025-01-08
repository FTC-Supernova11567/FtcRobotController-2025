package org.firstinspires.ftc.teamcode.Gripper;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp

public class Gripper extends OpMode {
//    private ServoEx gripperCatcherServo;
    private ServoEx gripperAngleServo;
    double gamepadStickPlace = 0;

    @Override
    public void init() {
//        gripperCatcherServo = new SimpleServo(hardwareMap, "catcher", 0, Double.POSITIVE_INFINITY, AngleUnit.DEGREES);
        gripperAngleServo = new SimpleServo(hardwareMap, "angle", 0, 270, AngleUnit.DEGREES);
    }

    @Override
    public void loop() {
//        if(gamepad1.a){
//            gripperCatcherServo.rotateBy(0.1);
//        }else{
//            gripperCatcherServo.turnToAngle(0);
//        }
        if (gamepad1.right_stick_y == 1){
            gripperAngleServo.setPosition(1);
        }if(gamepad1.right_stick_y == 0.9){
            gripperAngleServo.setPosition(0.95);
        }if(gamepad1.right_stick_y == 0.8){
            gripperAngleServo.setPosition(0.90);
        }if(gamepad1.right_stick_y == 0.7){
            gripperAngleServo.setPosition(0.85);
        }if(gamepad1.right_stick_y == 0.6){
            gripperAngleServo.setPosition(0.80);
        }if(gamepad1.right_stick_y == 0.5){
            gripperAngleServo.setPosition(0.75);
        }if(gamepad1.right_stick_y == 0.4){
            gripperAngleServo.setPosition(0.70);
        }if(gamepad1.right_stick_y == 0.3){
            gripperAngleServo.setPosition(0.65);
        }if(gamepad1.right_stick_y == 0.2){
            gripperAngleServo.setPosition(0.60);
        }if(gamepad1.right_stick_y == 0.1){
            gripperAngleServo.setPosition(0.55);
        }if(gamepad1.right_stick_y == 0){
            gripperAngleServo.setPosition(0.50);
        }if(gamepad1.right_stick_y == -0.1){
            gripperAngleServo.setPosition(0.45);
        }if(gamepad1.right_stick_y == -0.2){
          gripperAngleServo.setPosition(0.40);
        }if(gamepad1.right_stick_y == -0.3){
            gripperAngleServo.setPosition(0.35);
        }if(gamepad1.right_stick_y == -0.4){
            gripperAngleServo.setPosition(0.30);
        }if(gamepad1.right_stick_y == -0.5){
            gripperAngleServo.setPosition(0.25);
        }if(gamepad1.right_stick_y == -0.6){
            gripperAngleServo.setPosition(0.20);
        }if(gamepad1.right_stick_y == -0.7){
            gripperAngleServo.setPosition(0.15);
        }if(gamepad1.right_stick_y == -0.8){
            gripperAngleServo.setPosition(0.10);
        }if(gamepad1.right_stick_y == -0.9){
            gripperAngleServo.setPosition(0.05);
        }if(gamepad1.right_stick_y == -1){
            gripperAngleServo.setPosition(0);
        }
    }
}