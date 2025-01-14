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
    }

    public void rightTriggerExtension() {
        if(myGamepad.right_trigger != 0){
            extension.setRunMode(Motor.RunMode.RawPower);
            extension.set(0.7);
        }else{
            stopExtension();
        }
    }

    public void rightBumperRetraction() {
        if (myGamepad.right_bumper) {
            extension.setRunMode(Motor.RunMode.RawPower);
            extension.set(-0.8);
        }else{
            stopExtension();
        }

    }

    public void dpadAngle() {
        angleControl.setRunMode(Motor.RunMode.RawPower);
        if (myGamepad.dpad_up){
            angleControl.set(0.6);
        }else if(myGamepad.dpad_down){
            angleControl.set(-0.6);
        }else{
            stopAngleControl();
        }
    }

    public void stopExtension() {
        extension.set(0);
    }


    public void stopAngleControl() {
        angleControl.set(0);
    }

}
