package org.firstinspires.ftc.teamcode.Elbow;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Lift extends OpMode {

    private DcMotor extantion;

    @Override
    public void init() {
        extantion = hardwareMap.get(DcMotor.class, "extantion");
    }

    @Override
    public void loop() {
       if(gamepad1.right_trigger!=0){
           extantion.setPower(0.7);
       }
        else if (gamepad1.right_bumper) {
            extantion.setPower(-0.8);
        }
        else{
            extantion.setPower(0);
        }
    }
}
