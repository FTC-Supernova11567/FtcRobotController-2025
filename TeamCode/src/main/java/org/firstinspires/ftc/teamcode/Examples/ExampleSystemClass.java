package org.firstinspires.ftc.teamcode.Examples;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ExampleSystemClass {
    //Here goes the components of the system.
//    private final DcMotor myMotor;
    private final DcMotorEx PIDMotor;

    private Gamepad gamepad;

    private PIDFController pidfController = new PIDFController(0.001, 0, 0, 0.001);

    //At the default constructor you initialize the motor .
    public ExampleSystemClass(HardwareMap constHardwareMap, Gamepad constGamepad) {
//        myMotor = constHardwareMap.get(DcMotor.class, "myMotor");
         PIDMotor = constHardwareMap.get(DcMotorEx.class, "PIDMotor");

        PIDMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        gamepad = constGamepad;
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

    void setPositionWithBbutton(int wantedPosition) {
        if (gamepad.b) {
            PIDMotor.setPower(pidfController.calculate(PIDMotor.getCurrentPosition(), wantedPosition));
        } else {
            PIDMotor.setPower(0);
        }
    }
    void setPositionWithXbutton(int wantedPosition) {
        if (gamepad.x) {
            PIDMotor.setPower(pidfController.calculate(PIDMotor.getCurrentPosition(), wantedPosition));
        } else {
            PIDMotor.setPower(0);
        }
    }


    double getPose() {
        return (((double) PIDMotor.getCurrentPosition()));
    }
}
