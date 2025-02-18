package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.util.Timing;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.Arm.Arm;
import org.firstinspires.ftc.teamcode.DriveTrain.ImprovedMecanum;

import java.util.concurrent.TimeUnit;

@Autonomous
public class AutoHigh2Parking extends LinearOpMode {

    public ImprovedMecanum mecanum;
    public Arm arm;

    public Timing.Timer time;

    @Override
    public void runOpMode() throws InterruptedException {

        mecanum = new ImprovedMecanum(hardwareMap);
        arm = new Arm(hardwareMap);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        time = new Timing.Timer(30000, TimeUnit.MILLISECONDS);

        BNO055IMU imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;

        imu.initialize(parameters);

        waitForStart();
        time.start();

        arm.angleUp();

        while (time.elapsedTime() < 300 && !isStopRequested()) {
            mecanum.smartDrive(1, 0, 0, 0, 0);
        }
        arm.stopAngle();


        while (time.elapsedTime() < 1000 && !isStopRequested()) { //turning 110 degrees left
            mecanum.turnToAngle(110, imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle);
        }


        while (time.elapsedTime() < 1600 && !isStopRequested()) {
            mecanum.drive(1, 0, 0);
        }


        //todo: add angle and extension and gripper for busket game piece
        while (time.elapsedTime() < 3000 && !isStopRequested()){
            mecanum.stop();
        }
        //todo: add angle and extension close


        while (time.elapsedTime() < 3500 && !isStopRequested()) {
            mecanum.turnToAngle(0, imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle);
        }


        while (time.elapsedTime() < 4200 && !isStopRequested()) {
            mecanum.smartDrive(0, 0.3, 0, Math.toRadians(0), imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle);
        }

        while (time.elapsedTime() < 4500 && !isStopRequested()) {
            mecanum.drive(0.3, 0, 0);
        }

        //todo: extend arm and collect game piece
        while (time.elapsedTime() < 6000 && !isStopRequested()){
            mecanum.stop();
        }


        while (time.elapsedTime() < 6800 && !isStopRequested()) {
            mecanum.turnToAngle(150, imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle);
        }


        while (time.elapsedTime() < 7000 && !isStopRequested()) {
            mecanum.drive(1, 0, 0);
        }

        //todo: add angle and extension and gripper for busket game piece
        arm.angleUp();

        while (time.elapsedTime() < 7500 && !isStopRequested()) {
            mecanum.turnToAngle(0, imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle);
        }
        arm.stopAngle();

        while (time.elapsedTime() < 9500 && !isStopRequested()) {
            mecanum.smartDrive(1, 0.3, 0, Math.toRadians(0), imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle);
        }


        while (time.elapsedTime() < 10500 && !isStopRequested()) {
            mecanum.turnToAngle(100, imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle);
        }


        while (time.elapsedTime() < 11550 && !isStopRequested()) {
            mecanum.drive(-1, 0, 0);
        }

        //todo: add arm angle up


    }
}
