package org.firstinspires.ftc.teamcode.Arm;


import static java.lang.Math.signum;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.Examples.PIDControl;

public class Arm {
    private final DcMotorEx extensionMotor;
    private final DcMotorEx angleMotor;
    private final PIDControl anglePID;
    private final PIDControl extensionPID;
    private double legalMaxExtension;

    private Gamepad gamepad;
    private double wantedBusketAngle;
    private double wantedBusketExtension;
    private double power;

    //private int wristSetpoint = 0;
    //TODO: PID management

//    private PIDFControllerwristController = new PIDFController(0.5, 0.0, 0.0, 1.0);
//    private PIDFController extensionController = new PIDFController(5.0, 0.0, 0.0, 0.0);
    //TODO: PID management

    public Arm(HardwareMap hardwareMap, Gamepad constGamepad) {
        extensionMotor = hardwareMap.get(DcMotorEx.class, "extension");
        extensionMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        //extensionMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        extensionMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        //PIDTestingMode() =new PIDTestingMode();

        anglePID = new PIDControl(0.1, 0.00014,0.00061);
        extensionPID = new PIDControl( 0.8, 0.000,0.00002);

        //TODO: PID management

        angleMotor = hardwareMap.get(DcMotorEx.class, "angleControl");
        angleMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        angleMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        // angleMotor.setCurrentAlert(1200, CurrentUnit.MILLIAMPS);
        //resetEncoder(angleMotor);

        //TODO: PID management

//        wristController.setTolerance(2000);
        //TODO: PID management
        gamepad = constGamepad;
    }

    public void resetAngleEncoder(){
        angleMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        angleMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void resetExtensionEncoder(){
        extensionMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        extensionMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
    }


    public void  autoReset(){
            angleDown();
            if(angleMotor.isOverCurrent()){
                resetAngleEncoder();
                stopAngle();
            }
    }

    public void angleDown() {
        if (angleMotor.isOverCurrent()){
            return;
        }
        angleMotor.setPower(0.7);
    }


    public void angleUp() {
        if (-angleMotor.getCurrentPosition() >= 3600){
            return;
        }
        angleMotor.setPower(-0.6);
    }

    public void stopAngle() {
        angleMotor.setPower(0);
    }

    public void stopExtension() {
        extensionMotor.setPower(0);
    }

    public void extend() {
        if (-angleMotor.getCurrentPosition() >= 3600){
            return;
        }
        if (Math.abs(Math.cos(Math.toRadians(-getAngle())) *  extensionMotor.getCurrentPosition()) < 2500) {
            extensionMotor.setPower(0.8);
        }
        else if (Math.abs(Math.cos(Math.toRadians(-getAngle())) *  extensionMotor.getCurrentPosition()) >= 2500){
            stopExtension();
        }
    }

    public void retract() {
        if (extensionMotor.getCurrentPosition() >= 50){
            extensionMotor.setPower(-0.8);
        }
        else {
            stopExtension();
        }
    }


//    public void rightBumperRetract() {
//        if(Math.abs(Math.cos(Math.toRadians(-getAngle()-66)) * extensionMotor.getCurrentPosition()) > 2500){
//            retract();
//        }
//        else if (gamepad.right_bumper) {
//            retract();
//        }
//        else if(!gamepad.right_bumper && gamepad.right_trigger == 0){
//            stopExtension();
//        }
//    }
//
//    public void rightTriggerExtend() {
//        if (gamepad.right_trigger != 0) {
//            extend();
//        }else if(gamepad.right_trigger == 0 && !gamepad.right_bumper){
//            stopExtension();
//        }
//    }
//
//    public void extensionButtonControl() {
//        rightTriggerExtend();
//        rightBumperRetract();
//
//    }

    public void moveByPIDAngle(double anglePos){
        wantedBusketAngle = -anglePos;
        power = (anglePID.calculatePID(wantedBusketAngle, angleMotor.getCurrentPosition()))/10;
        angleMotor.setPower(power);
    }

    public void moveByPIDExtension(double extensionPos){

        // Math.cos(Math.toRadians(-getAngle())) *  x == 2500

        legalMaxExtension = 2500 / Math.cos(Math.toRadians(-getAngle())); // finding the max extension of our arm
        wantedBusketExtension = 2500 > (Math.abs(Math.cos(Math.toRadians(-getAngle())) * extensionPos)) ? extensionPos : legalMaxExtension;

        power = (extensionPID.calculatePID(wantedBusketExtension, extensionMotor.getCurrentPosition()))/10;
        power = Math.abs(power) > 0.8 ? signum(power) * 0.8 : power;
        extensionMotor.setPower(power);
    }

    public void setPoints(){
        if (gamepad.a){
            wantedBusketAngle = 0;
            wantedBusketExtension = 0;
        }
        else if(gamepad.b){
            wantedBusketAngle = 2300; // Best amount of ticks in encoder for lower basket
            wantedBusketExtension = 0;
        }
        else if(gamepad.x){
            wantedBusketAngle = 2750; // Best amount of ticks in encoder for high basket - front robot
            wantedBusketExtension = 3440;

        }


        // else if(gamepad.back){
        //     wantedBusketAngle = 3450; // Best amount of ticks in encoder for high basket - behind robot
        // }
    }




//    public void stopArmMovment(){
//        if(extensionMotor.getCurrentPosition()<4000){
//            stopExtension();
//        }
//    }

//
//    public void AutoResetEncoder(){
//        if(extensionMotor.isAngleOverCurrent()){
//            resetEncoder();
//        }
//    }

    //    public void resetAngleEncoder(){
//        angleMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        angleMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//    }


    public void resetEncoderTeleOp(){
        if (gamepad.y){
            resetExtensionEncoder();
        }
    }


    public void resetAngleEncoderTeleop(){
        if(gamepad.right_stick_button){
            resetAngleEncoder();
        }
    }


//    public void armControl() {
//        extensionButtonControl();
//        dpadAngle();
//        resetEncoderTeleOp();
//        resetAngleEncoderTeleop();
//        autoReset();
//    }


//    public void setArmAngle(int position) {]

//        wristSetpoint = position;
//    }
//
//    public void update() {
//        angleMotor.setPower(wristController.calculate(angleMotor.getCurrentPosition(), wristSetpoint));
//    }

    //TODO: PID management


    public double getAngle () {
        return (((double) angleMotor.getCurrentPosition() + 1423) / 8192 * 360);
    }
    public int getExtend(){
        return extensionMotor.getCurrentPosition();
    }
    public double getAngleMotorPosition(){
        return angleMotor.getCurrentPosition();
    }
    public double getExtensionMotorPosition(){
        return extensionMotor.getCurrentPosition();
    }
    public boolean getIsOverCurrent(){
        return angleMotor.isOverCurrent();
    }
    public double getLegalMaxExtension(){
        return legalMaxExtension;
    }
    public double getWantedBusketExtension(){
        return wantedBusketExtension;
    }
    public double getPower(){
        return power;
    }
}


