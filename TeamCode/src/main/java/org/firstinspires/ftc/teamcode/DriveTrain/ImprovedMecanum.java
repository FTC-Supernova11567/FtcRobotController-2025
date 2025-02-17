package org.firstinspires.ftc.teamcode.DriveTrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Utils.PIDControl;

public class ImprovedMecanum {
    private final DcMotor frontRight;
    private final DcMotor backRight;
    private final DcMotor frontLeft;
    private final DcMotor backLeft;

    private final PIDControl PIDMecanum;

    public ImprovedMecanum(HardwareMap constHardwareMap) {
        frontRight = constHardwareMap.get(DcMotor.class, "frontRight");
        backRight = constHardwareMap.get(DcMotor.class, "backRight");
        frontLeft = constHardwareMap.get(DcMotor.class, "frontLeft");
        backLeft = constHardwareMap.get(DcMotor.class, "backLeft");

        PIDMecanum = new PIDControl(2, 0, 0);

        frontLeft.setDirection(DcMotorEx.Direction.REVERSE);
        backLeft.setDirection(DcMotorEx.Direction.REVERSE);

        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontRight.setPower(0);
        backRight.setPower(0);
        frontLeft.setPower(0);
        backLeft.setPower(0);

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

    public void turnToAngle(double angle, double currentOrientation){
        double output = PIDMecanum.calculatePID(Math.toRadians(angle), currentOrientation);

        frontLeft.setPower(-output);
        backLeft.setPower(-output);
        frontRight.setPower(output);
        backRight.setPower(output);
    }

    public void smartDrive(double y, double x, double rx, double wantedAngle, double currentOrientation){

        double output = PIDMecanum.calculatePID(Math.toRadians(wantedAngle), currentOrientation);
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

        frontLeft.setPower(-output + (y + x + rx) / denominator);
        backLeft.setPower(-output + (y - x + rx) / denominator);
        frontRight.setPower(output + (y - x - rx) / denominator);
        backRight.setPower(output + (y + x - rx) / denominator);
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
        this.drive(1, 1, 0);
    }
    public void rightBack(){
        this.drive(-1, 1, 0);
    }
    public void leftFront(){
        this.drive(1, -1, 0);
    }
    public void leftBack(){
        this.drive(-1, -1, 0);
    }

    public void rotateRight(){
        this.drive(0.0 , 0.0 , 1.0);
    }
    public void rotateLeft(){
        this.drive(0.0, 0.0, -1.0);
    }

    public void stop(){this.drive(0, 0, 0);}
}
