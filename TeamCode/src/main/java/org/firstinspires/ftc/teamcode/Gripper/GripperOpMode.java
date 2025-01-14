package org.firstinspires.ftc.teamcode.Gripper;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp
public class GripperOpMode extends OpMode {
    Gripper gripper;


    @Override
    public void init() {
        gripper = new Gripper(hardwareMap, gamepad2);

    }

    @Override
    public void loop() {
        gripper.anglePlace(215);
        gripper.catcherDirection();
    }

}
