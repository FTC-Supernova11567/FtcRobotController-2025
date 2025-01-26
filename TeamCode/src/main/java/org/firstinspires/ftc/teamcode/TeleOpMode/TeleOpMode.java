package org.firstinspires.ftc.teamcode.TeleOpMode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

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

    @Override
    public void init() {
        arm = new Arm(hardwareMap, gamepad2);
        gripper = new Gripper(hardwareMap, gamepad1, gamepad2);

        mecanum = new MecanumDrive(hardwareMap, gamepad1);

//        FtcDashboard dashboard = FtcDashboard.getInstance();
//        telemetry = dashboard.getTelemetry();

        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {
        arm.armControl();
        gripper.gripperControl();
        mecanum.mecanumAlL();
        telemetry.update();

        telemetry.addData("Status", "Run Time: " + getRuntime());
        telemetry.addData("gripper angle", gripper.getAngle());
        telemetry.addData("arm angle", -arm.getAngle() - 66.8466);
//        telemetry.addData("left stick y", gamepad2.left_stick_y);
//        telemetry.addData("gripper direction", myGripper.getDirection() );
        telemetry.addData("Pose", -arm.getAngle());
        telemetry.addData("Extension", arm.getExtend());
        telemetry.addData("Check Cos", Math.cos(0));
        telemetry.addData("CosAngle", Math.cos(Math.toRadians(-arm.getAngle())));
        telemetry.addData("ArcCosAngle", Math.acos(Math.cos(Math.toRadians(-arm.getAngle()))));
       // telemetry.addData("ExCalc", Math.abs(Math.cos(-arm.getAngle())) * arm.getExtensionMotor().encoder.getPosition());
        telemetry.addData("extension current", arm.getExtensionMotor().getCurrent(CurrentUnit.MILLIAMPS));
        telemetry.addData("average extension", arm.getAverage(300));
    }
}
