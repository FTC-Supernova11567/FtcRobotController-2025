package org.firstinspires.ftc.teamcode.TeleOpMode;

import static java.lang.Math.signum;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Arm.Arm;
import org.firstinspires.ftc.teamcode.DriveTrain.ImprovedMecanum;
import org.firstinspires.ftc.teamcode.Gripper.Gripper;
import org.firstinspires.ftc.teamcode.Utils.Utils;

@Config
@TeleOp
public class TeleOpMode extends OpMode {
    Arm arm;
    Gripper gripper;
    ImprovedMecanum mecanum;
    private IMU imu;


    public boolean movingToPosition = true;
    public double driverBuffer = 1;

//    public final double[] collect = new double[]{0, 0, 0};


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

//        arm.resetAngleEncoder();
//        arm.resetExtensionEncoder();


    }

    @Override
    public void loop() {
        arm.updateLegalMaxExtension();
        arm.check_fix_overExtend();

        if (gamepad1.triangle) {
            driverBuffer = 1;
        }
        if (gamepad1.share){
            imu.resetYaw();
        }

        double deadbend = 0.4;

        // Right stick main drive
        double y = Utils.deadbend(-gamepad1.right_stick_y, deadbend);
        double x = Utils.deadbend(gamepad1.right_stick_x, deadbend);
        double rx = Utils.deadbend(gamepad1.left_stick_x, deadbend);

        // Left stick main drive
//        double y = Utils.deadbend(-gamepad1.left_stick_y, deadbend);
//        double x = Utils.deadbend(gamepad1.left_stick_x, deadbend);
//        double rx = Utils.deadbend(gamepad1.right_stick_x, deadbend);

        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        mecanum.driveWithBuffer(rotY, rotX, rx, driverBuffer);
//        if (fiedloriented){
//            mecanum.drive(rotY, rotX, rx);
//        }
//        else{
//            mecanum.driveWithBuffer(y, x, rx, driverBuffer); // Basic driving
//        }

        // if (gamepad2.b) arm.autoReset();
        if (gamepad2.dpad_up) arm.angleUp(); // Change angle manually
        if (gamepad2.dpad_down) arm.angleDown(); // Change angle manually
        if (!gamepad2.dpad_up && !gamepad2.dpad_down &&
                !gamepad2.cross && !gamepad2.circle && !gamepad2.triangle) arm.stopAngle(); // Stop angle automatically when nothing is pressed

        if (gamepad1.dpad_up){ // open climb
            arm.moveByPIDAngle(-2950);
            arm.moveByPIDExtension(0);
        }

        if (gamepad1.dpad_down){ // close climb
            arm.moveByPIDAngle(300);
            arm.moveByPIDExtension(1000);
            gripper.moveHook(0);
            gripper.moveHook(1);
        }

        if (gamepad2.right_trigger != 0) arm.extend(); // Change extension manually
        if (gamepad2.left_trigger != 0) arm.retract(); // Change extension manually
        if (gamepad2.right_trigger == 0 && gamepad2.left_trigger == 0 &&
                !gamepad2.cross && !gamepad2.circle && !gamepad2.triangle && !gamepad1.circle) arm.stopExtension(); // Stop extension automatically when nothing is pressed

        if (gamepad2.options) arm.resetExtensionEncoder(); // Reset extension encoder
        if (gamepad2.share) arm.resetAngleEncoder(); // reset angle encoder

        if (gamepad1.right_trigger != 0) gripper.moveHook(0); // Close hook
        if(gamepad1.left_trigger != 0) gripper.moveHook(0.28); // Open hook

        if(gamepad2.square) gripper.turnToAngle(0); // Gripper centered
        if (gamepad2.right_stick_x > 0) gripper.moveByAngle(0.8); // Rotate griper max right
        if (gamepad2.right_stick_x < 0) gripper.moveByAngle(-0.8); // Rotate griper max left
        if (gamepad2.right_stick_x == 0 && !gamepad2.cross && !gamepad2.circle && !gamepad2.triangle && !gamepad2.left_stick_button && movingToPosition) gripper.turnToAngle(0);

        gripper.moveByAngle(gamepad2.dpad_right ? 0.02 : gamepad2.dpad_left ? -0.02 : 0); // Rotate griper with stick manually


        if (gamepad2.cross){ // Set-point closed
            movingToPosition = true;
            driverBuffer = 1;
            arm.moveByPIDAngle(-780);
            arm.moveByPIDExtension(0);
        }
        if (gamepad2.circle){ // Set-point lower basket
            movingToPosition = false;
            driverBuffer = 0.4;
            arm.moveByPIDAngle(-2000);
            arm.moveByPIDExtension(1300);
            gripper.turnToAngle(0);
        }
        if (gamepad2.triangle){ // Set-point high basket
            movingToPosition = false;
            driverBuffer = 0.4;
            arm.moveByPIDAngle(-2580);
            arm.moveByPIDExtension(3000);
            gripper.turnToAngle(0.8);
        }
        if (gamepad2.left_stick_button){ // Set-point collect
            movingToPosition= false;
            driverBuffer = 0.3;
            arm.moveByPIDAngle(-550);
            arm.moveByPIDExtension(2050);
            gripper.moveHook(0.28);
        }


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
        telemetry.addData("Wanted angle", arm.getWantedBasketAngle());
        telemetry.addLine();
        telemetry.addData("Extension position", arm.getExtensionMotorPosition());
        telemetry.addData("Extension Calculation output", Math.cos(Math.toRadians(-arm.getAngle())) *  arm.getExtensionMotorPosition());
        telemetry.addData("LegalMaxExtension",arm.getLegalMaxExtension());
        telemetry.addData("WantedBusketExtension",arm.getWantedBasketExtension());
        telemetry.addLine();

        telemetry.update();
    }
}
