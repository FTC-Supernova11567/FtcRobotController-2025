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
        mySystem.setPositionWithXbutton(-2000);
        mySystem.setPositionWithBbutton(0);
        telemetry.addData("Pose", mySystem.getPose());
    }
}
