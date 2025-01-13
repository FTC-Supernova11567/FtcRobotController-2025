package org.firstinspires.ftc.teamcode.Arm;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

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

//    int getCurrentPosition() {
//        return extension.getCurrentPosition();
//    }

//    Motor getExtension() {
//        return extension;
//        //TODO: Continue extension motor
//    }

    public void setExtension() {
        extension.setRunMode(Motor.RunMode.RawPower);
        extension.set(0.7);
    }

    public void setMinusExtension() {
        extension.setRunMode(Motor.RunMode.RawPower);
        extension.set(-0.8);
    }

    void setDegree() {
        angleControl.setRunMode(Motor.RunMode.RawPower);
        angleControl.set(0.6);
    }

    void setMinusDegree(){
        angleControl.setRunMode(Motor.RunMode.RawPower);
        angleControl.set(-0.6);
    }

    public void stopExtension() {
        extension.set(0);
    }


    public void stopAngleControl() {
        angleControl.set(0);
    }

}
