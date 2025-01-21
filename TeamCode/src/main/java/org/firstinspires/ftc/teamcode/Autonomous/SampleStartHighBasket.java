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
        Arm arm = new Arm(hardwareMap, gamepad2);
        MecanumDrive mecanum = new MecanumDrive(hardwareMap, gamepad2);
        Gripper gripper = new Gripper(hardwareMap, gamepad2);

        while (getRuntime() > 0 && getRuntime() < 0.9) {
            mecanum.forward();
            arm.angleUp();
        }

        while (getRuntime() > 0.9 && getRuntime() < 1.8) {
            mecanum.rotateLeft();
            arm.angleUp();
        }

        while (getRuntime() > 1.8 && getRuntime() < 2.2) {
            mecanum.right();
            arm.angleUp();
        }

        while (getRuntime() > 2.2 && getRuntime() < 3.3) {
            mecanum.forward();
            arm.angleUp();
        }

        while (getRuntime() > 3.3 && getRuntime() < 4){
            mecanum.stop();
            arm.angleUp();
        }

        while (getRuntime() > 4 && getRuntime() < 6.1){
            arm.stopAngle();
            arm.extend();
        }

        while (getRuntime() >6.1 && getRuntime() < 8){
            arm.stopExtension();
            gripper.spinBackward();
        }

       while(getRuntime() > 8 && getRuntime() <9){
            mecanum.backward();
            arm.retract();
            arm.angleDown();
        }

       while(getRuntime()>9 && getRuntime()<9.8){
           arm.angleDown();
           mecanum.rotateRight();
           arm.retract();

       }

       while(getRuntime()>9.8 && getRuntime()<10.6){
           mecanum.rotateLeft();
       }

       while (getRuntime()>10.6 && getRuntime()<11){
           mecanum.rotateRight();
       }


}
}
