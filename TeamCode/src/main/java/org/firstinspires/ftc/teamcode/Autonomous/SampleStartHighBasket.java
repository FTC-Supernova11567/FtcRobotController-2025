package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Arm.Arm;
import org.firstinspires.ftc.teamcode.DriveTrain.MecanumDrive;
import org.firstinspires.ftc.teamcode.Gripper.Gripper;

@Autonomous
public class SampleStartHighBasket extends LinearOpMode {
    // There is no arm extension and no gripper in this code.
    @Override
    public void runOpMode() throws InterruptedException {
        Arm myArm = new Arm(hardwareMap, gamepad2);
        MecanumDrive mecanum = new MecanumDrive(hardwareMap, gamepad2);
        Gripper gripper = new Gripper(hardwareMap, gamepad2);

        while (getRuntime() > 0 && getRuntime() < 0.9) {
            mecanum.forward();
            myArm.angleUp();
        }

        while (getRuntime() > 0.9 && getRuntime() < 1.8) {
            mecanum.rotateLeft();
            myArm.angleUp();
        }

        while (getRuntime() > 1.8 && getRuntime() < 2.2) {
            mecanum.right();
            myArm.angleUp();
        }

        while (getRuntime() > 2.2 && getRuntime() < 3.3) {
            mecanum.forward();
            myArm.angleUp();
        }

        while (getRuntime() > 3.3 && getRuntime() < 4){
            mecanum.stop();
            myArm.angleUp();
        }

        while (getRuntime() > 4 && getRuntime() < 6.1){
            myArm.stopAngle();
            myArm.extend();
        }

        while (getRuntime() >6.1 && getRuntime() < 8){
            myArm.stopExtension();
            gripper.spinBackward();
        }

//        while(getRuntime() > 8 && getRuntime() <){
//         TODO: write backwords driving and arm retraction   
//        }

}
}
