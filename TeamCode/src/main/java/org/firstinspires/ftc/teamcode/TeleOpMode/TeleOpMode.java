package org.firstinspires.ftc.teamcode.TeleOpMode;

import static java.lang.Math.signum;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Arm.Arm;
import org.firstinspires.ftc.teamcode.DriveTrain.MecanumDrive;
import org.firstinspires.ftc.teamcode.Gripper.Gripper;

@Config
@TeleOp
public class TeleOpMode extends OpMode {
    Arm arm;
    Gripper gripper;
    MecanumDrive mecanum;

    public double integralSum = 0;

    ElapsedTime timer = new ElapsedTime();
    public double lastError = 0;

    @Override
    public void init() {
        arm = new Arm(hardwareMap);
        gripper = new Gripper(hardwareMap);
        mecanum = new MecanumDrive(hardwareMap, gamepad1);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void loop() {
        mecanum.mecanumAlL();

        arm.updateLegalMaxExtension();
        arm.check_fix_overExtend();

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
         gripper.moveToAngle(gamepad2.right_stick_x == 0 ? 0 : signum(gamepad2.right_stick_x) * 0.01);


        if (gamepad2.cross) arm.moveByPIDAngle(0);
        if (gamepad2.circle) arm.moveByPIDAngle(-2350);
        if (gamepad2.triangle) arm.moveByPIDAngle(-2920);
        if (gamepad2.touchpad) arm.moveByPIDAngle(3200);

//        if (gamepad2.cross && !arm.getOverExtend()) arm.moveByPIDExtension(0);
//        if (gamepad2.circle && !arm.getOverExtend()) arm.moveByPIDExtension(0);
//        if (gamepad2.triangle && !arm.getOverExtend()) arm.moveByPIDExtension(2990);



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

    public void moveToSetPoint(double anglePos, double extensionPos, double gripperAngle){
        arm.goToSetPoint(anglePos, extensionPos);
        gripper.turnToAngle(gripperAngle);
    }
}
