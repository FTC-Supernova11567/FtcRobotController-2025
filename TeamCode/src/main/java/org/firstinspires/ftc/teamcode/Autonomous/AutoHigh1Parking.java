package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.util.Timing;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.Arm.Arm;
import org.firstinspires.ftc.teamcode.DriveTrain.ImprovedMecanum;
import org.firstinspires.ftc.teamcode.Gripper.Gripper;

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
        gripper.turnToAngle(0);
        gripper.moveHook(0);
        arm.resetAngleEncoder();
        arm.resetExtensionEncoder();

        telemetry.addData("angle position", arm.getAngleMotorPosition());
        telemetry.update();


        waitForStart();
        time.start();
        long currentTime = time.elapsedTime();

        arm.moveByPIDAngle(-2580);
        arm.moveByPIDExtension(3000);

        while (time.elapsedTime() < 300 && !isStopRequested()) {
            mecanum.driveWithBuffer(1, 0, 0, 1);
            telemetry.addData("angle position", arm.getAngleMotorPosition());
            telemetry.update();
        }
        currentTime = time.elapsedTime();
        gripper.turnToAngle(1);
        arm.moveByPIDAngle(-2580);
        arm.moveByPIDExtension(3000);

        while (time.elapsedTime() < currentTime + 700 && !isStopRequested()) {
            mecanum.turnToAngle(135,
                    imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle);
            telemetry.addData("angle position", arm.getAngleMotorPosition());
            telemetry.update();
        }
        currentTime = time.elapsedTime();


        while (time.elapsedTime() < currentTime + 1350 && !isStopRequested()) {
            mecanum.smartDrive(1, 0.67, 135,
                    imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle);arm.moveByPIDAngle(-2920);
            telemetry.addData("angle position", arm.getAngleMotorPosition());
            telemetry.update();
        }
        currentTime = time.elapsedTime();

        mecanum.stop();

        while (time.elapsedTime() < currentTime + 700 && !isStopRequested()){
            arm.moveByPIDAngle(-2580);
            arm.moveByPIDExtension(3000);
            telemetry.addData("angle position", arm.getAngleMotorPosition());
            telemetry.update();
        }
        currentTime = time.elapsedTime();


        arm.stopAngle();
        arm.stopExtension();

        while (time.elapsedTime() < currentTime + 300 && !isStopRequested()) {
            gripper.moveHook(0.28);
            telemetry.addData("angle position", arm.getAngleMotorPosition());
            telemetry.update();
        }
        currentTime = time.elapsedTime();

        // until here, "working" auto
        gripper.moveHook(0);

        while (time.elapsedTime() < currentTime + 700 && !isStopRequested()){
            arm.moveByPIDAngle(-2800);
        }
        currentTime = time.elapsedTime();


        gripper.turnToAngle(0);

        while (time.elapsedTime() < currentTime + 1500 && !isStopRequested()) {
            mecanum.turnToAngle(0, imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle);
            arm.moveByPIDAngle(-700);
            arm.moveByPIDExtension(0);
        }
        currentTime = time.elapsedTime();



        while (time.elapsedTime() < currentTime + 1750 && !isStopRequested()) {
            mecanum.smartDrive(1, 0.7, Math.toRadians(0), imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle);
            arm.moveByPIDAngle(-700);
            arm.moveByPIDExtension(0);
        }
        currentTime = time.elapsedTime();

        arm.stopAngle();
        arm.stopExtension();


        while (time.elapsedTime() < currentTime + 700 && !isStopRequested()) {
            mecanum.turnToAngle(100, imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle);
        }
        currentTime = time.elapsedTime();



        while (time.elapsedTime() < currentTime + 600 && !isStopRequested()) {
            mecanum.driveWithBuffer(-1, 0, 0, 1);
        }
        currentTime = time.elapsedTime();
        mecanum.stop();


        while (time.elapsedTime() < currentTime + 850 && !isStopRequested()){
            arm.moveByPIDAngle(-2920);
        }
        arm.stopAngle();

        telemetry.addData("final time", time.elapsedTime());
        telemetry.update();
    }
}
