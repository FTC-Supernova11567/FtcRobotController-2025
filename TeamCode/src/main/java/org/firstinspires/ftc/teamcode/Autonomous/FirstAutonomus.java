package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Arm.Arm;
import org.firstinspires.ftc.teamcode.DriveTrain.MecanumDrive;
import org.firstinspires.ftc.teamcode.Gripper.Gripper;
@Autonomous
public class FirstAutonomus extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Arm myArm= new Arm(hardwareMap, gamepad2);
        Gripper myGripper= new Gripper(hardwareMap, gamepad2);
        MecanumDrive mecanum= new MecanumDrive(hardwareMap, gamepad1);;
        while(getRuntime()<2){
            mecanum.backward();
            myGripper.spinForward();
        }
        while(getRuntime()<4){
            myGripper.stopCatcher();
            myArm.angleUp();
    }
}
}
