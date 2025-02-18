package org.firstinspires.ftc.teamcode.Arm;


import static java.lang.Math.signum;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Utils.PIDControl;
import org.firstinspires.ftc.teamcode.Gripper.Gripper;
import org.firstinspires.ftc.teamcode.Utils.PIDControl;

public class Arm {
    private final DcMotorEx extensionMotor;
    private final DcMotorEx angleMotor;

    private final PIDControl anglePID;
    private final PIDControl extensionPID;

    private double legalMaxExtension;

    private double wantedBusketAngle;
    private double wantedBusketExtension;
    private double power;
    private boolean overExtend = false;

    //private int wristSetpoint = 0;
    //TODO: PID management

//    private PIDFControllerwristController = new PIDFController(0.5, 0.0, 0.0, 1.0);
//    private PIDFController extensionController = new PIDFController(5.0, 0.0, 0.0, 0.0);
    //TODO: PID management

    public Arm(HardwareMap hardwareMap) {
        extensionMotor = hardwareMap.get(DcMotorEx.class, "extension");
        extensionMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        //extensionMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        extensionMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        //PIDTestingMode() =new PIDTestingMode();

        anglePID = new PIDControl(0.003, 0, 0);
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

    public void updateLegalMaxExtension(){
        legalMaxExtension = 2500 / Math.cos(Math.toRadians(-getAngle()));;
    }

    public void check_fix_overExtend(){
        if (extensionMotor.getCurrentPosition() > legalMaxExtension){
            overExtend = true;
            extensionMotor.setPower(extensionPID.calculatePID(legalMaxExtension, extensionMotor.getCurrentPosition()));
        }
        else{
            overExtend = false;
        }
    }

    public void stopAngle() {
        angleMotor.setPower(0);
    }

    public void stopExtension() {
        extensionMotor.setPower(0);
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

    public void extend() {
        if (-angleMotor.getCurrentPosition() >= 3600){
            return;
        }
        if (extensionMotor.getCurrentPosition() < legalMaxExtension) {
            extensionMotor.setPower(0.8);
        }
        else{
            stopExtension();
        }
    }

    public void retract() {
        if (extensionMotor.getCurrentPosition() >= 25){
            extensionMotor.setPower(-0.8);
        }
        else {
            stopExtension();
        }
    }

    public void moveByPIDAngle(double anglePos){
        wantedBusketAngle = anglePos;
        power = anglePID.calculatePID(wantedBusketAngle, angleMotor.getCurrentPosition());
        angleMotor.setPower(power);
    }

    public void moveByPIDExtension(double extensionPos){

//        legalMaxExtension = 2500 / Math.cos(Math.toRadians(-getAngle())); // finding the max extension of our arm
        wantedBusketExtension = 2500 > (Math.abs(Math.cos(Math.toRadians(-getAngle())) * extensionPos)) ? extensionPos : legalMaxExtension;

        power = (extensionPID.calculatePID(wantedBusketExtension, extensionMotor.getCurrentPosition()))/10;
        power = Math.abs(power) > 0.8 ? signum(power) * 0.8 : power;
        extensionMotor.setPower(power);
    }

    public void goToSetPoint(double anglePos, double extensionPos){
        moveByPIDAngle(anglePos);
        moveByPIDExtension(extensionPos);
    }

    public void moveToSetPoint(double anglePos, double extensionPos, double gripperAngle, Gripper gripper){
        goToSetPoint(anglePos, extensionPos);
        gripper.turnToAngle(gripperAngle);
    }

    public double getAngle () {return (((double) angleMotor.getCurrentPosition() + 1423) / 8192 * 360);}
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
    public boolean getOverExtend(){
        return overExtend;
    }


    public void setAngleMotorPower(double power){
        angleMotor.setPower(power);
    }
    public void setExtensionMotorPower(double power){
        extensionMotor.setPower(power);
    }
}


