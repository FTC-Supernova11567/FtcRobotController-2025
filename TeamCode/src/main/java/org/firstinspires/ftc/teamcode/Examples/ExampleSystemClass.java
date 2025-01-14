package org.firstinspires.ftc.teamcode.Examples;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ExampleSystemClass {
    //Here goes the components of the system.
    private final DcMotor myMotor;
    private Gamepad myGamepad;

    //At the default constructor you initialize the motor .
    public ExampleSystemClass(HardwareMap constHardwareMap, Gamepad constGamepad){
        myMotor = constHardwareMap.get(DcMotor.class, "myMotor");
        myGamepad = constGamepad;
    }


    //Here you write the actions your system can do as functions.
    void activateMyMotor(double power){
        myMotor.setPower(power);
    }

    void stopMyMotor(){
        myMotor.setPower(0);
    }

    void activateWithAButton(double power){
        if(myGamepad.a){
            activateMyMotor(power);
        }
        else{
            stopMyMotor();
        }
    }
}
