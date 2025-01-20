package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Arm.Arm;
import org.firstinspires.ftc.teamcode.DriveTrain.MecanumDrive;
@Autonomous
public class SampleStartHighBasket extends LinearOpMode {
    // There is no arm extension and no gripper in this code.
    @Override
    public void runOpMode() throws InterruptedException {
        Arm myArm = new Arm(hardwareMap, gamepad2);
        MecanumDrive mecanum = new MecanumDrive(hardwareMap, gamepad2);
        while (getRuntime() > 0 && getRuntime() < 0.9) {
            mecanum.forward();
            myArm.angleUp();
        }
        while (getRuntime() > 0.9 && getRuntime() < 1.8) {
            mecanum.rotateLeft();
            myArm.angleUp();
        }
        while (getRuntime() > 1.8 && getRuntime() < 2.1) {
            mecanum.right();
            myArm.angleUp();
        }
        while (getRuntime() > 2.05 && getRuntime() < 3.2) {
            mecanum.forward();
            myArm.angleUp();
        }
        mecanum.stop();
        while (getRuntime() > 0 && getRuntime() < 4.1){
            myArm.angleUp();
        }
//        while(getRuntime() > 4.1 && getRuntime() < 8.1){
//            mecanum.stop();
//        }
        //TODO: extension
        while(getRuntime() > 8.1 && getRuntime() < 9.25){
            mecanum.backward();
            myArm.angleDown();
        }
    }
}
