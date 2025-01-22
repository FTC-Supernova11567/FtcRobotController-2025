package org.firstinspires.ftc.teamcode.Arm;


import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Arm {
    private final DcMotorEx extensionMotor;
    private final DcMotorEx angleMotor;
    private Gamepad gamepad;

    //private int wristSetpoint = 0;
    //TODO: PID management

//    private PIDFController wristController = new PIDFController(0.5, 0.0, 0.0, 1.0);
//    private PIDFController extensionController = new PIDFController(5.0, 0.0, 0.0, 0.0);
    //TODO: PID management

    public Arm(HardwareMap hardwareMap, Gamepad constGamepad) {
        extensionMotor = hardwareMap.get(DcMotorEx.class, "extension");
        extensionMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

//        extensionMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//        extensionMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //TODO: PID management

        angleMotor = hardwareMap.get(DcMotorEx.class, "angleControl");
        angleMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

//        angleMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//        angleMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //TODO: PID management

//        wristController.setTolerance(2000);
        //TODO: PID management
        gamepad = constGamepad;
    }

    public void angleDown() {
        angleMotor.setPower(-0.6);
    }

    public void angleUp() {
        angleMotor.setPower(0.6);
    }

    public void stopExtension() {
        extensionMotor.setPower(0);
    }
    public void stopTeleop(){
        if (gamepad.right_bumper==false && gamepad.right_trigger==0){
            stopExtension();
        }
    }

    public void stopAngle() {
        angleMotor.setPower(0);
    }

    public void extend() {
        if (Math.cos(getAngle()) * extensionMotor.getCurrentPosition() < 1676) {
            extensionMotor.setPower(0.8);
        } else if (Math.cos(getAngle()) * extensionMotor.getCurrentPosition()  > 1676) {
            stopExtension();
        }
         else if (extensionMotor.getCurrentPosition() < -3400){
            stopExtension();
        }


    }

    public void reset(){
        if(gamepad2.left_trigger!=0){
            extensionMotor.setPositionPIDFCoefficients(0.0);
        }
    }

    public void retract() {
        extensionMotor.setPower(-0.8);
    }

    public void rightBumperRetract() {
        if (gamepad.right_bumper) {
            retract();
        }
    }

    public void rightTriggerExtend() {
        if (gamepad.right_trigger != 0) {
            extend();
        }
    }

    public void extensionButtonControl() {
        rightTriggerExtend();
        rightBumperRetract();
        stopTeleop();
    }

    public void dpadAngle() {
        if (gamepad.dpad_up) {
            angleUp();
        } else if (gamepad.dpad_down) {
            angleDown();
        } else {
            stopAngle();
        }
    }

    public void armControl() {
        extensionButtonControl();
        dpadAngle();
    }

//    public void setArmAngle(int position) {
//        wristSetpoint = position;
//    }
//
//    public void update() {
//        angleMotor.setPower(wristController.calculate(angleMotor.getCurrentPosition(), wristSetpoint));
//    }

    //TODO: PID management

    public double getAngle () {
        return (((double) angleMotor.getCurrentPosition()) / 8192 * 360);
    }
    public int getExtend(){
        return extensionMotor.getCurrentPosition();
    }
}


