package org.firstinspires.ftc.teamcode.DriveTrain;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Utils.Utils;

@TeleOp
public class TestingImprovedMecanum extends OpMode {
    ImprovedMecanum mecanum;
    // Retrieve the IMU from the hardware map
    IMU imu;

    @Override
    public void init(){

        mecanum = new ImprovedMecanum(hardwareMap);

        imu = hardwareMap.get(IMU.class, "imu");

        // Adjust the orientation parameters to match your robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD,
                RevHubOrientationOnRobot.UsbFacingDirection.DOWN));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

    }

    @Override
    public void loop(){
        double deadbend = 0.4;
        double y = Utils.deadbend(-gamepad1.left_stick_y, deadbend);
        double x = Utils.deadbend(gamepad1.left_stick_x, deadbend);
        double rx = Utils.deadbend(gamepad1.right_stick_x, deadbend);

        if (gamepad1.a) {
            imu.resetYaw();
        }

        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        // mecanum.drive(y, x, rx); // Basic driving
        mecanum.drive(rotY, rotX, rx); // Field orianted driving

        telemetry.addData("IMU yaw", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS));
        telemetry.update();

    }
}
