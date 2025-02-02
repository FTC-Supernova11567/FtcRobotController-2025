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
        mySystem = new ExampleSystemClass(hardwareMap, gamepad1);
    }

    //Here you use the actions you created in your system class.
    @Override
    public void loop() {
        mySystem.activateWithAButton(0.5);
    }
}
