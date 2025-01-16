package org.firstinspires.ftc.teamcode.Arm;


import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm{
    private Motor extension;
    private Motor angleControl;
    private Gamepad myGamepad;

    public Arm(HardwareMap constHardwareMap, Gamepad constMyGamePad) {
        extension = new Motor(constHardwareMap, "extension");
        angleControl = new Motor(constHardwareMap, "angleControl");
        myGamepad = constMyGamePad;
        extension.setInverted(true);
        angleControl.setInverted(true);
    }

    public void angleDown(){
        angleControl.set(-0.6);
    }

    public void angleUp(){
        angleControl.set(0.6);
    }

    public void stopExtension() {
        extension.set(0);
    }

    public void stopAngleControl() {
        angleControl.set(0);
    }

    public void retract(){
        extension.setRunMode(Motor.RunMode.RawPower);
        extension.set(-0.8);
    }
    public void rightBumperRetraction() {
        if (myGamepad.right_bumper) {
            retract();
        }
        else{
            stopExtension();
        }

    }

    public Motor getAngleControl(){
        return angleControl;
    }

    public void extend(){
        extension.setRunMode(Motor.RunMode.RawPower);
        extension.set(0.7);
    }

    public void rightTriggerExtension() {
        if (myGamepad.right_trigger != 0) {
            extend();
        } else {
            stopExtension();
        }
    }

    public void extensionControl(){
        rightTriggerExtension();
        rightBumperRetraction();

    }
    public void armControl(){
        extensionControl();
        dpadAngle();
    }

    public void dpadAngle() {
        angleControl.setRunMode(Motor.RunMode.RawPower);
        if (myGamepad.dpad_up){
            angleUp();
        }
        else if(myGamepad.dpad_down){
            angleDown();
        }
        else{
            stopAngleControl();
        }
    }

    public double getAngle(){
        return angleControl.getCurrentPosition();
    }
}
