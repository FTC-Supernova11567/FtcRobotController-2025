package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.util.Timing;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Examples.ImprovedMecanum;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

@Autonomous
public class AutoHigh1Parking extends LinearOpMode {

    public ImprovedMecanum mecanum;
    public IMU imu;
    public Timing.Timer time;

    @Override
    public void runOpMode() throws InterruptedException {
        mecanum = new ImprovedMecanum(hardwareMap);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        time = new Timing.Timer(30000, TimeUnit.MILLISECONDS);

        imu = hardwareMap.get(IMU.class, "imu");

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD,
                RevHubOrientationOnRobot.UsbFacingDirection.DOWN));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);


        waitForStart();

        time.start();
        imu.resetYaw();
        double robotStartingAngle = imu.getRobotYawPitchRollAngles().getYaw();


        while (time.elapsedTime() < 200 && !isStopRequested()){
            mecanum.drive(1, 0, 0);
        }

        telemetry.addData("!!!!!!after forward time", time.elapsedTime());

        telemetry.addData("!!!!!robot starting angle", robotStartingAngle);

        while (85 >= Math.abs(imu.getRobotYawPitchRollAngles().getYaw()) && !isStopRequested()) { //turning 90 degrees left
            mecanum.drive(0, 0, -1);
        }

        telemetry.addData("!!! !current robot angle", imu.getRobotYawPitchRollAngles().getYaw());
        telemetry.addData("!!!current time", time.elapsedTime());


//        while (time.elapsedTime() < 1500 && !isStopRequested()){
//            mecanum.drive(1, 0, 0);
//        }

        while(time.elapsedTime() < 2000 && !isStopRequested()){
            mecanum.stop();
        }

        while (0 <= Math.abs(imu.getRobotYawPitchRollAngles().getYaw()) && !isStopRequested()) { // turning back to 0
            mecanum.drive(0, 0, 1);
            telemetry.addData("current angle", imu.getRobotYawPitchRollAngles().getYaw());
            telemetry.update();
        }
        telemetry.addData("!!time for 2nd turn", time.elapsedTime());


        while (time.elapsedTime() < 2500 && !isStopRequested()){
            mecanum.drive(0.2, 0, 0);
        }

        telemetry.addData("end time", time.elapsedTime());
        telemetry.update();
    }
}
