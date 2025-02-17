package org.firstinspires.ftc.teamcode.Examples;

import static java.lang.Math.signum;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ExampleSystemClass {

    private final DcMotorEx PIDMotor;

    private final PIDControl PIDController;
    private double wantedPosition = 0;

    private double PIDPower;

    //At the default constructor you initialize the motor .
    public ExampleSystemClass(HardwareMap constHardwareMap, double kP, double kI, double kD) {
//       myMotor = constHardwareMap.get(DcMotor.class, "myMotor");
        PIDMotor = constHardwareMap.get(DcMotorEx.class, "PIDMotor");
        // Only when testing extension PID
        // DcMotorEx angleMotor = constHardwareMap.get(DcMotorEx.class, "angleMotor"); // Only when testing extension PID

        PIDMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        PIDMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        // angleMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // Only when testing extension PID

        PIDController = new PIDControl(kP, kI, kD);
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

    public void changeLength(double power){
        PIDMotor.setPower(power);
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

//    public void moveToAngleInsideEncoder(int position){
//        PIDMotor.setTargetPosition(-position);
//        PIDMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//    }

    void correctByPID(double pos){
        wantedPosition = -pos; // Only when testing angle PID
        // wantedPosition = pos; // Only when testing extension PID
        PIDPower = (PIDController.calculatePID(wantedPosition, PIDMotor.getCurrentPosition()))/10;
        PIDPower = Math.abs(PIDPower) > 0.8 ? signum(PIDPower) * 0.8 : PIDPower;
        PIDMotor.setPower(PIDPower);
        // PIDMotor.setPower(power);
    }


    public void resetEncoder(){
        PIDMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        PIDMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    public double getPose() {
        return PIDMotor.getCurrentPosition();
    }
    public double getPIDPower() {
        return PIDMotor.getPower();
    }
    public double getCorrection(){
        return PIDPower;
    }
    public double getWantedPosition(){
        return wantedPosition;
    }
}
