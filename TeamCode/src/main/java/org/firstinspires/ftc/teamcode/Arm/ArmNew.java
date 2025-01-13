package org.firstinspires.ftc.teamcode.Arm;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp
public class ArmNew extends OpMode {
    Arm myArm;

    @Override
    public void init() {
        myArm = new Arm(hardwareMap, gamepad1);
    } 

    @Override
    public void loop() {
        if (gamepad1.right_trigger != 0) {
            myArm.setExtension();
        } else if (gamepad1.right_trigger == 0) {
            myArm.stopExtension();
        }
        if (gamepad1.right_bumper == true) {
            myArm.setMinusExtension();
        }
        else if(gamepad1.right_bumper == false){
            myArm.stopExtension();
        }
       if(gamepad1.dpad_up== true){
           myArm.setDegree();
       }
       else if(gamepad1.dpad_up== false){
           myArm.stopAngleControl();
       }
       if(gamepad1.dpad_down== true){
           myArm.setMinusDegree();
       }
       else if (gamepad1.dpad_down== false) {
           myArm.stopAngleControl();
       }
    }
}

