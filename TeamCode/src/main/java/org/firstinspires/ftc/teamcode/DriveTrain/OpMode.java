package org.firstinspires.ftc.teamcode.DriveTrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class OpMode {
    private DcMotor frontRight;
    private DcMotor backRight;
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private Gamepad myGamePad;

    public OpMode(HardwareMap consthardwaremap, Gamepad constGamepad){
        frontRight=consthardwaremap.get(DcMotor.class,"frontRight");
        backRight= consthardwaremap.get(DcMotor.class, "backRight");
        frontLeft= consthardwaremap.get(DcMotor.class, "frontLeft");
        backLeft=consthardwaremap.get(DcMotor.class, "backLeft");
        myGamePad=constGamepad;
    }
    void tankDrive(double power){
        frontRight.setPower(0.7);
        backRight.setPower(0.7);
        frontLeft.setPower(-0.7);
        backLeft.setPower(-0.7);
    }
    void mecanumDrive(double power){
        frontRight.setPower(power);
        backRight.setPower(power);
        frontLeft.setPower(-power);
        backLeft.setPower(-power);

        frontRight.setPower(-power);
        backRight.setPower(power);
        frontLeft.setPower(-power);
        backLeft.setPower(power);
    }
    void stopMotor(double power){
        frontRight.setPower(0);
        backRight.setPower(0);
        frontLeft.setPower(0);
        backLeft.setPower(0);
    }
}
