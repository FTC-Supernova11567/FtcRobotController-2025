package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Arm.Arm;
import org.firstinspires.ftc.teamcode.DriveTrain.MecanumDrive;
import org.firstinspires.ftc.teamcode.Gripper.Gripper;
@Autonomous
public class AutonomusTrail extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Arm myArm = new Arm(hardwareMap, gamepad2);
        Gripper myGripper = new Gripper(hardwareMap, gamepad2);
        MecanumDrive mecanum = new MecanumDrive(hardwareMap, gamepad1);
        ;
        while (getRuntime() > 0 && getRuntime() < 2) {
            mecanum.forward();
        }
        while (getRuntime() > 2 && getRuntime() < 4) {
            mecanum.backward();
        }
        while (getRuntime() > 4 && getRuntime() < 6) {
            mecanum.right();
        }
        while (getRuntime() > 6 && getRuntime() < 8) {
            mecanum.left();
        }
        while (getRuntime() > 8 && getRuntime() < 10) {
            mecanum.rotateRight();
        }
        while (getRuntime() > 10 && getRuntime() < 12) {
            mecanum.rotateLeft();
        }
        while (getRuntime() > 12 && getRuntime() < 14) {
            mecanum.diagonalFrontRight();
        }
        while (getRuntime() > 14 && getRuntime() < 16) {
            mecanum.diagonalFrontLeft();
        }
        while (getRuntime() > 16 && getRuntime() < 18) {
            mecanum.diagonalBackRight();
        }
        while (getRuntime() > 18 && getRuntime() < 20) {
            mecanum.diagonalBackLeft();
        }

        while (getRuntime() > 20 && getRuntime() < 22) {
            myArm.extend();
        }
        while (getRuntime() > 22 && getRuntime() < 24) {
            myArm.retract();
        }

        while(getRuntime()>24&& getRuntime()<26){
            myArm.angleUp();
        }
        while (getRuntime()>24&&getRuntime()<26){
            myArm.angleDown();
        }

        while (getRuntime()>26&&getRuntime()<28){
            myGripper.spinForward();
        }

        while (getRuntime()>28&&getRuntime()<30){
            myGripper.spinBackward();
        }

    }
}