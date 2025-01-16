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

        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void rotateLeftTeleOp() {
        if (mecanumGamepad.left_stick_x < 0) {
            rotateLeft();
        }
    }

    public void rotateLeft() {
        frontRight.setPower(-0.8);
        backRight.setPower(-0.8);
        frontLeft.setPower(0.8);
        backLeft.setPower(0.8);
    }


    public void rotateRightTeleOp() {
        if (mecanumGamepad.left_stick_x > 0) {
            rotateRight();
        }
    }

    public void rotateRight() {
        frontRight.setPower(0.8);
        backRight.setPower(0.8);
        frontLeft.setPower(-0.8);
        backLeft.setPower(-0.8);
    }


    public void diagonalFrontLeftTeleOp() {
        if (mecanumGamepad.right_stick_y > 0 && mecanumGamepad.right_stick_x < 0) {
            diagonalFrontLeft();
        }
    }

    public void diagonalFrontLeft() {
        frontRight.setPower(0);
        backRight.setPower(0.6);
        frontLeft.setPower(0.6);
        backLeft.setPower(0);
    }


    public void forwardTeleOp() {
        if (mecanumGamepad.right_stick_y > 0 && mecanumGamepad.right_stick_x == 0) {
            forward();
        }
    }

    public void forward() {
        frontRight.setPower(0.6);
        backRight.setPower(0.6);
        frontLeft.setPower(0.6);
        backLeft.setPower(0.6);
    }


    public void diagonalFrontRightTeleOp() {
        if (mecanumGamepad.right_stick_y > 0 && mecanumGamepad.right_stick_x > 0) {
            diagonalFrontRight();
        }
    }

    public void diagonalFrontRight() {
        frontRight.setPower(0.6);
        backRight.setPower(0);
        frontLeft.setPower(0);
        backLeft.setPower(0.6);
    }


    public void rightTeleOp() {
        if (mecanumGamepad.right_stick_y == 0 && mecanumGamepad.right_stick_x > 0) {
            right();
        }
    }

    public void right() {
        frontRight.setPower(0.6);
        backRight.setPower(-0.6);
        frontLeft.setPower(-0.6);
        backLeft.setPower(0.6);
    }


    public void diagonalBackRightTeleOp() {
        if (mecanumGamepad.right_stick_y < 0 && mecanumGamepad.right_stick_x > 0) {
            diagonalBackRight();
        }
    }

    public void diagonalBackRight() {
        frontRight.setPower(0);
        backRight.setPower(-0.6);
        frontLeft.setPower(-0.6);
        backLeft.setPower(0);
    }


    public void backwardTeleOp() {
        if (mecanumGamepad.right_stick_y < 0 && mecanumGamepad.right_stick_x == 0) {
            backward();
        }
    }

    public void backward() {
        frontRight.setPower(-0.6);
        backRight.setPower(-0.6);
        frontLeft.setPower(-0.6);
        backLeft.setPower(-0.6);
    }


    public void diagonalBackLeftTeleOp(){
          if(mecanumGamepad.right_stick_y< 0&&mecanumGamepad.right_stick_x< 0){
              diagonalBackLeft();
          }
    }

    public void diagonalBackLeft() {
        frontRight.setPower(-0.6);
        backRight.setPower(0);
        frontLeft.setPower(0);
        backLeft.setPower(-0.6);
    }



    public void leftTeleOp() {
        if (mecanumGamepad.right_stick_y == 0 && mecanumGamepad.right_stick_x < 0) {
            left();
        }
    }

    public void left() {
        frontRight.setPower(-0.6);
        backRight.setPower(0.6);
        frontLeft.setPower(0.6);
        backLeft.setPower(-0.6);
    }



    public void stopTeleOp(){
        if(mecanumGamepad.right_stick_y == 0 && mecanumGamepad.right_stick_x == 0){
            stop();
        }
    }
    public void stop(){
        frontRight.setPower(0);
        backRight.setPower(0);
        frontLeft.setPower(0);
        backLeft.setPower(0);
    }



    public void mecanumAlL(){
        forwardTeleOp();
        diagonalFrontRightTeleOp();
        diagonalFrontLeftTeleOp();

        backwardTeleOp();
        diagonalBackRightTeleOp();
        diagonalBackLeftTeleOp();

        rightTeleOp();
        leftTeleOp();

        rotateLeftTeleOp();
        rotateRightTeleOp();

        stopTeleOp();
    }
}