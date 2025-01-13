package org.firstinspires.ftc.teamcode.Arm;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.arcrobotics.ftclib.hardware.motors.Motor;

public class Arm{
    private Motor extension;
    private Motor angleControl;

    public Arm() {
        extension = new Motor(hardwareMap, "extension");
        angleControl = new Motor(hardwareMap, "degree");
    }

    int getCurrentPosition() {
        return extension.getCurrentPosition();
    }

    Motor getExtension() {
        return extension;
        //TODO: Continue extension motor
    }

    public void setExtension() {
        extension.setRunMode(Motor.RunMode.RawPower);
        extension.set(0.6);
    }

    void setDegree() {
        angleControl.setRunMode(Motor.RunMode.RawPower);
        extension.set(0.6);
    }

    public void stopExtension() {
        extension.set(0);
    }


}
