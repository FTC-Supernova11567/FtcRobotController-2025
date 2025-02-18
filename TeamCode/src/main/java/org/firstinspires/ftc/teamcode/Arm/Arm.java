package org.firstinspires.ftc.teamcode.Arm;


import static java.lang.Math.signum;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Utils.PIDControl;
import org.firstinspires.ftc.teamcode.Gripper.Gripper;

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



    public Arm(HardwareMap hardwareMap) {
        extensionMotor = hardwareMap.get(DcMotorEx.class, "extension");
        angleMotor = hardwareMap.get(DcMotorEx.class, "angleControl");

        extensionMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        angleMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        extensionMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        angleMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        extensionMotor.setPower(0);
        angleMotor.setPower(0);

        extensionPID = new PIDControl( 0.8, 0.000,0.00002);
        anglePID = new PIDControl(0.003, 0, 0);

        // angleMotor.setCurrentAlert(1200, CurrentUnit.MILLIAMPS);

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


