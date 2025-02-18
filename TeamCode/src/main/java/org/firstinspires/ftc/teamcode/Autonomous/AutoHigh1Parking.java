package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.util.Timing;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.Arm.Arm;
import org.firstinspires.ftc.teamcode.DriveTrain.ImprovedMecanum;
import org.firstinspires.ftc.teamcode.Gripper.Gripper;
import org.firstinspires.ftc.teamcode.TeleOpMode.TeleOpMode;

import java.util.concurrent.TimeUnit;

@Autonomous
public class AutoHigh1Parking extends LinearOpMode {

    public ImprovedMecanum mecanum;
    public Arm arm;
    public Gripper gripper;

    public Timing.Timer time;

    @Override
    public void runOpMode() throws InterruptedException {

        mecanum = new ImprovedMecanum(hardwareMap);
        arm = new Arm(hardwareMap);
        gripper = new Gripper(hardwareMap);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        time = new Timing.Timer(30000, TimeUnit.MILLISECONDS);

        BNO055IMU imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;

        imu.initialize(parameters);

        waitForStart();
        time.start();

        //todo lift arm before moving
        arm.moveByPIDAngle(-2920);
        arm.moveByPIDExtension(2990);
        gripper.turnToAngle(1);

        while (time.elapsedTime() < 300 && !isStopRequested()) {
            mecanum.smartDrive(1, 0, 0, 0, 0);
        }
        arm.stopAngle();
        arm.stopExtension();


        while (time.elapsedTime() < 1000 && !isStopRequested()) {
            mecanum.turnToAngle(90, imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle);
        }


        while (time.elapsedTime() < 1600 && !isStopRequested()) {
            mecanum.drive(1, 0, 0);
        }

        //todo: add angle and busket game piece
        while (time.elapsedTime() < 3000 && !isStopRequested()){
            mecanum.stop();
        }


        while (time.elapsedTime() < 3500 && !isStopRequested()) {
            mecanum.turnToAngle(0, imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle);
        }


        while (time.elapsedTime() < 4800 && !isStopRequested()) {
            mecanum.smartDrive(1, 0.3, 0, Math.toRadians(0), imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle);
        }


        while (time.elapsedTime() < 5650 && !isStopRequested()) {
            mecanum.turnToAngle(100, imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle);
        }


        while (time.elapsedTime() < 6300 && !isStopRequested()) {
            mecanum.drive(-1, 0, 0);
        }

        //todo: add arm angle up


    }
}
