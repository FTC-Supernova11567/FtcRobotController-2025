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
        Gripper gripper = new Gripper(hardwareMap, gamepad2, gamepad1);

        while (getRuntime() > 0 && getRuntime() < 0.9) {
            mecanum.forward();
            //  arm.angleUp();
        }

        while (getRuntime() > 0.9 && getRuntime() < 1.8) {
            mecanum.rotateLeft();
            //   arm.angleUp();
        }

        while (getRuntime() > 1.8 && getRuntime() < 2.2) {
            mecanum.right();
            //   arm.angleUp();
        }

        while (getRuntime() > 2.2 && getRuntime() < 3.3) {
            mecanum.forward();
            //  arm.angleUp();
        }

        while (getRuntime() > 3.3 && getRuntime() < 4) {
            mecanum.stop();
            //   arm.angleUp();
        }

        while (getRuntime() > 4 && getRuntime() < 6.1) {
            //arm.stopAngle();
            // arm.extend();
        }

        while (getRuntime() > 6.1 && getRuntime() < 8) {
            // arm.stopExtension();
            //gripper.turnAForward();
            //gripper.spinBackward();

        }
//todo: first part of autonomus ends here.
        //todo: dont use the gripper and the arm darg the parts from now!

        while (getRuntime() > 8 && getRuntime() < 9.4) {
            mecanum.backward();
            mecanum.stop();
        }

        while (getRuntime() > 9.4 && getRuntime() < 9.7) {

            mecanum.rotateRight();
            mecanum.stop();

        }

        while (getRuntime() > 9.7 && getRuntime() < 11.2) {
            mecanum.right(); 
            mecanum.stop();
        }

        while (getRuntime() > 11.2 && getRuntime() < 12) {

        }

        while (getRuntime() > 12 && getRuntime() < 14) {
            mecanum.left();
            //arm.extend();
            mecanum.stop();
        }

        while (getRuntime() > 13.6 && getRuntime() < 15) {
            mecanum.forward();
            mecanum.stop();
        }

        while (getRuntime() > 15 && getRuntime() < 15.5) {
            mecanum.rotateLeft();
            mecanum.forward();
            mecanum.stop();
       }
            while (getRuntime() > 15.5 && getRuntime() < 17) {
////           arm.angleUp();
////           arm.extend();
////           gripper.spinForward();
            }

            while (getRuntime() > 17 && getRuntime() < 18.5) {
                mecanum.backward();
                mecanum.stop();
            }
            while (getRuntime() > 18.5 && getRuntime() < 19) {
                mecanum.rotateRight();
                //arm.angleDown();
                mecanum.stop();
            }

            while (getRuntime() > 19 && getRuntime() < 19.8) {
                // arm.extend();
                //   gripper.spinBackward();
                mecanum.forward();
                mecanum.stop();
            }

            while (getRuntime()>19.8&&getRuntime()<21){
                //gripper.spinBackward();
            }



        }
    }
