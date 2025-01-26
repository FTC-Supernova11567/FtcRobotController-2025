package org.firstinspires.ftc.teamcode.Arm;


import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

public class Arm {
    private final DcMotorEx extensionMotor;
    private final DcMotorEx angleMotor;
    private Gamepad gamepad;

    //private int wristSetpoint = 0;
    //TODO: PID management

//    private PIDFControllerwristController = new PIDFController(0.5, 0.0, 0.0, 1.0);
//    private PIDFController extensionController = new PIDFController(5.0, 0.0, 0.0, 0.0);
    //TODO: PID management

    public Arm(HardwareMap hardwareMap, Gamepad constGamepad) {
        extensionMotor = hardwareMap.get(DcMotorEx.class, "extension");
        extensionMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        //extensionMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        extensionMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

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
        angleMotor.setPower(0.6);
    }

    public void angleUp() {
        angleMotor.setPower(-0.6);
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
     //   if (Math.abs(Math.cos(Math.PI / 180 / getAngle()) *  extensionMotor.encoder.getPosition()) < 1100) {
     //       extensionMotor.set(-0.8);
     //   }
     //   else{
     //       stopExtension();
     //   }
     //   // if (Math.abs(Math.cos(-getAngle() - 64.8466) * extensionMotor.getCurrentPosition())  > 1676)



        if(Math.abs(extensionMotor.getCurrentPosition()) > 3000){
            stopExtension();
        }
        else{
            extensionMotor.setPower(0.8);
        }


    }

    // public void reset(){
    //     if(gamepad2.left_trigger!=0){
    //         extensionMotor.setPositionPIDFCoefficients(0.0);
    //     }
    // }

    public void retract() {
        if (extensionMotor.getCurrentPosition() >= 0) {
            extensionMotor.setPower(-0.8);
        }
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
        AutoResetEncoder();
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

    public void resetEncoder(){
        extensionMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        extensionMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);;
    }

    public void AutoResetEncoder(){
        if(extensionMotor.getCurrent(CurrentUnit.MILLIAMPS) > 20){
            resetEncoder();
        }
    }

    public void resetEncoderTeleOp(){
        if (gamepad.y){
            resetEncoder();
        }
    }


    public void armControl() {
        extensionButtonControl();
        dpadAngle();
        resetEncoderTeleOp();
    }

    public DcMotorEx getExtensionMotor(){
        return extensionMotor;
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

    public double getAverage(int count){
        double average = extensionMotor.getCurrent(CurrentUnit.MILLIAMPS);
        if(count >= 0){
            average += extensionMotor.getCurrent(CurrentUnit.MILLIAMPS);
            count --;
        }
        average /= count;
        return average;
    }
}


