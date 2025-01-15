package org.firstinspires.ftc.teamcode.DriveTrain;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class MecanumDriveTeleOpMode extends OpMode {
    MecanumDrive mecanum;

    @Override
    public void init() {
        mecanum = new MecanumDrive(hardwareMap, gamepad1);
    }

    @Override
    public void loop() {
        mecanum.rotateLeft();
    }
}
