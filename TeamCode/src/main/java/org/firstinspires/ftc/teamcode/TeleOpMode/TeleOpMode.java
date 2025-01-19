package org.firstinspires.ftc.teamcode.TeleOpMode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

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

        gripper = new Gripper(hardwareMap, gamepad2);

        mecanum = new MecanumDrive(hardwareMap, gamepad1);

        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = dashboard.getTelemetry();

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
//        telemetry.addData("arm angle", myArm.getAngle());
        telemetry.addData("left stick y", gamepad2.left_stick_y);
//        telemetry.addData("gripper direction", myGripper.getDirection() );

    }
}
