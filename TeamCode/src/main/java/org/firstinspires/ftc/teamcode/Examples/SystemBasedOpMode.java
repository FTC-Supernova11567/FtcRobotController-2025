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
        mySystem.setPositionWithXbutton(0);
        mySystem.setPositionWithBbutton(-2000);
        telemetry.addData("Pose", mySystem.getPose());
        // telemetry.addData("x", gamepad2.x);
        // telemetry.addData("b", gamepad2.b);
        telemetry.addData("setPoint", mySystem.getTarget());
        telemetry.addData("Error", mySystem.getError());
        telemetry.addData("motorPower", mySystem.getPower());
        telemetry.addData("velocity", mySystem.getVelocity());
        telemetry.addData("PIDoutput", mySystem.getPIDOutput());


    }
}
