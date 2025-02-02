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
    public void runOpMode() {
        Arm arm = new Arm(hardwareMap, gamepad2);
        MecanumDrive mecanum = new MecanumDrive(hardwareMap, gamepad2);
        Gripper gripper = new Gripper(hardwareMap, gamepad2, gamepad1);

        waitForStart();
        while(getRuntime()>0 && getRuntime()<1&& !isStopRequested()){
            arm.angleUp();
        }

        while(getRuntime() >= 1 && getRuntime() <= 1.1 && !isStopRequested()){
            arm.stopAngle();
        }

        while (getRuntime()>1.1 && getRuntime()<3 && !isStopRequested()){
            mecanum.right();
        }

        while (getRuntime() >= 3 && getRuntime() <= 3.1 && !isStopRequested()){
            mecanum.stop();
        }

        //        while (getRuntime() > 0 && getRuntime() < 0.9 && !isStopRequested()) {
//            arm.resetAngleEncoder();
//            arm.resetEncoder();
//            gripper.angleRoatateUp();
//            mecanum.forward();
//              arm.angleUp();
//        }
//
//        while (getRuntime() > 0.9 && getRuntime() < 1.8 && !isStopRequested()) {
//            mecanum.rotateLeft();
//               arm.angleUp();
//        }
//
//        while (getRuntime() > 1.8 && getRuntime() < 2.2 && !isStopRequested()) {
//            mecanum.right();
//               arm.angleUp();
//        }
//
//        while (getRuntime() > 2.2 && getRuntime() < 3.3 && !isStopRequested()) {
//            mecanum.forward();
//            arm.angleUp();
//        }
//
//        while (getRuntime() > 3.3 && getRuntime() < 4 && !isStopRequested()) {
//            mecanum.stop();
//            arm.angleUp();
//        }
//
//        while (getRuntime() > 4 && getRuntime() < 10 && !isStopRequested()) {
//            arm.extend();
//            arm.stopAngle();
//            arm.stopExtension();
//        }
//
//        while (getRuntime() > 10 && getRuntime() < 12.1 && !isStopRequested()) {
//            gripper.angleRoataeDown();
//
//        }
////todo: first part of autonomus ends here.
//        //todo: dont use the gripper and the arm darg the parts from now!
//
//        while (getRuntime() > 12.1 && getRuntime() < 13.7 && !isStopRequested()) {
//            gripper.angleRoatateStop();
//            mecanum.backward();
//            arm.angleDown();
//            arm.retract();
//            mecanum.stop();
//        }
////
//        while (getRuntime() > 13.7 && getRuntime() < 18.5) {
//            arm.stopAngle();
//            arm.stopExtension();
//            mecanum.stop();
//        }
//
//        while (getRuntime() > 9.7 && getRuntime() < 11.2) {
//            mecanum.right();
//            mecanum.stop();
//        }
//
//        while (getRuntime() > 11.2 && getRuntime() < 11.5) {
//        mecanum.backward();
//        }
//
//        while (getRuntime() > 12 && getRuntime() < 13) {
//            mecanum.rotateRight();
//            mecanum.stop();
//        }
//
//        while (getRuntime() > 13 && getRuntime() < 13.5) {
//            mecanum.forward();
//            mecanum.stop();
//        }
//
//        while (getRuntime() > 13.5 && getRuntime() < 14) {
//            mecanum.rotateLeft();
//            mecanum.stop();
//       }
//            while (getRuntime() > 14 && getRuntime() < 15.5) {
//            mecanum.forward();
//            }
//
//            while (getRuntime() > 17 && getRuntime() < 18.5) {
//                mecanum.backward();
//                mecanum.stop();
//            }
//            while (getRuntime() > 18.5 && getRuntime() < 19.5) {
//                mecanum.backward();
//                mecanum.stop();
//            }
//
//            while (getRuntime() > 19.5 && getRuntime() < 19.8) {
//                mecanum.rotateRight();
//                mecanum.stop();
//            }
//
//            while (getRuntime()>19.5&&getRuntime()<20.5){
//                mecanum.forward();
//                mecanum.stop();
//            }
//
//            while(getRuntime()>20.5 && getRuntime()<21){
//                mecanum.rotateRight();
//            }
//            while(getRuntime()>21 && getRuntime()<21.3){
//                mecanum.right();
//            }
//
//            while(getRuntime()>21.3 && getRuntime()<22){
//                mecanum.forward();
//            }
//
//            while(getRuntime()>22 && getRuntime()<22.5){
//                mecanum.backward();
//            }



        }
    }
