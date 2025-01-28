package org.firstinspires.ftc.teamcode.Examples;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ExampleSystemClass {
    //Here goes the components of the system.
//    private final DcMotor myMotor;
    private final DcMotorEx PIDMotor;
    private Gamepad gamepad;

    // private PIDFController pidfController = new PIDFController(0.5, 0, 0, 0);

    private PIDControl rotationPID = new PIDControl(0.015,0,0.00);
    private double wnatedPosition = 0;

    //At the default constructor you initialize the motor .
    public ExampleSystemClass(HardwareMap constHardwareMap, Gamepad constGamepad) {
//        myMotor = constHardwareMap.get(DcMotor.class, "myMotor");
        PIDMotor = constHardwareMap.get(DcMotorEx.class, "PIDMotor");

        PIDMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        PIDMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        gamepad = constGamepad;
    }

    public void changeAngle(){
        PIDMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        if (gamepad.left_stick_y < 0){
            PIDMotor.setPower(-0.6);
        }
        else if(gamepad.left_stick_y > 0){
            PIDMotor.setPower(0.6);
        }
        else if(gamepad.left_stick_y == 0){
            PIDMotor.setPower(0);
        }
    }
    //Here you write the actions your system can do as functions.
//    void activateMyMotor(double power) {
//        myMotor.setPower(power);
//    }
//
//    void stopMyMotor() {
//        myMotor.setPower(0);
//    }

//    void activateWithAButton(double power) {
//        if (gamepad.a) {
//            activateMyMotor(power);
//        } else {
//            stopMyMotor();
//        }
//    }

//    void setPositionWithBbutton(int wantedPosition) {
//        if (gamepad.b) {
//            PIDMotor.setPower(pidfController.calculate(PIDMotor.getCurrentPosition(), wantedPosition));
//        } else {
//            PIDMotor.setPower(0);
//        }
//    }
//    void setPositionWithXbutton(int wantedPosition) {
//        if (gamepad.x) {
//            PIDMotor.setPower(pidfController.calculate(PIDMotor.getCurrentPosition(), wantedPosition));
//        } else {
//            PIDMotor.setPower(0);
//        }
//    }

//    void setPositionWithPID(){
//
//        if (gamepad.x){
//            PIDMotor.setTargetPosition(-2000);
//            PIDMotor.setPositionPIDFCoefficients(0.9);
//            PIDMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            PIDMotor.setPower(0.8);
//            //PIDMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        }
//        else if (gamepad.b){
//            PIDMotor.setTargetPosition(0);
//            PIDMotor.setPositionPIDFCoefficients(0.9);
//            PIDMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            PIDMotor.setPower(0.8);
//            //PIDMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        }
//    }

    void correctByPID(){

        if (gamepad.x){
            wnatedPosition = -2000;
            double power = rotationPID.calculatePID(-2000, PIDMotor.getCurrentPosition());
            PIDMotor.setPower(power);
        }
        else if (gamepad.b){
            wnatedPosition = 0;
            double power = rotationPID.calculatePID(0, PIDMotor.getCurrentPosition());
            PIDMotor.setPower(power);
        }
    }


    void resetEncoder(){
        if(gamepad.y){
            PIDMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            PIDMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    double getPose() {
        return PIDMotor.getCurrentPosition();
    }

    DcMotorEx getMotor(){
        return PIDMotor;
    }
    double getCorrection(){
        return rotationPID.calculatePID(wnatedPosition, PIDMotor.getCurrentPosition());
    }
}
