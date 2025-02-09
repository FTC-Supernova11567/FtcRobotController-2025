package org.firstinspires.ftc.teamcode.Examples;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ImprovedMecanum {
    private final DcMotor frontRight;
    private final DcMotor backRight;
    private final DcMotor frontLeft;
    private final DcMotor backLeft;

    public ImprovedMecanum(HardwareMap constHardwareMap) {
        frontRight = constHardwareMap.get(DcMotor.class, "frontRight");
        backRight = constHardwareMap.get(DcMotor.class, "backRight");
        frontLeft = constHardwareMap.get(DcMotor.class, "frontLeft");
        backLeft = constHardwareMap.get(DcMotor.class, "backLeft");

        frontLeft.setDirection(DcMotorEx.Direction.REVERSE);
        backLeft.setDirection(DcMotorEx.Direction.REVERSE);

    }

    public void drive(double y, double x, double rx){
        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        frontLeft.setPower(frontLeftPower);
        backLeft.setPower(backLeftPower);
        frontRight.setPower(frontRightPower);
        backRight.setPower(backRightPower);
    }
    public void forward(){
        this.drive(1.0, 0.0, 0.0);
    }
    public void backwards(){
        this.drive(-1.0, 0.0 , 0.0 );
    }
    public void right(){
        this.drive(0.0, 1.0, 0.0);
    }
    public void left(){
        this.drive(0.0 , -1.0 , 0.0);
    }
    public void rightFront(){
        this.drive(1.0, 1.0, 0.0);
    }
    public void rightBack(){
        this.drive(-1.0, 1.0, 0.0);
    }
    public void leftFront(){
        this.drive(1, -1, 0.0);
    }
    public void lefBack(){
        this.drive(-1, -1, 0.0);
    }
    public void rotateRight(){
        this.drive(0.0 , 0.0 , 1.0);
    }
    public void rotateLeft(){
        this.drive(0.0, 0.0, -1.0);
    }
}
