package org.firstinspires.ftc.teamcode.TeleOpMode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.Arm.Arm;
import org.firstinspires.ftc.teamcode.DriveTrain.MecanumDrive;
import org.firstinspires.ftc.teamcode.Gripper.Gripper;

@Config
@TeleOp
public class TeleOpMode extends OpMode {
    Arm arm;
    Gripper gripper;
    MecanumDrive mecanum;
    private IMU imu;

    @Override
    public void init() {
        arm = new Arm(hardwareMap, gamepad2);
        gripper = new Gripper(hardwareMap, gamepad1, gamepad2);
        mecanum = new MecanumDrive(hardwareMap, gamepad1);

//        RevHubOrientationOnRobot.LogoFacingDirection logoFacingDirection = RevHubOrientationOnRobot.LogoFacingDirection.FORWARD;
//        RevHubOrientationOnRobot.UsbFacingDirection usbFacingDirection = RevHubOrientationOnRobot.UsbFacingDirection.UP;
//        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoFacingDirection, usbFacingDirection);
//
//        imu = hardwareMap.get(IMU.class, "imu");
//        imu.initialize(new IMU.Parameters(orientationOnRobot));
//        mecanum = new MecanumDrive(hardwareMap, gamepad1);
//
//        FtcDashboard dashboard = FtcDashboard.getInstance();
//        telemetry = dashboard.getTelemetry();
//
//        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {
        arm.armControl();
        gripper.gripperControl();
        mecanum.mecanumAlL();
        telemetry.update();


        telemetry.addData("Status", "Run Time: " + getRuntime());
        telemetry.addData("gripper angle", gripper.getPosition());
        // telemetry.addData("arm angle", -arm.getAngle() - 64);
//        telemetry.addData("left stick y", gamepad2.left_stick_y);
//        telemetry.addData("gripper direction", myGripper.getDirection() );
        telemetry.addData("Pose", -arm.getAngle() - 64);
        // telemetry.addData("Pose radians", Math.cos(Math.toRadians(-arm.getAngle() - 64)));
        telemetry.addData("Extension", arm.getExtend());
        telemetry.addData("Current position ticks", -arm.getAngleMotor().getCurrentPosition());
        // telemetry.addData("Check Cos radians", Math.cos(Math.toRadians(60)));
        // telemetry.addData("Check Cos", Math.cos(60));
//        telemetry.addData("CosAngle", Math.cos(Math.toRadians(-arm.getAngle() - 64)));
//        telemetry.addData("ArcCosAngle", Math.acos(Math.toRadians(-arm.getAngle() - 64)) *  arm.getExtensionMotor().getCurrentPosition());
        telemetry.addData("ExCalc", Math.cos(Math.toRadians(-arm.getAngle() - 64)) *  arm.getExtensionMotor().getCurrentPosition());
//        telemetry.addData("extension busy?", arm.getExtensionMotor().isMotorEnabled());
        // telemetry.addData("extension current", arm.getExtensionMotor().getCurrent(CurrentUnit.MILLIAMPS));
        // telemetry.addData("average extension", arm.getAverage(300));
        // telemetry.addData("Extension avg current", arm.getExtensionMotor().isOverCurrent());
    }
}
