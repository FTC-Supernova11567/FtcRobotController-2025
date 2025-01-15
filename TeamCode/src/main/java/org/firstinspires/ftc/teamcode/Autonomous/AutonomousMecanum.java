package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.DriveTrain.MecanumDrive;


@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class AutonomousMecanum extends LinearOpMode {
    MecanumDrive mecanum = new MecanumDrive(hardwareMap, gamepad1);


    @Override
    public void runOpMode() throws InterruptedException {
        while(getRuntime() <= 0.5){
            mecanum.forward();
        }
        //TODO: measure the time it is taking to the robot to rotate by 90 degrees
        while(getRuntime() <= 0.5){
            mecanum.rotateLeft();
        }
        while(getRuntime() <= 3){
            mecanum.forward();
        }
    }
}
