
package org.firstinspires.ftc.teamcode.Arm.ArmTeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class ArmTeleop extends OpMode {

    private DcMotor angleControl;
    private DcMotor extension;

    @Override
    public void init() {
        angleControl = hardwareMap.get(DcMotor.class, "angleControl");

        extension = hardwareMap.get(DcMotor.class, "extension");
    }

    @Override
    public void loop() {
        /**
         * This chunk of code is responsible for the angle change of the arm subSystem.
         */
        boolean upDegreeControl = gamepad1.dpad_up;
        boolean downDegreeControl = gamepad1.dpad_down;
        if (upDegreeControl == true) {
            angleControl.setPower(0.6);
        } else if (upDegreeControl == false) {
            angleControl.setPower(0);
        }
        if (downDegreeControl== true){
            angleControl.setPower(-0.6);
        }
        else if (downDegreeControl == false) {
            angleControl.setPower(0);
        }

        /**
         * This chunk of code is responsible for arm subSystem extension.
         */
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
