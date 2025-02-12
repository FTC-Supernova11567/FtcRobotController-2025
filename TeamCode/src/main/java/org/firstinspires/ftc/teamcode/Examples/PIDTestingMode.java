package org.firstinspires.ftc.teamcode.Examples;

import static java.lang.Math.signum;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class PIDTestingMode extends OpMode {
    ExampleSystemClass mySystem;


    @Override
    public void init() {
        // perfect values for angle PID: 0.1, 0.00014,0.00061
        // perfect values for extension PID: 0.8, 0.000,0.00002
        mySystem = new ExampleSystemClass(hardwareMap, 0.1, 0.00014,0.00061);
    }


    @Override
    public void loop() {
        if (gamepad2.y) mySystem.resetEncoder();

        // mySystem.changeAngle();
        mySystem.changeLength(gamepad2.left_stick_y == 0 ? 0.0 : (signum(gamepad2.left_stick_y) * -0.8));

        if (gamepad2.x) mySystem.correctByPID(3000);
        if (gamepad2.b) mySystem.correctByPID(0);

        telemetry.addData("Current pose", mySystem.getPose());
        telemetry.addData("Wanted position", mySystem.getWantedPosition());
        telemetry.addData("Motor power", mySystem.getPower());
        telemetry.addData("Correction", mySystem.getCorrection());
        telemetry.addData("Actual value into motor", mySystem.getCorrection() / 10);
        telemetry.update();
    }
}
