package org.firstinspires.ftc.teamcode.Arm;


import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Arm {
    private final DcMotorEx extension;
    private final DcMotorEx wrist;
    Telemetry telemetry;

    private int wristSetpoint = 0;

    private PIDFController wristController = new PIDFController(0.5, 0.0, 0.0, 1.0);
    private PIDFController extensionController = new PIDFController(5.0, 0.0, 0.0, 0.0);

    public Arm(HardwareMap hardwareMap, Telemetry telemetry) {
        extension = hardwareMap.get(DcMotorEx.class, "extension");
        extension.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extension.setDirection(DcMotorSimple.Direction.REVERSE);
        extension.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        wrist = hardwareMap.get(DcMotorEx.class, "angleControl");
        wrist.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        wrist.setDirection(DcMotorSimple.Direction.REVERSE);
        wrist.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        wristController.setTolerance(2000 );

        this.telemetry = telemetry;
    }

    public void angleDown() {
        wrist.setPower(-0.6);
    }

    public void angleUp() {
        wrist.setPower(0.6);
    }

    public void stopExtension() {
        extension.setPower(0);
    }

    public void stopAngleControl() {
        wrist.setPower(0);
    }

    public void retract() {
        extension.setPower(-0.8);
    }

//    public void leftStickYRetract() {
//        if (myGamepad.left_stick_y < 0) {
//            retract();
//        }
//        else{
//            stopExtension();
//        }
//
//    }

    /**
     * @param position Motor ticks 8192
     */
    public void setWristPosition(int position) {
        wristSetpoint = position;
    }

    public void extend() {
        extension.setPower(0.8);
    }

    public void update() {
        wrist.setPower(wristController.calculate(wrist.getCurrentPosition(), wristSetpoint));
    }

//    public void extensionStickControl() {
//        if (myGamepad.right_trigger != 0) {
//            retract();
//        } else if (myGamepad.left_trigger != 0) {
//            extend();
//        } else if (myGamepad.y) {
//            motor.setTargetPosition(1000);
//            motor.getController()
//            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            telemetry.addData("hello", motor.getTargetPosition());
//        } else {
//            stopExtension();
//        }
//    }

//    public void armControl() {
//        extensionStickControl();
//        dpadAngle();
    }

//    public void dpadAngle() {
//        if (myGamepad.dpad_up) {
//            angleUp();
//        } else if (myGamepad.dpad_down) {
//            angleDown();
//        } else {
//            stopAngleControl();
//        }
//    }

//    public double getAngle() {
//        return (((double) motor.encoder.getPosition()) / 8192 * 360);
//    }
