package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Arm.Arm;
import org.firstinspires.ftc.teamcode.DriveTrain.MecanumDrive;
import org.firstinspires.ftc.teamcode.Gripper.Gripper;


@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class AutonomousMecanum extends LinearOpMode {
    MecanumDrive mecanum = new MecanumDrive(hardwareMap, gamepad1);
    Arm arm = new Arm(hardwareMap, gamepad1);
    Gripper gripper = new Gripper(hardwareMap, gamepad1, gamepad2);


    @Override
    public void runOpMode() throws InterruptedException {
        //TODO: measure the times
        while(getRuntime() <= 0.5){
            mecanum.forward();
        }
        while(getRuntime() <= 1.5 && getRuntime() >= 0.5){
            mecanum.rotateLeft();
        }
        while(getRuntime() <= 0.5){
            mecanum.forward();
        }
        while(getRuntime() <= 1.5){
            mecanum.rotateLeft();
        }
        while(getRuntime() <= 4.5){
            mecanum.forward();
        }
        while(getRuntime() <= 5.5){
            arm.extend();
        }
        while(getRuntime() <= 7){
            arm.stopExtension();
        }
        while(getRuntime() <= 7.5){
            gripper.spinBackward();
        }
        while(getRuntime() <= 10){
            mecanum.forward();
        }

    }
}
