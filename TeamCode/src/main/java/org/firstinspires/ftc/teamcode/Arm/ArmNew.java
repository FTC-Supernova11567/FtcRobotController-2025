package org.firstinspires.ftc.teamcode.Arm;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Gripper.Gripper;

@TeleOp
public class ArmNew extends OpMode {
    Arm myArm;


    @Override
    public void init() {
        myArm = new Arm(hardwareMap, gamepad1);


    } 

    @Override
    public void loop() {
        myArm.rightTriggerExtension();
        myArm.rightBumperRetraction();
        myArm.dpadAngle();


    }
}

