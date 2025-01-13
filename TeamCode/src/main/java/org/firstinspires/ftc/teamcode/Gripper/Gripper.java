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


    @Override
    public void init() {
//        gripperCatcherServo = new SimpleServo(hardwareMap, "catcher", 0, 360, AngleUnit.DEGREES);
        gripperAngleServo = new SimpleServo(hardwareMap, "angle", 0, 360, AngleUnit.DEGREES);

    }

    @Override
    public void loop() {
//        if(gamepad1.a){
//            gripperCatcherServo.turnToAngle(360);
//        }
//        if(gamepad1.b){
//            gripperCatcherServo.turnToAngle(0);
//        }


//        if(gamepad1.right_stick_y < 0){
//            gripperAngleServo.turnToAngle(200);
//        }
//        if(gamepad1.right_stick_y > 0){
//            gripperAngleServo.turnToAngle(270);
//        }
//        if(gamepad1.x){
//            gripperAngleServo.turnToAngle(215);
//        }

        double angle = gamepad1.right_stick_y * 55 + 215;
        if (gamepad1.right_stick_button){
            gripperAngleServo.turnToAngle(angle);
        }

    }
}
