package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Examples.ImprovedMecanum;

@Autonomous
public class SafetyAutonomous extends LinearOpMode {
    public ImprovedMecanum mecanum;
    public IMU imu;



    @Override
    public void runOpMode() throws InterruptedException {
        mecanum = new ImprovedMecanum(hardwareMap);

        imu = hardwareMap.get(IMU.class, "imu");

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD,
                RevHubOrientationOnRobot.UsbFacingDirection.DOWN));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);

        imu.resetYaw();



        waitForStart();
        mecanum.drive(0, -1, 0);
        while (getRuntime() > 0 && getRuntime() < 4 && !isStopRequested()){
            double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            double rotX = 0.5 * Math.cos(-botHeading) - 0 * Math.sin(-botHeading);
            double rotY = 0.5 * Math.sin(-botHeading) + 0 * Math.cos(-botHeading);

            mecanum.drive(0, rotX, 0);
        }
    }
}
