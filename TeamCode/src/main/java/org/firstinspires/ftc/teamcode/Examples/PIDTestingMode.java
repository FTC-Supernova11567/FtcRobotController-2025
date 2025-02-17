package org.firstinspires.ftc.teamcode.Examples;

import static java.lang.Math.signum;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Config
@TeleOp
public class PIDTestingMode extends OpMode {
    ExampleSystemClass mySystem;
    public static double kP = 0.8;
    public static double kI = 0;
    public static double kD = 0.0002;

    FtcDashboard dashboard = FtcDashboard.getInstance();
    // TelemetryPacket packet = new TelemetryPacket();

    @Override
    public void init() {
        // perfect values for angle PID: 0.1, 0.00014,0.00061
        // perfect values for` extension PID: 0.8, 0.000,0.00002
        mySystem = new ExampleSystemClass(hardwareMap, kP, kI, kD);
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
    }


    @Override
    public void loop() {
        if (gamepad2.share) mySystem.resetEncoder();

        // mySystem.changeAngle();
        mySystem.changeLength(gamepad2.left_stick_y == 0 ? 0.0 : (signum(gamepad2.left_stick_y) * -0.8));

        if (gamepad2.triangle) mySystem.correctByPID(2750);
        if (gamepad2.circle) mySystem.correctByPID(2300);
        if (gamepad2.cross) mySystem.correctByPID(500);
        if (gamepad2.touchpad) mySystem.correctByPID(3200);

        telemetry.addData("!!Current pose", mySystem.getPose());
        telemetry.addData("!Wanted position", mySystem.getWantedPosition());
        // telemetry.addData("Motor power", mySystem.getPIDPower());
        telemetry.addData("Correction", mySystem.getCorrection());
        telemetry.update();
    }
}
