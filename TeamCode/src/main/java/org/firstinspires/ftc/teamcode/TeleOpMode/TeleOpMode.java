package org.firstinspires.ftc.teamcode.TeleOpMode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.Arm.Arm;
import org.firstinspires.ftc.teamcode.DriveTrain.ImprovedMecanum;
import org.firstinspires.ftc.teamcode.Gripper.Gripper;
import org.firstinspires.ftc.teamcode.Utils.Utils;

@TeleOp
public class TeleOpMode extends OpMode {
    Arm arm;
    Gripper gripper;
    // NewMecanumDrive mecanum;
    ImprovedMecanum mecanum;
    private IMU imu;

    @Override
    public void init() {
        arm = new Arm(hardwareMap, gamepad2);
        gripper = new Gripper(hardwareMap);
        mecanum = new ImprovedMecanum(hardwareMap);

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
//        gripper.gripperControl();

        //if (-gamepad1.right_stick_y > 0 && gamepad1.right_stick_x == 0) mecanum.forward();
        //if (-gamepad1.right_stick_y < 0 && gamepad1.right_stick_x == 0) mecanum.backwards();
        //if (-gamepad1.right_stick_y == 0 && gamepad1.right_stick_x > 0) mecanum.right();
        //if (-gamepad1.right_stick_y == 0 && gamepad1.right_stick_x < 0) mecanum.left();
//
        //if(-gamepad1.right_stick_y > 0 && gamepad1.right_stick_x > 0) mecanum.diagonalFrontRight();
        //if(-gamepad1.right_stick_y > 0 && gamepad1.right_stick_x < 0) mecanum.diagonalFrontLeft();
        //if (-gamepad1.right_stick_y < 0 && gamepad1.right_stick_x > 0) mecanum.diagonalBackRight();
        //if(-gamepad1.right_stick_y < 0 && gamepad1.right_stick_x < 0) mecanum.diagonalBackLeft();

        // if(-gamepad1.left_stick_x > 0) mecanum.rotateRight();
        //if(-gamepad1.left_stick_x < 0) mecanum.rotateLeft();

        // if (gamepad1.right_stick_y == 0 && gamepad1.right_stick_x == 0) mecanum.stop();

        double deadbend = 0.4;
        double y = Utils.deadbend(-gamepad1.left_stick_y, deadbend);
        double x = Utils.deadbend(gamepad1.left_stick_x, deadbend);
        double rx = Utils.deadbend(gamepad1.right_stick_x, deadbend);

//        if (gamepad1.a) {
//            imu.resetYaw();
//        }
//
//        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
//
//        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
//        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        mecanum.drive(y, x, rx); // Basic driving
        // mecanum.drive(rotY, rotX, rx); // Field orianted driving

//        telemetry.addData("IMU yaw", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS));
//        telemetry.update();



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

        telemetry.update();
    }
}
