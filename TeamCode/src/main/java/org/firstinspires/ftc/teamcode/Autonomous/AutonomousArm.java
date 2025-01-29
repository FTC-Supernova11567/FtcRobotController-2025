package org.firstinspires.ftc.teamcode.Autonomous;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Arm.Arm;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class AutonomousArm extends LinearOpMode {
    Arm arm = new Arm(hardwareMap, gamepad1);

    @Override
    public void runOpMode() throws InterruptedException {
        while(getRuntime() <= 4.5){
            arm.stopExtension();
        }
        while(getRuntime() <= 5.5){
            arm.extensionOpen();
        }
        while(getRuntime() <= 7){
            arm.stopExtension();
        }
        while(getRuntime() <= 8){
            arm.extensionOpen();
        }

    }
}
