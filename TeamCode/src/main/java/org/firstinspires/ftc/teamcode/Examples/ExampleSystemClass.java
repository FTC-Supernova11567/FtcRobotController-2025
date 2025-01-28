package org.firstinspires.ftc.teamcode.Examples;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ExampleSystemClass {

    private final DcMotorEx PIDMotor;
    private Gamepad gamepad;

    // private PIDFController pidfController = new PIDFController(0.5, 0, 0, 0);

    // PID stats for wrist
    //private final PIDControl rotationPID = new PIDControl(0.2, 0.000,0.0015);

    //PID stats for extension
    private final PIDControl extensionPID = new PIDControl(0.2, 0.000,0);
    private double wantedPosition = 0;

    //At the default constructor you initialize the motor .
    public ExampleSystemClass(HardwareMap constHardwareMap, Gamepad constGamepad) {
//        myMotor = constHardwareMap.get(DcMotor.class, "myMotor");
        PIDMotor = constHardwareMap.get(DcMotorEx.class, "PIDMotor");

        PIDMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        PIDMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        gamepad = constGamepad;
    }

//    public void changeAngle(){
//        if (gamepad.left_stick_y < 0){
//            PIDMotor.setPower(-0.8);
//        }
//        else if(gamepad.left_stick_y > 0){
//            PIDMotor.setPower(0.8);
//        }
//        else if(gamepad.left_stick_y == 0){
//            PIDMotor.setPower(0);
//        }
//    }

    public void changeLength(){
        if (gamepad.left_stick_y < 0){
            PIDMotor.setPower(0.8);
        }
        else if(gamepad.left_stick_y > 0){
            PIDMotor.setPower(-0.8);
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
            wantedPosition = 2500;
            double power = extensionPID.calculatePID(wantedPosition, PIDMotor.getCurrentPosition());
            PIDMotor.setPower(Math.max(-0.8, (power / 10)));
        }
        else if (gamepad.b){
            wantedPosition = 20;
            double power = extensionPID.calculatePID(wantedPosition, PIDMotor.getCurrentPosition());
            PIDMotor.setPower(Math.min(0.8, (power / 10)));
        }
    }


    public void resetEncoder(){
        if(gamepad.y){
            PIDMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            PIDMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    public double getPose() {
        return PIDMotor.getCurrentPosition();
    }

    public DcMotorEx getMotor(){
        return PIDMotor;
    }
    public double getCorrection(){
        return extensionPID.calculatePID(wantedPosition, PIDMotor.getCurrentPosition());
    }
    public double getWantedPosition(){
        return wantedPosition;
    }
}
