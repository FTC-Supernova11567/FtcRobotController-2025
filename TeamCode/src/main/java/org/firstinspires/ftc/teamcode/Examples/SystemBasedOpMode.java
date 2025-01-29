package org.firstinspires.ftc.teamcode.Examples;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class SystemBasedOpMode extends OpMode {
    //Here you create an empty system object
    ExampleSystemClass mySystem;

    //Here you initialize the system object,
    // along with passing the hardwareMap and gamepad1 through the constructor of the system.
    @Override
    public void init() {
        mySystem = new ExampleSystemClass(hardwareMap, gamepad2);
    }

    //Here you use the actions you created in your system class.
    @Override
    public void loop() {
        telemetry.update();
        mySystem.resetEncoder();
        // mySystem.changeAngle();
        mySystem.changeLength();
//        mySystem.setPositionWithXbutton(-2000);
//        mySystem.setPositionWithBbutton(0);
        mySystem.correctByPID();
        telemetry.addData("Current pose", mySystem.getPose());
        telemetry.addData("Wanted position", mySystem.getWantedPosition());
        telemetry.addData("Motor power", mySystem.getMotor().getPower());
        telemetry.addData("Correction", mySystem.getCorrection());
        telemetry.addData("Actual value into motor", mySystem.getCorrection() / 10);
    }
}
