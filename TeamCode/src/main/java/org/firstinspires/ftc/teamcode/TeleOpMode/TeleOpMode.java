package org.firstinspires.ftc.teamcode.TeleOpMode;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.util.MathUtils;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
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
    int count = 1;
    double avg = 0;

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
        mecanum.mecanumAlL();
        telemetry.update();

        if (gamepad2.b) arm.autoReset();
        if (gamepad2.dpad_down) arm.angleDown();
        if(gamepad2.dpad_up) arm.angleUp();
        if(gamepad2.right_trigger != 0) arm.extend();
        if(gamepad2.right_bumper) arm.retract();

        if (gamepad2.right_stick_button) arm.resetExtensionEncoder();
        if (gamepad2.y) arm.resetAngleEncoder();

        if (gamepad1.right_trigger != 0) gripper.spinForward();
        if (gamepad1.left_trigger != 0) gripper.spinBackward();
        if (gamepad1.left_trigger == 0 && gamepad1.right_trigger == 0) gripper.stopSpinning();

        if(gamepad2.a) gripper.changeToAngle(35.5);
        if(gamepad2.x) gripper.changeToAngle(40);
        gripper.moveAngleServo(gamepad2.right_stick_y == 0 ? 0 : gamepad2.right_stick_y < 0 ? -0.3 : 0.3);



        telemetry.addData("Status", "Run Time: " + getRuntime());
        telemetry.addData("gripper angle", gripper.getAngle());
        telemetry.addData("arm angle", -arm.getAngle());
        telemetry.addData("Angle motor current limit", arm.getAngleMotor().getCurrentAlert(CurrentUnit.MILLIAMPS));
        telemetry.addData("Angle motor over current", arm.getIsOverCurrent());
        // telemetry.addData("Extension", arm.getExtend());
        //telemetry.addData("Current angle ticks", -arm.getAngleMotor().getCurrentPosition());
        telemetry.addData("Extension Calculation ouput", Math.cos(Math.toRadians(-arm.getAngle() - 64)) *  arm.getExtensionMotor().getCurrentPosition());
        // telemetry.addData("Extension avg current", arm.getExtensionMotor().isOverCurrent());
        //telemetry.addData("angleCurrent", arm.getAngleMotor().getCurrent(CurrentUnit.MILLIAMPS));

        telemetry.addData("Angle motor current", arm.getAngleMotor().getCurrent(CurrentUnit.MILLIAMPS));

//        avg = arm.getAngleMotorCurrent(avg, count);
//        telemetry.addData("Angle motor current avg MiliAmps", avg);
//        count ++;
    }
}
