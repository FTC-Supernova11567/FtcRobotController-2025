package org.firstinspires.ftc.teamcode.TeleOpMode;

import static java.lang.Math.signum;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Arm.Arm;
import org.firstinspires.ftc.teamcode.DriveTrain.ImprovedMecanum;
import org.firstinspires.ftc.teamcode.Gripper.Gripper;
import org.firstinspires.ftc.teamcode.Utils.Utils;

@TeleOp
public class TeleOpMode extends OpMode {
    Arm arm;
    Gripper gripper;
    ImprovedMecanum mecanum;
    private IMU imu;

    public final double[] collect = new double[]{0, 0, 0};


    @Override
    public void init() {
        arm = new Arm(hardwareMap);
        gripper = new Gripper(hardwareMap);
        mecanum = new ImprovedMecanum(hardwareMap);

        imu = hardwareMap.get(IMU.class, "imu");

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD,
                RevHubOrientationOnRobot.UsbFacingDirection.DOWN));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        arm.resetAngleEncoder();
        arm.resetExtensionEncoder();

    }

    @Override
    public void loop() {
        arm.updateLegalMaxExtension();
        arm.check_fix_overExtend();

        if (gamepad1.triangle) {
            imu.resetYaw();
        }

        /**
         * Drive-train code
         */

        double deadbend = 0.4;
        double y = Utils.deadbend(-gamepad1.left_stick_y, deadbend);
        double x = Utils.deadbend(gamepad1.left_stick_x, deadbend);
        double rx = Utils.deadbend(gamepad1.right_stick_x, deadbend);

        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

//        mecanum.drive(y, x, rx); // Basic driving
        mecanum.drive(rotY, rotX, rx); // Field orianted driving

//        telemetry.addData("IMU yaw", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS));
//        telemetry.update();

        /**
         * Arm and Gripper code
         */

        // if (gamepad2.b) arm.autoReset();
        if (gamepad2.dpad_down) arm.angleDown();
        if (gamepad2.dpad_up) arm.angleUp();
        if (!gamepad2.dpad_up && !gamepad2.dpad_down) arm.stopAngle();

        if (gamepad2.right_trigger != 0) arm.extend();
        if (gamepad2.right_bumper) arm.retract();
        if (!gamepad2.right_bumper && gamepad2.right_trigger==0) arm.stopExtension();

        if (gamepad2.right_stick_button) arm.resetExtensionEncoder();
        if (gamepad2.share) arm.resetAngleEncoder();

        if (gamepad1.right_trigger != 0) gripper.moveHook(0);
        if (gamepad1.left_trigger != 0) gripper.moveHook(0.28);

        if(gamepad2.square) gripper.turnToAngle(0); // Gripper centered
        if(gamepad2.right_stick_x < 0) gripper.turnToAngle(-1); // Gripper centered
        if(gamepad2.right_stick_x > 0) gripper.turnToAngle(1); // Gripper centered
        gripper.moveByAngle(gamepad2.right_stick_x == 0 ? 0 : signum(gamepad2.right_stick_x) * 0.01);


        if (gamepad2.cross){
            arm.moveByPIDAngle(0);
            arm.moveByPIDExtension(0);
            gripper.turnToAngle(0);
        }
        if (gamepad2.circle){
            arm.moveByPIDExtension(-2350);
            arm.moveByPIDAngle(0);
            gripper.turnToAngle(0);
        }
        if (gamepad2.triangle){
            arm.moveByPIDAngle(-2920);
            arm.moveByPIDExtension(2990);
            gripper.turnToAngle(1);
        }

//        if (gamepad2.cross) arm.moveByPIDAngle(0);
//        if (gamepad2.circle) arm.moveByPIDAngle(-2350);
//        if (gamepad2.triangle) arm.moveByPIDAngle(-2920);
//        if (gamepad2.touchpad) arm.moveByPIDAngle(3200);
//
//        if (gamepad2.cross && !arm.getOverExtend()) arm.moveByPIDExtension(0);
//        if (gamepad2.circle && !arm.getOverExtend()) arm.moveByPIDExtension(0);
//        if (gamepad2.triangle && !arm.getOverExtend()) arm.moveByPIDExtension(2990);


        /**
         * Telemetry
         */

        telemetry.addData("Status", "Run Time: " + getRuntime());
        // telemetry.addData("gripper angle", gripper.getAngle());
        telemetry.addData("arm angle", -arm.getAngle());
        // telemetry.addData("Angle motor current limit", arm.getAngleMotor().getCurrentAlert(CurrentUnit.MILLIAMPS));
        // telemetry.addData("Angle motor over current", arm.getIsOverCurrent());
        // telemetry.addData("Extension", arm.getExtend());
        //telemetry.addData("Current angle ticks", -arm.getAngleMotor().getCurrentPosition());
        // telemetry.addData("Extension avg current", arm.getExtensionMotor().isOverCurrent());
        //telemetry.addData("angleCurrent", arm.getAngleMotor().getCurrent(CurrentUnit.MILLIAMPS));

        // telemetry.addData("Angle motor current", arm.getAngleMotor().getCurrent(CurrentUnit.MILLIAMPS));
        telemetry.addData("Angle position", arm.getAngleMotorPosition());
        telemetry.addLine();
        telemetry.addData("Extension position", arm.getExtensionMotorPosition());
        telemetry.addData("Extension Calculation output", Math.cos(Math.toRadians(-arm.getAngle())) *  arm.getExtensionMotorPosition());
        telemetry.addData("LegalMaxExtension",arm.getLegalMaxExtension());
        telemetry.addData("WantedBusketExtension",arm.getWantedBusketExtension());
        telemetry.addLine();

        telemetry.update();
    }
}
