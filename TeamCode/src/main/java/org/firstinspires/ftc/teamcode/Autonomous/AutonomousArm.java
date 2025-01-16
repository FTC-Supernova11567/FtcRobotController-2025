package org.firstinspires.ftc.teamcode.Autonomous;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Arm.Arm;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class AutonomousArm extends LinearOpMode {
    Arm arm = new Arm(hardwareMap, gamepad1);

    @Override
    public void runOpMode() throws InterruptedException {
        while(getRuntime() <= 2){
            arm.extensionOpen();
        }
        while(getRuntime() <= 4){
            arm.stopExtension();
        }
        while(getRuntime() <= 6){
            arm.extensionOpen();
        }
    }
}
