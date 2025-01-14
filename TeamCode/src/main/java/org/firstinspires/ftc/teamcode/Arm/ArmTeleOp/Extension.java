package org.firstinspires.ftc.teamcode.Arm.ArmTeleOp;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Extension extends OpMode {

    private DcMotor extension;

    @Override
    public void init() {
        extension = hardwareMap.get(DcMotor.class, "extantion");
    }

    @Override
    public void loop() {
       if(gamepad1.right_trigger!=0){
           extension.setPower(0.7);
       }
        else if (gamepad1.right_bumper) {
            extension.setPower(-0.8);
        }
        else{
            extension.setPower(0);
        }

    }
}
