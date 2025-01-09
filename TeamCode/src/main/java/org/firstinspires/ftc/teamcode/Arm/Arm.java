package org.firstinspires.ftc.teamcode.Arm;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm extends SubsystemBase {
    private Motor extension;
    private Motor degree;

    Arm (){
        extension = new Motor(hardwareMap, "extension");
        degree = new Motor(hardwareMap, "degree");
    }

    int getCurrentPosition() {
        return extension.getCurrentPosition();
    }

    Motor getExtension() {
        return extension;
        //TODO: Continue extension motor
    }

    void setExtension() {
        extension.setRunMode(Motor.RunMode.RawPower);
        extension.set(0.6);
    }

    void setDegree() {
        degree.setRunMode(Motor.RunMode.RawPower);
        extension.set(0.6);
    }
}
