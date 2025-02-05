package org.firstinspires.ftc.teamcode.DriveTrain;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class NewMecanumDrive {
    private final DcMotor frontRight;
    private final DcMotor backRight;
    private final DcMotor frontLeft;
    private final DcMotor backLeft;
    private final double Power = 0.6;
    private Gamepad mecanumGamepad;

    public NewMecanumDrive(HardwareMap constHardwareMap, Gamepad constmecanumGamepad) {
        frontRight = constHardwareMap.get(DcMotor.class, "frontRight");
        backRight = constHardwareMap.get(DcMotor.class, "backRight");
        frontLeft = constHardwareMap.get(DcMotor.class, "frontLeft");
        backLeft = constHardwareMap.get(DcMotor.class, "backLeft");
        mecanumGamepad = constmecanumGamepad;

    }
    public void forward(){
        frontRight.setPower(Power);
        frontLeft.setPower(Power);
        backRight.setPower(Power);
        backLeft.setPower(Power);
    }

    public void backwards(){
        frontRight.setPower(-Power);
        frontLeft.setPower(-Power);
        backRight.setPower(-Power);
        backLeft.setPower(-Power);
    }

    public void right(){
        frontRight.setPower(-Power);
        frontLeft.setPower(Power);
        backRight.setPower(-Power);
        backLeft.setPower(Power);
    }

    public void left(){
        frontRight.setPower(Power);
        frontLeft.setPower(-Power);
        backRight.setPower(Power);
        backLeft.setPower(-Power);
    }

    public void diagonalFrontRight() {
        frontRight.setPower(0);
        backRight.setPower(Power);
        frontLeft.setPower(Power);
        backLeft.setPower(0);
    }

    public void diagonalFrontLeft() {
        frontRight.setPower(Power);
        backRight.setPower(0);
        frontLeft.setPower(0);
        backLeft.setPower(Power);
    }

    public void diagonalBackRight() {
        frontRight.setPower(-Power);
        backRight.setPower(0);
        frontLeft.setPower(0);
        backLeft.setPower(-Power);
    }
    public void diagonalBackLeft() {
        frontRight.setPower(0);
        backRight.setPower(Power);
        frontLeft.setPower(Power);
        backLeft.setPower(0);
    }
    public void stop(){
        frontRight.setPower(0);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    public void teleopForward() {
        if (-mecanumGamepad.right_stick_y > 0 && mecanumGamepad.right_stick_x == 0) {
            forward();
        }
    }
    public void teleopBackwards(){
        if (-mecanumGamepad.right_stick_y<0 && mecanumGamepad.right_stick_x==0) {
         backwards();
        }
    }

    public void teleopRight(){
        if (-mecanumGamepad.right_stick_y==0 && mecanumGamepad.right_stick_x >0){
            right();
        }
        }

    public  void teleopLeft() {
        if (-mecanumGamepad.right_stick_y == 0 && mecanumGamepad.right_stick_x < 0) {
            left();
        }
    }

    public void teleopDiagonalFrontRight(){
            if(-mecanumGamepad.right_stick_y>0 && mecanumGamepad.right_stick_x<0){
                right();
            }
        }
    public void teleopdiagonalFrontLeft(){
        if(-mecanumGamepad.right_stick_y>0 && mecanumGamepad.right_stick_x<0){
            diagonalFrontLeft();
        }
    }

    public void teleodiagnoalFrontRight(){
        if(-mecanumGamepad.right_stick_y<)
    }


}