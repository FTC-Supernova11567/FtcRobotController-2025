package org.firstinspires.ftc.teamcode.DriveTrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumDrive {
    private final DcMotor frontRight;
    private final DcMotor backRight;
    private final DcMotor frontLeft;
    private final DcMotor backLeft;
    private Gamepad mecanumGamepad;

    public MecanumDrive(HardwareMap constHardwareMap, Gamepad constmecanumGamepad) {
        frontRight = constHardwareMap.get(DcMotor.class, "frontRight");
        backRight = constHardwareMap.get(DcMotor.class, "backRight");
        frontLeft = constHardwareMap.get(DcMotor.class, "frontLeft");
        backLeft = constHardwareMap.get(DcMotor.class, "backLeft");

        mecanumGamepad = constmecanumGamepad;
    }

    public void setMecanumDrivePowerAndDirection() {
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        if(mecanumGamepad.left_stick_x < 0){
            frontRight.setPower(-0.8);
            backRight.setPower(-0.8);
            frontLeft.setPower(0.8);
            backLeft.setPower(0.8);
        } else if (mecanumGamepad.left_stick_x > 0) {
            frontRight.setPower(0.8);
            backRight.setPower(0.8);
            frontLeft.setPower(-0.8);
            backLeft.setPower(-0.8);
        }
        if (mecanumGamepad.right_stick_y > 0 && mecanumGamepad.right_stick_x < 0) {
            frontRight.setPower(0);
            backRight.setPower(0.6);
            frontLeft.setPower(0.6);
            backLeft.setPower(0);
        } else if (mecanumGamepad.right_stick_y > 0 && mecanumGamepad.right_stick_x == 0) {
            frontRight.setPower(0.6);
            backRight.setPower(0.6);
            frontLeft.setPower(0.6);
            backLeft.setPower(0.6);
        } else if (mecanumGamepad.right_stick_y > 0 && mecanumGamepad.right_stick_x > 0) {
            frontRight.setPower(0.6);
            backRight.setPower(0);
            frontLeft.setPower(0);
            backLeft.setPower(0.6);
        } else if (mecanumGamepad.right_stick_y == 0 && mecanumGamepad.right_stick_x > 0) {
            frontRight.setPower(0.6);
            backRight.setPower(-0.6);
            frontLeft.setPower(-0.6);
            backLeft.setPower(0.6);
        } else if (mecanumGamepad.right_stick_y < 0 && mecanumGamepad.right_stick_x > 0) {
            frontRight.setPower(0);
            backRight.setPower(-0.6);
            frontLeft.setPower(-0.6);
            backLeft.setPower(0);
        } else if (mecanumGamepad.right_stick_y < 0 && mecanumGamepad.right_stick_x == 0) {
            frontRight.setPower(-0.6);
            backRight.setPower(-0.6);
            frontLeft.setPower(-0.6);
            backLeft.setPower(-0.6);
        } else if (mecanumGamepad.right_stick_y < 0 && mecanumGamepad.right_stick_x < 0) {
            frontRight.setPower(-0.6);
            backRight.setPower(0);
            frontLeft.setPower(0);
            backLeft.setPower(-0.6);
        } else if (mecanumGamepad.right_stick_y == 0 && mecanumGamepad.right_stick_x < 0) {
            frontRight.setPower(-0.6);
            backRight.setPower(0.6);
            frontLeft.setPower(0.6);
            backLeft.setPower(-0.6);
        }else{
            frontRight.setPower(0);
            backRight.setPower(0);
            frontLeft.setPower(0);
            backLeft.setPower(0);
        }
    }
}