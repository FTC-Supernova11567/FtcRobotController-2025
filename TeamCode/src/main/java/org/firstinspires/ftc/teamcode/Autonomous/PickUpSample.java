package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Arm.Arm;
import org.firstinspires.ftc.teamcode.DriveTrain.MecanumDrive;
import org.firstinspires.ftc.teamcode.Gripper.Gripper;

@Autonomous
public class PickUpSample extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Arm myArm= new Arm(hardwareMap, gamepad2);
        Gripper myGripper = new Gripper(hardwareMap, gamepad2, gamepad1);
        MecanumDrive mecanum = new MecanumDrive(hardwareMap, gamepad1);

        while (getRuntime()>0&&getRuntime()<5){
            mecanum.forward();
            myGripper.spinForward();
        }

        while(getRuntime()>5&&getRuntime()<7){
            myArm.angleUp();
        }
        while (getRuntime()>7&&getRuntime()<10){
            myGripper.spinBackward();
        }
    }
}
