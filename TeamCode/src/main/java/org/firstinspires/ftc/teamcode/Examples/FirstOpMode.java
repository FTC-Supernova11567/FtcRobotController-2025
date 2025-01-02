package org.firstinspires.ftc.teamcode.Examples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class FirstOpMode extends OpMode {

    DcMotor myMotor;

    @Override
    public void init() {
        myMotor = hardwareMap.get(DcMotor.class, "myMotor");
    }

    @Override
    public void loop() {
    myMotor.setPower(-gamepad1.left_stick_y);
    }
}
