package org.firstinspires.ftc.teamcode.Gripper;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp

public class Gripper extends OpMode {
    private ServoEx gripperCatcherServo;
    private ServoEx gripperAngleServo;


    @Override
    public void init() {
        gripperCatcherServo = new SimpleServo(hardwareMap, "catcher", 0, Double.POSITIVE_INFINITY, AngleUnit.DEGREES);
        gripperAngleServo = new SimpleServo(hardwareMap, "angle", 45, 135, AngleUnit.DEGREES);

    }

    @Override
    public void loop() {
        if(gamepad1.a){
            gripperCatcherServo.rotateBy(0.1);
        }else{
            gripperCatcherServo.turnToAngle(0);
        }
        if(gamepad1.right_stick_y != 0){
           gripperAngleServo.setPosition(gamepad1.right_stick_y);
        }else{
            gripperAngleServo.turnToAngle(90);
        }
    }
}
