package org.firstinspires.ftc.teamcode.DriveTrain;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class TankDrive extends OpMode {

    DcMotor frontRight;
    DcMotor backRight;

    DcMotor frontLeft;
    DcMotor backLeft;



    @Override
    public void init(){
        frontRight=hardwareMap.get(DcMotor.class, "frontRight");
        backRight=hardwareMap.get(DcMotor.class, "backRight");
        frontLeft= hardwareMap.get(DcMotor.class, "frontLeft");
        backLeft= hardwareMap.get(DcMotor.class, "backLeft");

    }
    @Override
    public void loop(){
        frontRight.setPower(-gamepad1.right_stick_y);
        backRight.setPower(-gamepad1.right_stick_y);
        frontLeft.setPower(gamepad1.left_stick_y);
        backLeft.setPower(gamepad1.left_stick_y);

    }

}
